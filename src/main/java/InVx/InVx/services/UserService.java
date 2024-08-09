package InVx.InVx.services;
import InVx.InVx.exceptions.EntityNotFoundException;
import InVx.InVx.models.User;
import InVx.InVx.payload.user.UpdateUser;
import InVx.InVx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    // Gets all user accounts
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Gets one user account
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    // Finds the user if excisted and updates
    public User updateUser(String userId, UpdateUser updateUserDTO) {

        if (updateUserDTO.getPassword() != null) {
            String encodedPassword = encoder.encode(updateUserDTO.getPassword());
            updateUserDTO.setPassword(encodedPassword);
        }
        return userRepository.findById(userId)
                .map(existingUser -> {
                    Optional.ofNullable(updateUserDTO.getPassword()).ifPresent(existingUser::setPassword);
                    Optional.ofNullable(updateUserDTO.getEmail()).ifPresent(existingUser::setEmail);
                    Optional.ofNullable(updateUserDTO.getFirstname()).ifPresent(existingUser::setFirstName);
                    Optional.ofNullable(updateUserDTO.getLastname()).ifPresent(existingUser::setLastName);

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
    }

    // Deletes one user by id
    public ResponseEntity<?> deleteUser(String userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.deleteById(userId);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}
