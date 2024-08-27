package InVx.InVx.controllers;

import InVx.InVx.exceptions.EntityNotFoundException;
import InVx.InVx.payload.userFeedback.CreateUserFeedback;
import InVx.InVx.services.UserFeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userFeedback")
public class UserFeedbackController {

    @Autowired
    UserFeedbackService userFeedbackService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/all")
    public ResponseEntity<?> findAllFeedback() {
        try {
            return ResponseEntity.ok(userFeedbackService.findAll());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createFeedback(@Valid @RequestBody CreateUserFeedback createUserFeedback) {
        try {
            return ResponseEntity.ok(userFeedbackService.save(createUserFeedback));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
