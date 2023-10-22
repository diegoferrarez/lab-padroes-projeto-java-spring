package one.digitalinnovation.gof.repository;

import one.digitalinnovation.gof.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStoreRepository extends MongoRepository<Product, String> {
}
