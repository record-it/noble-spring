package pl.recordit.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "items", collectionResourceRel = "items")
public interface RestBlogRepository extends PagingAndSortingRepository<BlogItem, Long> {
}
