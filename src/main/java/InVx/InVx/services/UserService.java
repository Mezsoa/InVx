package InVx.InVx.services;


import InVx.InVx.models.User;
import InVx.InVx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;




    // Gets all user accounts
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }




}
