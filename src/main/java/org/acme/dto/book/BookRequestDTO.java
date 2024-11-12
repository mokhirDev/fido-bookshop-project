package org.acme.dto.book;

import lombok.*;
import org.acme.dto.user.UserRequestDTO;
import org.acme.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    public Long id;
    public String name;
    public String title;
    public LocalDate published;
    public BigDecimal price;
    public Set<User> authors;
}
