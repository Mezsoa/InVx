package InVx.InVx.services;

import InVx.InVx.models.PurchaseIcon;
import InVx.InVx.payload.purchaseIcon.CreatePurchaseIcon;
import InVx.InVx.repositories.PurchaseIconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIconService {

    @Autowired
    PurchaseIconRepository purchaseIconRepository;



    // To find all bought icons by a user
    public List<PurchaseIcon> getAllIcons(String userId) {
        return purchaseIconRepository.findByUserId(userId);
    }

    // Creating a new icon object.
    public PurchaseIcon purchaseIcon(CreatePurchaseIcon createPurchaseIcon) {
        PurchaseIcon newPurchaseIcon = new PurchaseIcon();
        newPurchaseIcon.setUserId(createPurchaseIcon.getUserId());
        newPurchaseIcon.setIconTag(createPurchaseIcon.getIconTag());
        newPurchaseIcon.setCellIndex(createPurchaseIcon.getCellIdenx());
        return purchaseIconRepository.save(newPurchaseIcon);
    }
}
