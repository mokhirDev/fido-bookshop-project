package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import lombok.*;
import org.acme.base.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class Book extends BaseEntity implements Serializable {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Serial
    private static final long serialVersionUID = 4773805991841170855L;
    public String name;
    public String title;
    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    public Set<User> authors;
    public LocalDate published;
    public BigDecimal price;
}
