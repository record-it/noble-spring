package pl.recordit.data;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository books;

    public BookService(BookRepository books) {
        this.books = books;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void insert(Book book){
        books.insert(book);
    }
}
