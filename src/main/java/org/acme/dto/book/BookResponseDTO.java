package org.acme.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.acme.dto.user.UserResponseDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    public Long id;
    public String name;
    public String title;
    @JsonIgnoreProperties("books")
    public Set<UserResponseDTO> authors;
    public Date published;
    public BigDecimal price;
}
