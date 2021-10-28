package pl.recordit.data;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface JpaBlogRepository extends JpaRepository<BlogItem, Long> {
    int countByRatingGreaterThan(int limit);
    //Wyszukaj wpisy z tytyłem zaczynającym się prefiksem

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    List<BlogItem> findAllByTitleStartsWith(String prefix);

    @Query(value = "select count(*) from blog_items where rating=?1", nativeQuery = true)
    int countBlogItemsByRating(int rating);


    @Query(value = "select id, item_title from blog_items", nativeQuery = true)
    List<Map<String, Object>> findTitleAndId();

    default List<IdAndTitle> findTitlesWithId(){
        return findTitleAndId().stream().map(e ->
                new IdAndTitle((BigInteger) e.get("id"), (String) e.get("item_title")))
                .collect(Collectors.toList());
    }

    @Getter
    @ToString
    class IdAndTitle{
        private BigInteger id;
        private String title;

        private IdAndTitle(BigInteger id, String title) {
            this.id = id;
            this.title = title;
        }
    }
}
