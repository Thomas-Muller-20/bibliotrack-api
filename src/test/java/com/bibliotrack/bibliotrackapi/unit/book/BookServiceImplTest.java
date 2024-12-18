package com.bibliotrack.bibliotrackapi.unit.book;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bibliotrack.bibliotrackapi.exeption.BookNotFoundException;
import com.bibliotrack.bibliotrackapi.exeption.ChangeException;
import com.bibliotrack.bibliotrackapi.model.Book;
import com.bibliotrack.bibliotrackapi.repository.BookRepository;
import com.bibliotrack.bibliotrackapi.service.impl.BookServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookServiceImpl bookService;

  @Test
  void createBook() {
    Book book = new Book();
    when(bookRepository.save(book)).thenReturn(book);
    Book result = bookService.createBook(book);
    assertNotNull(result);
    verify(bookRepository, times(1)).save(book);
  }

  @Test
  void findById_Found() {
    Long id = 1L;
    Book book = new Book();
    when(bookRepository.findById(id)).thenReturn(Optional.of(book));
    Optional<Book> result = bookService.findById(id);
    assertTrue(result.isPresent());
    assertEquals(book, result.get());
    verify(bookRepository, times(1)).findById(id);
  }

  @Test
  void findById_NotFound() {
    Long id = 1L;
    when(bookRepository.findById(id)).thenReturn(Optional.empty());
    Optional<Book> result = bookService.findById(id);
    assertFalse(result.isPresent());
    verify(bookRepository, times(1)).findById(id);
  }

  @Test
  void findByTitle() {
    String title = "Test Title";
    Book book = new Book();
    when(bookRepository.findByTitle(title)).thenReturn(Optional.of(book));
    Optional<Book> result = bookService.findByTitle(title);
    assertTrue(result.isPresent());
    assertEquals(book, result.get());
    verify(bookRepository, times(1)).findByTitle(title);
  }

  @Test
  void findByAuthor() {
    String author = "Test Author";
    Book book = new Book();
    when(bookRepository.findByAuthor(author)).thenReturn(Optional.of(book));
    Optional<Book> result = bookService.findByAuthor(author);
    assertTrue(result.isPresent());
    assertEquals(book, result.get());
    verify(bookRepository, times(1)).findByAuthor(author);
  }

  @Test
  void findAll() {
    List<Book> books = List.of(new Book(), new Book());
    when(bookRepository.findAll()).thenReturn(books);
    List<Book> result = bookService.findAll();
    assertNotNull(result);
    assertEquals(2, result.size());
    verify(bookRepository, times(1)).findAll();
  }

  @Test
  void updateBook_Success() {
    Long id = 1L;
    Book book = new Book();
    Book existingBook = new Book();
    when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
    when(bookRepository.save(existingBook)).thenReturn(existingBook);
    Book result = bookService.updateBook(id, book);
    assertNotNull(result);
    verify(bookRepository, times(1)).findById(id);
    verify(bookRepository, times(1)).save(existingBook);
  }

  @Test
  void updateBook_NotFound() {
    Long id = 1L;
    Book book = new Book();
    when(bookRepository.findById(id)).thenReturn(Optional.empty());
    assertThrows(ChangeException.class, () -> bookService.updateBook(id, book));
    verify(bookRepository, times(1)).findById(id);
    verify(bookRepository, never()).save(any(Book.class));
  }

  @Test
  void updateBook_Exception() {
    Long id = 1L;
    Book book = new Book();
    when(bookRepository.findById(id)).thenThrow(RuntimeException.class);
    assertThrows(ChangeException.class, () -> bookService.updateBook(id, book));
    verify(bookRepository, times(1)).findById(id);
  }

  @Test
  void deleteBook() {
    Long id = 1L;
    doNothing().when(bookRepository).deleteById(id);
    bookService.deleteBook(id);
    verify(bookRepository, times(1)).deleteById(id);
  }
}
