package InVx.InVx.payload.purchaseIcon;

import jakarta.validation.constraints.NotBlank;

public class CreatePurchaseIcon {
    @NotBlank
    private String userId;
    @NotBlank
    private String iconTag;
    @NotBlank
    private int cellIdenx;

    public @NotBlank String getUserId() {
        return userId;
    }

    public void setUserId(@NotBlank String userId) {
        this.userId = userId;
    }



    public @NotBlank String getIconTag() {
        return iconTag;
    }

    public void setIconTag(@NotBlank String iconTag) {
        this.iconTag = iconTag;
    }

    @NotBlank
    public int getCellIdenx() {
        return cellIdenx;
    }

    public void setCellIdenx(@NotBlank int cellIdenx) {
        this.cellIdenx = cellIdenx;
    }
}
