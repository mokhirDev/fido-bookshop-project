package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import lombok.*;
import org.acme.base.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class User extends BaseEntity implements Serializable {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Serial
    private static final long serialVersionUID = 4773805991841170855L;
    public String name;
    public String fullName;
    @Column(unique = true)
    public String username;
    public String password;
    @Column(unique = true)
    public String email;
    @Column(unique = true)
    public String phone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    @JsonManagedReference
    private Set<Book> books;
}
