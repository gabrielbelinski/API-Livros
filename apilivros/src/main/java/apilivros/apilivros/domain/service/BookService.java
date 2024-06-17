package apilivros.apilivros.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import apilivros.apilivros.domain.dto.book.BookRequestDTO;
import apilivros.apilivros.domain.dto.book.BookResponseDTO;
import apilivros.apilivros.domain.exception.ResourceNotFoundException;
import apilivros.apilivros.domain.model.Book;
import apilivros.apilivros.domain.model.User;
import apilivros.apilivros.domain.repository.BookRepository;

@Service
public class BookService implements ICRUDService<BookRequestDTO, BookResponseDTO> {

    @Autowired
    private BookRepository livroRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<BookResponseDTO> getAll() {
        User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Book> livros = livroRepository.findByUsuario(usuario);
        return livros.stream().map(livro -> mapper.map(livro, BookResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO getById(Long id) {
       Optional<Book> optLivro = livroRepository.findById(id);
       if(!optLivro.isPresent()){
            throw new ResourceNotFoundException("Livro de ID " + id + " não encontrado.");
       }
      User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if(optLivro.get().getUsuario().getId() != usuario.getId()){
            throw new ResourceNotFoundException("Erro! Livro de ID " + id + " não pertence ao usuário autenticado.");
      } 
        return mapper.map(optLivro.get(), BookResponseDTO.class);
    }

    @Override
    public BookResponseDTO create(BookRequestDTO dto) {
        Book livro = mapper.map(dto, Book.class);
        User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       livro.setUsuario(usuario);
       livro.setId(null);
       livro = livroRepository.save(livro);
       return mapper.map(livro, BookResponseDTO.class);
    }

    @Override
    public BookResponseDTO update(Long id, BookRequestDTO dto) {
        getById(id);
        Book livro = mapper.map(dto, Book.class);
        User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       livro.setUsuario(usuario);
       livro.setId(id);
       livro = livroRepository.save(livro);
       return mapper.map(livro, BookResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        livroRepository.deleteById(id);
    }
    
}
