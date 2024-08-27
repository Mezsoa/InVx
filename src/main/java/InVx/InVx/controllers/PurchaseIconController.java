package InVx.InVx.controllers;
import InVx.InVx.models.PurchaseIcon;
import InVx.InVx.repositories.PurchaseIconRepository;
import InVx.InVx.services.PurchaseIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/purchaseIcon")
public class PurchaseIconController {

    @Autowired
    PurchaseIconService purchaseIconService;
    @Autowired
    private PurchaseIconRepository purchaseIconRepository;


    // PURCHASE AN ICON AS A USER
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId, @RequestParam("cellIndex") int cellIndex) throws IOException {
        return new ResponseEntity<>(purchaseIconService.addFile(file, userId, cellIndex), HttpStatus.OK);
    }


    @GetMapping("/load/icon/{userId}")
    public ResponseEntity<List<PurchaseIcon>> loadIcon(@PathVariable String userId) {
        System.out.println("Fetching icons for userId: " + userId);
        List<PurchaseIcon> icons = purchaseIconService.getIconsByUserId(userId);
        System.out.println("Icons retrieved: " + icons.size());
        return new ResponseEntity<>(icons, HttpStatus.OK);
    }


    // DOWNLOAD A FILE
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/download/{Id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        PurchaseIcon loadFile = purchaseIconService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getIconTag() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }


}
