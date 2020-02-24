package tech.bestwebshop.api.authorizationservice.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.bestwebshop.api.authorizationservice.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
