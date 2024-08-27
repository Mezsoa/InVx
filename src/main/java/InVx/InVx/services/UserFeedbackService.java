package InVx.InVx.services;

import InVx.InVx.models.UserFeedback;
import InVx.InVx.payload.userFeedback.CreateUserFeedback;
import InVx.InVx.repositories.UserFedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFeedbackService {

    @Autowired
    UserFedbackRepository userFedbackRepository;

    public UserFeedback save(CreateUserFeedback createUserFeedback) {
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUserId(createUserFeedback.getUsername());
        userFeedback.setDescription(createUserFeedback.getMessage());
        userFeedback.setCategory(createUserFeedback.getCategory());
        return userFedbackRepository.save(userFeedback);
    }


    public List<UserFeedback> findAll() {
        return userFedbackRepository.findAll();
    }
}
