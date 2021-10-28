package pl.noble.nobleprogspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import pl.noble.nobleprogspring.BookRepository;
import pl.noble.nobleprogspring.model.Book;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1")
public class RestBookController {

    private final BookRepository bookRepository;
    private AtomicInteger index = new AtomicInteger();

    public RestBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        Book newBook = Book.builder()
                .id(index.addAndGet(1))
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
        bookRepository.add(newBook);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newBook);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findBook(@PathVariable int id){
        return ResponseEntity.of(bookRepository.findById(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Error> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Error("Coś nie tak z książką " + e.getMessage()));
    }
}
