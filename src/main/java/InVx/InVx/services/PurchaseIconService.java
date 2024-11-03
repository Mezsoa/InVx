package InVx.InVx.services;
import InVx.InVx.models.PurchaseIcon;

import InVx.InVx.models.User;
import InVx.InVx.payload.purchaseIcon.CreatePurchaseIcon;


import InVx.InVx.repositories.PurchaseIconRepository;

import InVx.InVx.repositories.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@Service
public class PurchaseIconService {

    @Autowired
    PurchaseIconRepository purchaseIconRepository;

    @Autowired
    private GridFsOperations operations;
    @Autowired
    private GridFsTemplate template;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    UserRepository userRepository;

    private final SimpMessagingTemplate messagingTemplate;

    public PurchaseIconService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    // This is taken from stackoverflow and im not exactly sure how this works.....
    // more then that it splits up the image in fs.shunks and fs.files.
//    public String addFile(MultipartFile upload, String userId, int cellIndex) throws IOException {
//
//        DBObject metadata = new BasicDBObject();
//        metadata.put("fileSize", upload.getSize());
//        metadata.put("userId", userId);              // Add userId
//        metadata.put("cellIndex", cellIndex);        // Add cellIndex
//        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
//
//        // Storing the metadata in the purchaseicon collection aswell to ensure it sticks to the user
//        PurchaseIcon purchaseIcon = new PurchaseIcon(upload.getOriginalFilename(), cellIndex, userId, upload.getContentType(), String.valueOf(upload.getSize()), null);
//        purchaseIconRepository.save(purchaseIcon);
//        return fileID.toString();
//    }

    public String addFile(MultipartFile upload, String userId, int cellIndex) throws IOException {

        // 1. Check if an icon already exists for this cell and user
        Query existingIconQuery = new Query(Criteria.where("userId").is(userId).and("cellIndex").is(cellIndex));
        PurchaseIcon existingIcon = mongoTemplate.findOne(existingIconQuery, PurchaseIcon.class);

        // 2. If an icon exists, delete it from both GridFS and the PurchaseIcon collection
        if (existingIcon != null) {
            // Delete the file from GridFS
            Query gridFSQuery = new Query(Criteria.where("filename").is(existingIcon.getIconTag()));
            GridFSFile fileToDelete = template.findOne(gridFSQuery);
            if (fileToDelete != null) {
                template.delete(gridFSQuery);
            }

            // Delete metadata from the PurchaseIcon collection
            purchaseIconRepository.delete(existingIcon);
        }

        // 3. Upload the new icon
        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());
        metadata.put("userId", userId);
        metadata.put("cellIndex", cellIndex);
        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);

        // 4. Save metadata in the PurchaseIcon collection
        PurchaseIcon purchaseIcon = new PurchaseIcon(upload.getOriginalFilename(), cellIndex, userId, upload.getContentType(), String.valueOf(upload.getSize()), null);
        purchaseIconRepository.save(purchaseIcon);
        // skicka notis till högsta budgivare
        messagingTemplate.convertAndSendToUser(

                userId.toString(),
                "/private",
                "You have successfully bought a new dragon " + purchaseIcon.getIconTag()
        );

        return fileID.toString();
    }

    public boolean deleteFile(String userId, int cellIndex) {
        try {
            // Find the existing icon metadata in the PurchaseIcon collection
            Query query = new Query(Criteria.where("userId").is(userId).and("cellIndex").is(cellIndex));
            PurchaseIcon existingIcon = mongoTemplate.findOne(query, PurchaseIcon.class);

            if (existingIcon != null) {
                // Delete the file from GridFS based on the filename
                Query gridFSQuery = new Query(Criteria.where("filename").is(existingIcon.getIconTag()));
                GridFSFile fileToDelete = template.findOne(gridFSQuery);
                if (fileToDelete != null) {
                    template.delete(gridFSQuery); // Remove from GridFS
                }

                // Remove the metadata from the PurchaseIcon collection
                purchaseIconRepository.delete(existingIcon);

                return true; // Deletion was successful
            } else {
                System.out.println("No icon found for the specified userId and cellIndex.");
                return false; // No icon found to delete
            }
        } catch (Exception e) {
            System.err.println("Error deleting file: " + e.getMessage());
            return false; // Deletion failed
        }
    }



    public List<PurchaseIcon> getIconsByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, PurchaseIcon.class);
    }

    // TO DOWNLOAD AN ICON

    public PurchaseIcon downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));

        PurchaseIcon loadFile = new PurchaseIcon();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setIconTag(gridFSFile.getFilename());
            loadFile.setFileType(gridFSFile.getMetadata().get("_contentType").toString());
            loadFile.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());
            loadFile.setUserId(gridFSFile.getMetadata().get("userId").toString());  // Get userId
            loadFile.setCellIndex((Integer) gridFSFile.getMetadata().get("cellIndex")); // Get cellIndex
            loadFile.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
        }

        return loadFile;
    }
}
