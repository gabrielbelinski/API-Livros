package apilivros.apilivros.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apilivros.apilivros.domain.dto.book.BookRequestDTO;
import apilivros.apilivros.domain.dto.book.BookResponseDTO;
import apilivros.apilivros.domain.service.BookService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/livros")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAll(){
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@RequestBody 
    BookRequestDTO dto){
        BookResponseDTO responseDTO = bookService.create(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id,
        @RequestBody BookRequestDTO dto){
        BookResponseDTO responseDTO = bookService.update(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
