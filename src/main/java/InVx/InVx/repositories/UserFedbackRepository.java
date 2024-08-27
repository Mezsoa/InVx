package InVx.InVx.repositories;

import InVx.InVx.models.UserFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserFedbackRepository extends MongoRepository<UserFeedback, String> {
    public UserFeedback findByUserId(String userId);
}
