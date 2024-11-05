package org.acme.book.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.book.aggregate.entity.Book;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {
}
