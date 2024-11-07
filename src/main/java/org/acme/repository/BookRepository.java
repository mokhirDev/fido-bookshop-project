package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.base.BaseRepository;
import org.acme.entity.Book;

@ApplicationScoped
public class BookRepository implements BaseRepository<Book, Long> {
}
