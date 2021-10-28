package pl.recordit.data;

import lombok.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Table(name = "blog_items")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "item_title", nullable = false)
    private String title;

    @Column(length = 10_000)
    private String content;

    private int rating;

    @Version
    private Integer version;
}
