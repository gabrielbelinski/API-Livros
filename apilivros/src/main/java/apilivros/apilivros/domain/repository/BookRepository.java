package apilivros.apilivros.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import apilivros.apilivros.domain.model.Book;
import apilivros.apilivros.domain.model.User;

public interface BookRepository extends JpaRepository<Book, Long> {

    @SuppressWarnings("null")
    Optional<Book> findById(Long id);
    List<Book> findByUsuario(User usuario);
    
}
