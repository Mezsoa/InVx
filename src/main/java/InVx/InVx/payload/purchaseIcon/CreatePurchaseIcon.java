package InVx.InVx.payload.purchaseIcon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreatePurchaseIcon {
    @NotBlank
    private String userId;
    @NotBlank
    private String iconTag;
    @NotNull
    private int cellIndex;

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


    @NotNull
    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(@NotNull int cellIndex) {
        this.cellIndex = cellIndex;
    }
}
