package com.bibliotrack.bibliotrackapi.repository;

import com.bibliotrack.bibliotrackapi.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findByTitle(String title);

  Optional<Book> findByAuthor(String author);
}
