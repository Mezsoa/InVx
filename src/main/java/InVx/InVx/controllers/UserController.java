package InVx.InVx.controllers;


import InVx.InVx.exceptions.EntityNotFoundException;
import InVx.InVx.models.User;
import InVx.InVx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;


    //find all user accounts
    @GetMapping("/find/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Gets one user by its user ID
    @GetMapping("/find-one/{userId}")
    public ResponseEntity<User> getUserById(
            @PathVariable("userId") String userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

















}
