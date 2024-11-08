package org.acme.dto.user;


import lombok.*;
import org.acme.dto.book.BookRequestDTO;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    public Long id;
    public String name;
    public String fullName;
    public String username;
    public String password;
    public String email;
    public String phone;
    public Set<BookRequestDTO> books;
}
