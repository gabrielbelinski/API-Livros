package apilivros.apilivros.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import apilivros.apilivros.domain.model.Livro;
import apilivros.apilivros.domain.model.Usuario;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByUsuarios(Usuario usuarios);
    
}
