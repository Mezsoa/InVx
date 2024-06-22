package InVx.InVx.services;
import InVx.InVx.models.User;
import InVx.InVx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // Gets all user accounts
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Gets one user account
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Finds the user if excisted and updates
    public User updateUser(User user) {
        return userRepository.findById(user.getId()).map(existingUser -> {
            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getDateOfBirth() != null) {
                existingUser.setDateOfBirth(user.getDateOfBirth());
            }
            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Deletes one user by id
    public ResponseEntity<?> deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }



}
