package InVx.InVx.models;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "purchaseicon")
public class PurchaseIcon {

    @Id
    private String id;
    private String userId;
    // EXAMPLE blue dragon egg or blue dragon.
    private String iconTag;
    //Where/what index the icon is going to be place at inside the grid-cell in front-end
    private int cellIndex;

    @CreatedDate
    private Date purchaseDate = new Date();


    public PurchaseIcon() {
    }

    public PurchaseIcon(String iconTag, int cellIndex, String userId) {
        this.iconTag = iconTag;
        this.cellIndex = cellIndex;
        this.userId = userId;
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

    public String getIconTag() {
        return iconTag;
    }

    public void setIconTag(String iconTag) {
        this.iconTag = iconTag;
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
}
