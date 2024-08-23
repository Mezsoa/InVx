package InVx.InVx.controllers;


import InVx.InVx.models.PurchaseIcon;
import InVx.InVx.payload.purchaseIcon.CreatePurchaseIcon;
import InVx.InVx.services.PurchaseIconService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchaseIcon")
public class PurchaseIconController {

    @Autowired
    PurchaseIconService purchaseIconService;




    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addPurchaseIcon(@RequestBody CreatePurchaseIcon createPurchaseIcon) {
        try {
            return ResponseEntity.ok(purchaseIconService.purchaseIcon(createPurchaseIcon));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }





    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/find/all/{userId}")
    public ResponseEntity<?> getAllPurchaseIcons(@PathVariable("userId") String userId) {
        List<PurchaseIcon> foundPurchasedIcons = purchaseIconService.getAllIcons(userId);
        if (foundPurchasedIcons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No purchase icons found");
        } else {
            return ResponseEntity.ok(foundPurchasedIcons);
        }
    }







}
