package com.bibliotrack.bibliotrackapi.integration.web;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bibliotrack.bibliotrackapi.integration.testcontainer.TestContainersConfig;
import com.bibliotrack.bibliotrackapi.repository.BookRepository;
import com.bibliotrack.bibliotrackapi.web.dto.book.BookCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ContextConfiguration(classes = TestContainersConfig.class)
@AutoConfigureMockMvc
@WithMockUser(
    username = "admin",
    roles = {"ADMIN"})
public class BookControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private BookRepository repository;

  @AfterEach
  public void cleanAll() {
    repository.deleteAll();
  }

  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  void shouldCreateNewBookWhenBookIsValid() throws Exception {
    var book = getBook("Effective Java", "Joshua Bloch", "978-0134685991");
    mapper.findAndRegisterModules();
    var json = mapper.writeValueAsString(book);
    var result =
        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(json));

    result.andExpectAll(status().isCreated());
  }

  @Test
  @Sql("/books-create.sql")
  void shouldFindBookByIdIfNotExist() throws Exception {
    var id = 999L;

    var result = mockMvc.perform(get("/books/id/{id}", id));

    result.andExpectAll(status().isNotFound());
  }

  @Test
  @Sql("/books-create.sql")
  void shouldFindBooksByTitle() throws Exception {
    var title = "Book Two";

    var result = mockMvc.perform(get("/books/title/{title}", title));

    result.andExpectAll(status().isOk(), jsonPath("$.title").value(title));
  }

  @Test
  @Sql("/books-create.sql")
  void shouldFindBooksByAuthor() throws Exception {
    var author = "Author One";

    var result = mockMvc.perform(get("/books/author/{author}", author));

    result.andExpectAll(status().isOk(), jsonPath("$.author").value(author));
  }

  @Test
  void shouldReturnAllBooks() throws Exception {
    var result = mockMvc.perform(get("/books"));

    result.andExpectAll(status().isOk(), jsonPath("$").isArray(), jsonPath("$", hasSize(0)));
  }

  @Test
  @Sql("/books-create.sql")
  void shouldDeleteBookById() throws Exception {
    var id = 1L;

    var result = mockMvc.perform(delete("/books/{id}", id));

    result.andExpectAll(status().isNoContent());
    Assertions.assertThat(repository.existsById(id)).isFalse();
  }

  @Test
  @Sql("/books-create.sql")
  void shouldUpdateBook() throws Exception {
    var id = 1L;
    var updatedBook = getBook("Clean Code", "Robert C. Martin", "978-0132350884");
    mapper.findAndRegisterModules();
    var json = mapper.writeValueAsString(updatedBook);
    var result =
        mockMvc.perform(
            put("/books/{id}", id).contentType(MediaType.APPLICATION_JSON).content(json));

    result.andExpectAll(
        status().isOk(),
        jsonPath("$.title").value(updatedBook.getTitle()),
        jsonPath("$.author").value(updatedBook.getAuthor()),
        jsonPath("$.description").value(updatedBook.getDescription()));
  }

  private BookCreationDto getBook(String title, String author, String description) {
    return new BookCreationDto(title, author, description);
  }
}
