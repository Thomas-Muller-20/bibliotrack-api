package com.bibliotrack.bibliotrackapi.web.controller;

import com.bibliotrack.bibliotrackapi.model.mapper.BookMapper;
import com.bibliotrack.bibliotrackapi.service.BookService;
import com.bibliotrack.bibliotrackapi.web.dto.book.BookCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.book.BookDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/books")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class BookController {
  private final BookService bookService;
  private final BookMapper bookMapper;

  @PostMapping()
  public ResponseEntity<BookDto> createBook(@RequestBody BookCreationDto bookCreationDto) {
    var newBook = bookService.createBook(bookMapper.toBook(bookCreationDto));
    return new ResponseEntity<>(bookMapper.toBookDTO(newBook), HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
    return ResponseEntity.of(bookService.findById(id).map(bookMapper::toBookDTO));
  }

  @GetMapping("/title/{title}")
  public ResponseEntity<BookDto> getBookByTitle(@PathVariable("title") String title) {
    return ResponseEntity.of(bookService.findByTitle(title).map(bookMapper::toBookDTO));
  }

  @GetMapping("/author/{author}")
  public ResponseEntity<BookDto> getBookByAuthor(@PathVariable("author") String author) {
    return ResponseEntity.of(bookService.findByAuthor(author).map(bookMapper::toBookDTO));
  }

  @GetMapping()
  public ResponseEntity<List<BookDto>> getAllBooks() {
    return new ResponseEntity<>(bookMapper.toBookDTOList(bookService.findAll()), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDto> updateBookById(
      @PathVariable("id") Long id, @RequestBody BookCreationDto bookCreationDto) {
    var updatedBook = bookService.updateBook(id, bookMapper.toBook(bookCreationDto));
    return ResponseEntity.ok(bookMapper.toBookDTO(updatedBook));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBookById(@PathVariable("id") Long id) {
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }
}
