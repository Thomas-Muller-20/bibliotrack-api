package com.bibliotrack.bibliotrackapi.integration.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bibliotrack.bibliotrackapi.integration.testcontainer.TestContainersConfig;
import com.bibliotrack.bibliotrackapi.model.Book;
import com.bibliotrack.bibliotrackapi.repository.BookRepository;
import com.bibliotrack.bibliotrackapi.service.impl.BookServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TestContainersConfig.class)
@Transactional
public class BookServiceTest {

  @Autowired private BookRepository bookRepository;

  @Autowired private BookServiceImpl bookService;

  @AfterEach
  void cleanAll() {
    bookRepository.deleteAll();
  }

  @Test
  @Sql("/books-create.sql")
  void createBook() {
    Book book = new Book();
    book.setTitle("New Book");
    book.setAuthor("New Author");
    book.setDescription("New Book Description");

    Book savedBook = bookService.createBook(book);
    assertThat(savedBook).isNotNull();
    assertThat(savedBook.getId()).isNotNull();
    assertEquals(book.getTitle(), savedBook.getTitle());
  }

  @Test
  @Sql("/books-create.sql")
  void findBookById() {
    Long bookId = 1L;
    Book foundBook = bookService.findById(bookId).orElseThrow();
    assertEquals(bookId, foundBook.getId());
  }

  @Test
  @Sql("/books-create.sql")
  void findBookByTitle() {
    String title = "Book One";
    Book foundBook = bookService.findByTitle(title).orElseThrow();
    assertEquals(title, foundBook.getTitle());
  }

  @Test
  @Sql("/books-create.sql")
  void findAllBooks() {
    var books = bookService.findAll();
    assertThat(books).isNotEmpty();
    assertThat(books.size()).isEqualTo(4);
  }

  @Test
  @Sql("/books-create.sql")
  void updateBook() {
    Long bookId = 5L;
    Book bookToUpdate = new Book();
    bookToUpdate.setTitle("Updated Title");
    bookToUpdate.setAuthor("Updated Author");
    bookToUpdate.setDescription("Updated Description");

    Book updatedBook = bookService.updateBook(bookId, bookToUpdate);
    assertEquals(bookToUpdate.getTitle(), updatedBook.getTitle());
    assertEquals(bookToUpdate.getAuthor(), updatedBook.getAuthor());
    assertEquals(bookToUpdate.getDescription(), updatedBook.getDescription());
  }

  @Test
  @Sql("/books-create.sql")
  void deleteBook() {
    Long bookId = 1L;
    bookService.deleteBook(bookId);
    assertTrue(bookService.findById(bookId).isEmpty());
  }
}
