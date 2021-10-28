package pl.recordit.data;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookRepository {
    private final JdbcTemplate template;

    public BookRepository(JdbcTemplate template) {
        this.template = template;
    }

    public long insert(final Book book) {
        return template.update("insert into books(title, author) values(?,?);", pss -> {
            pss.setString(1, book.getTitle());
            pss.setString(2, book.getAuthor());
        });
    }

    public List<Book> findAll() {
        return template.query("select * from books;", (row, n) -> {
            return Book.builder()
                    .title(row.getString("title"))
                    .author(row.getString("author"))
                    .id(row.getInt("id"))
                    .build();
        });
    }

    public long delete(Book book) {
        return template.update("delete from books where id = ?", book.getId());
    }

    public Optional<Book> findById(int id) {
        try {
            return Optional.ofNullable(template.queryForObject("select * from books where id = ?", (r, n) ->
                                    Book.builder()
                                            .title(r.getString("title"))
                                            .author(r.getString("author"))
                                            .id(r.getInt("id"))
                                            .build()
                            , id
                    )
            );
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }


    public int count(String author){
        var parameters = new MapSqlParameterSource();
        parameters.addValue("author", author);
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(template);
        return namedTemplate.queryForObject("select count(*) from books where author =:author",
                parameters,
                Integer.class);
    }
}
