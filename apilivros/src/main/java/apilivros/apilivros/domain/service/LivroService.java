package apilivros.apilivros.domain.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import apilivros.apilivros.domain.dto.livro.LivroRequestDTO;
import apilivros.apilivros.domain.dto.livro.LivroResponseDTO;
import apilivros.apilivros.domain.exception.ResourceNotFoundException;
import apilivros.apilivros.domain.model.Livro;
import apilivros.apilivros.domain.model.Usuario;
import apilivros.apilivros.domain.repository.LivroRepository;

@Service
public class LivroService implements ICRUDService<LivroRequestDTO, LivroResponseDTO> {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<LivroResponseDTO> obterTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Livro> livros = livroRepository.findByUsuarios(usuario);
        return livros.stream().map(livro -> mapper.map(livro,
        LivroResponseDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public LivroResponseDTO obterPorId(Long id) {
       Optional<Livro> optLivro = livroRepository.findById(id);
       if(optLivro.isEmpty()){
        throw new ResourceNotFoundException("Não foi possível encontrar o título com o id: "+id);
       }
       Usuario usuario = (Usuario) SecurityContextHolder.getContext()
       .getAuthentication().getPrincipal();
       List<Usuario> usuarios = optLivro.get().getUsuarios();
       boolean usuarioEncontrado = usuarios.stream()
               .anyMatch(u -> u.getId().equals(usuario.getId()));
       if(!usuarioEncontrado){
           throw new ResourceNotFoundException("O título de id: " +
                   id + "não pertence a esse usuário");
       }
       return mapper.map(optLivro, LivroResponseDTO.class);
    }

    @Override
    public LivroResponseDTO cadastrar(LivroRequestDTO dto) {
        Livro livro = mapper.map(dto, Livro.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       livro.setUsuarios(Arrays.asList(usuario));
       livro.setId(null);
       livro = livroRepository.save(livro);
       return mapper.map(livro, LivroResponseDTO.class);
    }

    @Override
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        obterPorId(id);
        Livro livro = mapper.map(dto, Livro.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       livro.setUsuarios(Arrays.asList(usuario));
       livro.setId(id);
       livro = livroRepository.save(livro);
       return mapper.map(livro, LivroResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        livroRepository.deleteById(id);
    }
    
}
