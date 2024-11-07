package org.acme.dto.book;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
}
