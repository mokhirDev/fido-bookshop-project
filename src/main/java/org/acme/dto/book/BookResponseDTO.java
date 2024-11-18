package org.acme.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;
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
@RegisterForReflection
public class BookResponseDTO {
    public Long id;
    public String name;
    public String title;
    @JsonIgnoreProperties("books")
    public Set<User> authors;
    public LocalDate published;
    public BigDecimal price;
}
