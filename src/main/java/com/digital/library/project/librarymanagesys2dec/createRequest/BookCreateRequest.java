package com.digital.library.project.librarymanagesys2dec.createRequest;

import com.digital.library.project.librarymanagesys2dec.models.Author;
import com.digital.library.project.librarymanagesys2dec.models.Book;
import com.digital.library.project.librarymanagesys2dec.models.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest {
    @NotBlank(message="name of book should not be null or empty")
    private String name;
    @NotBlank
    @Email(message="enter valid email address")
    private String email;
    @Positive
    private int price;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private String authorName;
    public Book to(){
        Author author = Author.builder()
                .authName(authorName).email(email)
                .build();
        return Book.builder().name(name).genre(genre)
                .myAuthor(author).price(price).build();
    }

}
