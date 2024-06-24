package apilivros.apilivros.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apilivros.apilivros.domain.dto.user.*;
import apilivros.apilivros.domain.exception.BadRequestException;
import apilivros.apilivros.domain.exception.ResourceNotFoundException;
import apilivros.apilivros.domain.repository.UserRepository;
import apilivros.apilivros.domain.model.User;


@Service
public class UserService implements ICRUDService<UserRequestDTO, UserResponseDTO>{

      @Autowired
      private UserRepository usuarioRepository;
      @Autowired
      private ModelMapper mapper;
      @Autowired
      private BCryptPasswordEncoder bCryptPasswordEncoder;

      @Override
      public List<UserResponseDTO> getAll() {
        List<User> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> mapper.map(usuario, UserResponseDTO.class)).collect(Collectors.toList());
    }

      @Override
      public UserResponseDTO getById(Long id) {
        Optional<User> optUsuario = usuarioRepository.findById(id);
        if(optUsuario.isEmpty()){
          throw new ResourceNotFoundException("Não foi possível encontar o usuário de id " + id);
        }
        return mapper.map(optUsuario.get(), UserResponseDTO.class);
    }

      @Override
      public UserResponseDTO create(UserRequestDTO dto) {
      if(dto.getEmail() == null || dto.getSenha() == null){
          throw new BadRequestException("Email e senha são campos obrigatórios!");
      }
      Optional<User> optUsuario = usuarioRepository.findByEmail(dto.getEmail());
      if(optUsuario.isPresent()){
          throw new BadRequestException("Já existe um usuário cadastrado com esse email.");     
     }
      User usuario = mapper.map(dto, User.class);
       //criptografar senha
      String senha = bCryptPasswordEncoder.encode(usuario.getSenha());
       usuario.setSenha(senha);
       usuario.setId(null);
       usuario.setDataCadastro(new Date());
       usuario = usuarioRepository.save(usuario);
       return mapper.map(usuario, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        UserResponseDTO usuarioBanco = getById(id);
        if(dto.getEmail() == null || dto.getSenha() == null){
            throw new BadRequestException("Email e Senha são Obrigatórios!");
          }
        User usuario = mapper
       .map(dto, User.class);
       String senha = bCryptPasswordEncoder.encode(dto.getSenha());
       usuario.setSenha(senha);
       usuario.setId(id);
       usuario.setDataCadastro(usuarioBanco.getDataCadastro());
       usuario.setDataInativacao(usuarioBanco.getDataInativacao());
       usuario = usuarioRepository.save(usuario);
       return mapper.map(usuario, UserResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
      Optional<User> optUsuario = 
      usuarioRepository.findById(id);
      if(optUsuario.isEmpty()){
        throw new ResourceNotFoundException("não foi possível encontrar o usuário");
      }
      User usuario = optUsuario.get();
      usuario.setDataInativacao(new Date());
      usuarioRepository.save(usuario);
    }
    
}
