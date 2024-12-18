package com.bibliotrack.bibliotrackapi.unit.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.bibliotrack.bibliotrackapi.model.Book;
import com.bibliotrack.bibliotrackapi.model.mapper.BookMapper;
import com.bibliotrack.bibliotrackapi.service.BookService;
import com.bibliotrack.bibliotrackapi.web.controller.BookController;
import com.bibliotrack.bibliotrackapi.web.dto.book.BookCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.book.BookDto;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class BookControllerTest {

  @Mock private BookService bookService;

  @Mock private BookMapper bookMapper;

  @InjectMocks private BookController bookController;

  private Book testBook;
  private BookDto testBookDto;
  private BookCreationDto bookCreationDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    testBook = new Book();
    testBook.setId(1L);
    testBook.setTitle("Test Title");
    testBook.setAuthor("Test Author");

    testBookDto = new BookDto();
    testBookDto.setId(1L);
    testBookDto.setTitle("Test Title");
    testBookDto.setAuthor("Test Author");

    bookCreationDto = new BookCreationDto();
    bookCreationDto.setTitle("Test Title");
    bookCreationDto.setAuthor("Test Author");
  }

  @Test
  void createBook_ShouldReturnCreatedBook() {
    when(bookMapper.toBook(bookCreationDto)).thenReturn(testBook);
    when(bookService.createBook(testBook)).thenReturn(testBook);
    when(bookMapper.toBookDTO(testBook)).thenReturn(testBookDto);

    ResponseEntity<BookDto> response = bookController.createBook(bookCreationDto);

    assertThat(response.getStatusCodeValue()).isEqualTo(201);
    assertThat(response.getBody()).isEqualTo(testBookDto);
    verify(bookService).createBook(testBook);
    verify(bookMapper).toBookDTO(testBook);
  }

  @Test
  void getBookById_ShouldReturnBook_WhenBookExists() {
    when(bookService.findById(1L)).thenReturn(Optional.of(testBook));
    when(bookMapper.toBookDTO(testBook)).thenReturn(testBookDto);

    ResponseEntity<BookDto> response = bookController.getBookById(1L);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody()).isEqualTo(testBookDto);
    verify(bookService).findById(1L);
  }

  @Test
  void getBookById_ShouldReturnNotFound_WhenBookDoesNotExist() {
    when(bookService.findById(1L)).thenReturn(Optional.empty());

    ResponseEntity<BookDto> response = bookController.getBookById(1L);

    assertThat(response.getStatusCodeValue()).isEqualTo(404);
    assertThat(response.getBody()).isNull();
  }

  @Test
  void getAllBooks_ShouldReturnListOfBooks() {
    when(bookService.findAll()).thenReturn(List.of(testBook));
    when(bookMapper.toBookDTOList(List.of(testBook))).thenReturn(List.of(testBookDto));

    ResponseEntity<List<BookDto>> response = bookController.getAllBooks();

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody()).containsExactly(testBookDto);
    verify(bookService).findAll();
  }

  @Test
  void updateBookById_ShouldReturnUpdatedBook() {
    when(bookMapper.toBook(bookCreationDto)).thenReturn(testBook);
    when(bookService.updateBook(1L, testBook)).thenReturn(testBook);
    when(bookMapper.toBookDTO(testBook)).thenReturn(testBookDto);

    ResponseEntity<BookDto> response = bookController.updateBookById(1L, bookCreationDto);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody()).isEqualTo(testBookDto);
    verify(bookService).updateBook(1L, testBook);
    verify(bookMapper).toBookDTO(testBook);
  }

  @Test
  void deleteBookById_ShouldReturnNoContent() {
    doNothing().when(bookService).deleteBook(1L);

    ResponseEntity<Void> response = bookController.deleteBookById(1L);

    assertThat(response.getStatusCodeValue()).isEqualTo(204);
    verify(bookService).deleteBook(1L);
  }
}
