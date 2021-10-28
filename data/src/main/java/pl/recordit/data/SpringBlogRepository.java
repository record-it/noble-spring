package pl.recordit.data;

import org.springframework.data.repository.CrudRepository;

public interface SpringBlogRepository extends CrudRepository<BlogItem, Long> {
}
