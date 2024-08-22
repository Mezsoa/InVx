package InVx.InVx.repositories;

import InVx.InVx.models.PurchaseIcon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PurchaseIconRepository extends MongoRepository<PurchaseIcon, String> {
    List<PurchaseIcon> findByUserId(String userId);
}
