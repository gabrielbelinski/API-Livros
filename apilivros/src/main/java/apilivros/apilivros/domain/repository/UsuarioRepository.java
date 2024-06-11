package apilivros.apilivros.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import apilivros.apilivros.domain.model.Usuario;



public interface UsuarioRepository extends 
    JpaRepository<Usuario, Long> {
       
        Optional<Usuario> findByEmail(String email);
    
}
