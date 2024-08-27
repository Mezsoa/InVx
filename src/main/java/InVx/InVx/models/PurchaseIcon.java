package InVx.InVx.models;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Document(collection = "purchaseicon")
public class PurchaseIcon {

    @Id
    private String id;
    private String userId;
    private String iconTag;
    private String fileType;
    private String fileSize;
    private byte[] file;
    //Where/what index the icon is going to be place at inside the grid-cell in front-end
    private int cellIndex;

    @CreatedDate
    private Date purchaseDate = new Date();


    public PurchaseIcon() {
    }

    public PurchaseIcon(String iconTag, int cellIndex, String userId,String fileType, String fileSize, byte[] file) {
        this.iconTag = iconTag;
        this.cellIndex = cellIndex;
        this.userId = userId;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.file = file;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIconTag() {
        return iconTag;
    }

    public void setIconTag(String iconTag) {
        this.iconTag = iconTag;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
