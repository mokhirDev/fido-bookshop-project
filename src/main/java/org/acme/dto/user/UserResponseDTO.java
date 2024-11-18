package org.acme.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;
import org.acme.dto.book.BookResponseDTO;
import org.acme.entity.Book;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class UserResponseDTO {
    public Long id;
    public String name;
    public String fullName;
    public String username;
    public String password;
    public String email;
    public String phone;
//    @JsonIgnoreProperties("authors")
    private Set<Book> books;
}
