package com.bibliotrack.bibliotrackapi.service;

import com.bibliotrack.bibliotrackapi.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
  Book createBook(Book book);

  Optional<Book> findById(Long id);

  Optional<Book> findByTitle(String title);

  Optional<Book> findByAuthor(String author);

  List<Book> findAll();

  Book updateBook(Long id, Book book);

  void deleteBook(Long id);
}
