package InVx.InVx.controllers;


import InVx.InVx.exceptions.EntityNotFoundException;
import InVx.InVx.models.User;
import InVx.InVx.payload.user.UpdateUser;
import InVx.InVx.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    //find all user accounts
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Gets one user by its user ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getUserById(@Valid @PathVariable String userId) {
        try {
            return ResponseEntity.ok(userService.getUserById(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUser updateUser, @PathVariable("id") String id) {
        try {
            User updatedUser = userService.updateUser(id, updateUser);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable("userId") String userId) {
        try {
            return userService.deleteUser(userId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}