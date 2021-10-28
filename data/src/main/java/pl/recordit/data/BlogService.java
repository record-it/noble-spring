package pl.recordit.data;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final JpaBlogRepository repository;
    private final BookService bookService;

    public BlogService(JpaBlogRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Transactional
    public List<BlogItem> findByTitle(String prefix){
        return repository.findAllByTitleStartsWith(prefix);
    }

    @Transactional
    public void updateTitle(BlogItem item, String title){
       final Optional<BlogItem> i = repository.findById(item.getId());
       if (i.isPresent()) {
           i.get().setTitle(title);
           repository.save(i.get());
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           repository.save(i.get());
       }
    }


    @Transactional(rollbackFor = ArrayIndexOutOfBoundsException.class, noRollbackFor = ArithmeticException.class)
    public void addTwoItems(BlogItem ... items){
        if (items[0].getId() == 0){
            repository.save(items[0]);
        } else {
            throw new ArithmeticException();
        }
        if (items[1].getId() == 0){
            repository.save(items[1]);
        } else {
            throw new ArithmeticException();
        }
    }

    public void addItemAboutBook(BlogItem item, Book book){
        repository.save(item);
        try {
            bookService.insert(book);
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
        }
    }
}
