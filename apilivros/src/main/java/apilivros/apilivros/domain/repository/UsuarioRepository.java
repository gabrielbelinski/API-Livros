package apilivros.apilivros.domain.repository;

import java.util.Optional;
import apilivros.apilivros.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepository extends 
    JpaRepository<Usuario, Long> {
        Optional<Usuario> findByEmail(String email);
}
