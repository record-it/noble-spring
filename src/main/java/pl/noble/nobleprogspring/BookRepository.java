package pl.noble.nobleprogspring;

import org.springframework.stereotype.Service;
import pl.noble.nobleprogspring.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookRepository {
    private List<Book> books = new ArrayList<>();

    public void add(Book book){
        books.add(book);
    }

    public List<Book> findAll(){
        return books;
    }

    public Optional<Book> findById(int id){
        return books.stream().filter(b-> b.getId() == id).findAny();
    }
}
