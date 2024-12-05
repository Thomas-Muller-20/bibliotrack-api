package com.bibliotrack.bibliotrackapi.service.impl;

import com.bibliotrack.bibliotrackapi.exeption.BookNotFoundException;
import com.bibliotrack.bibliotrackapi.exeption.ChangeException;
import com.bibliotrack.bibliotrackapi.model.Book;
import com.bibliotrack.bibliotrackapi.repository.BookRepository;
import com.bibliotrack.bibliotrackapi.service.BookService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;

  @Override
  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Optional<Book> findById(Long id) {
    return bookRepository.findById(id);
  }

  @Override
  public Optional<Book> findByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  @Override
  public Optional<Book> findByAuthor(String author) {
    return bookRepository.findByAuthor(author);
  }

  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public Book updateBook(Long id, Book book) {
    try {
      Book existingBook =
          findById(id)
              .orElseThrow(
                  () -> new BookNotFoundException(String.format("Book with id %s not found", id)));

      existingBook.setDescription(book.getDescription());
      existingBook.setAuthor(book.getAuthor());
      existingBook.setTitle(book.getTitle());

      bookRepository.save(existingBook);
      return existingBook;
    } catch (Exception e) {
      throw new ChangeException("Error editing book");
    }
  }

  @Override
  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }
}
