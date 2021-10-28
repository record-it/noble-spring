package pl.recordit.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DataApplication implements CommandLineRunner {
    final BookRepository books;
    final BlogItemRepository items;
    final SpringBlogRepository blogRepository;
    final JpaBlogRepository jpaBlogRepository;
    final BlogService service;

    public DataApplication(BookRepository books, BlogItemRepository items, SpringBlogRepository blogRepository, JpaBlogRepository jpaBlogRepository, BlogService service) {
        this.books = books;
        this.items = items;
        this.blogRepository = blogRepository;
        this.jpaBlogRepository = jpaBlogRepository;
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        extracted();
        //extracted1();
        //extracted2();
        final List<BlogItem> aa = service.findByTitle("AA");
        System.out.println(aa);
        new Thread(() -> {
            service.updateTitle(aa.get(1), "AAAA");
        }).start();
        new Thread(() -> {
            service.updateTitle(aa.get(1), "BBB");
        }).start();
        Thread.sleep(1000);
        System.out.println(service.findByTitle("AA"));
    }

    private void extracted2() {
        try {
            BlogItem i1 = BlogItem.builder().title("Test1").rating(12).build();
            service.addItemAboutBook(i1, Book.builder().title("AAAA").author("VVVV").build());
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
        }
        System.out.println(jpaBlogRepository.findAll());
        System.out.println(books.findAll());
    }

    private void extracted1() {
        BlogItem i1 = BlogItem.builder().title("Test1").rating(12).build();
        BlogItem i2 = BlogItem.builder().title("Test2").rating(12).id(1).build();
        try {
            service.addTwoItems(i1);
        }catch (RuntimeException e){

        }
        System.out.println(jpaBlogRepository.findAll());
    }

    private void extracted() {
        final Book book = Book.builder().title("Test").author("test").build();
        books.insert(book);
        System.out.println(books.findAll());
        books.delete(Book.builder().id(3).build());
        System.out.println(books.findAll());
        System.out.println(books.findById(3));
        System.out.println(books.findById(1));
        System.out.println(books.count("Bloch"));
        items.save(BlogItem.builder().title("AAAA").content("BBBB").rating(2).build());
        items.save(BlogItem.builder().title("VVVV").content("CCCC").rating(22).build());
        System.out.println(items.findAll());
        blogRepository.save(BlogItem.builder().title("AAAA").content("BBBB").rating(2).build());
        blogRepository.save(BlogItem.builder().title("CCCC").content("CCCC").rating(5).build());
        System.out.println(blogRepository.findAll());
        System.out.println(jpaBlogRepository.findAll());
        System.out.println("Liczba arytkułów  punktacją większą od 3: "
                + jpaBlogRepository.countByRatingGreaterThan(3));
        System.out.println(jpaBlogRepository.countBlogItemsByRating(2));
        final List<Map<String, Object>> list = jpaBlogRepository.findTitleAndId();
        list.forEach(e -> {
            System.out.println(e.get("id") +" " + e.get("item_title"));
        });

        System.out.println(jpaBlogRepository.findTitlesWithId());
    }
}
