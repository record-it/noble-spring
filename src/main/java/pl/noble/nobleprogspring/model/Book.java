package pl.noble.nobleprogspring.model;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@Setter
public class Book {
    private int id;
    @NotNull(message = "Tytuł nie może być pusty!")
    @Pattern(regexp = "[a-zA-Z#]{3,40}", message = "Błędny znak w tytule")
    private String title;
    @NotNull(message = "Musi być autor!")
    private String author;
}
