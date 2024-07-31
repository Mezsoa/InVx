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
    private PasswordEncoder encoder;

    // Gets all user accounts
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Gets one user account
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Finds the user if excisted and updates
    public User updateUser(String id, UpdateUser updateUserDTO) {

        if (updateUserDTO.getPassword() != null) {
            String encodedPassword = encoder.encode(updateUserDTO.getPassword());
            updateUserDTO.setPassword(encodedPassword);
        }

        return userRepository.findById(id)
                .map(existingUser -> {
                    Optional.ofNullable(updateUserDTO.getPassword()).ifPresent(existingUser::setPassword);
                    Optional.ofNullable(updateUserDTO.getEmail()).ifPresent(existingUser::setEmail);
                    Optional.ofNullable(updateUserDTO.getFirstname()).ifPresent(existingUser::setFirstName);
                    Optional.ofNullable(updateUserDTO.getLastname()).ifPresent(existingUser::setLastName);

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }
//    public User updateUser(User user) {
//        return userRepository.findById(user.getId()).map(existingUser -> {
//            if (user.getUsername() != null) {
//                existingUser.setUsername(user.getUsername());
//            }
//            if (user.getEmail() != null) {
//                existingUser.setEmail(user.getEmail());
//            }
//            if (user.getPassword() != null) {
//                existingUser.setPassword(user.getPassword());
//            }
//            if (user.getDateOfBirth() != null) {
//                existingUser.setDateOfBirth(user.getDateOfBirth());
//            }
//            if (user.getFirstName() != null) {
//                existingUser.setFirstName(user.getFirstName());
//            }
//            if (user.getLastName() != null) {
//                existingUser.setLastName(user.getLastName());
//            }
//            return userRepository.save(existingUser);
//        }).orElseThrow(() -> new RuntimeException("User not found"));
//    }

    // Deletes one user by id
    public ResponseEntity<?> deleteUser(String id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }



}
