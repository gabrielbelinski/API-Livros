package apilivros.apilivros.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import apilivros.apilivros.domain.model.User;
import apilivros.apilivros.domain.repository.UserRepository;


@Component
public class UserDetailsSecurityServer 
                implements UserDetailsService {
    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        Optional<User> optUsuario = usuarioRepository
        .findByEmail(username);
        if(optUsuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário ou Senha Inválidos");
        }
        return optUsuario.get();
    }
        
    
}
