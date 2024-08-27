package InVx.InVx.payload.purchaseIcon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class CreatePurchaseIcon {
    @NotBlank
    private String userId;

   // private MultipartFile iconTag;
    private String fileType;
    private String fileSize;
    private byte[] file;
    private String iconTag;
    @NotNull
    private int cellIndex;




    public @NotBlank String getUserId() {
        return userId;
    }

    public void setUserId(@NotBlank String userId) {
        this.userId = userId;
    }





    @NotNull
    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(@NotNull int cellIndex) {
        this.cellIndex = cellIndex;
    }

//    public MultipartFile getIconTag() {
//        return iconTag;
//    }
//
//    public void setIconTag(MultipartFile iconTag) {
//        this.iconTag = iconTag;
//    }

    public void setIconTag(String iconTag) {
        this.iconTag = iconTag;
    }

    public String getIconTag() {
        return iconTag;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
