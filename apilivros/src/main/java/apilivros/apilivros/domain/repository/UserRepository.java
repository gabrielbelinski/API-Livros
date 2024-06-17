package apilivros.apilivros.domain.repository;

import java.util.Optional;
import apilivros.apilivros.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
