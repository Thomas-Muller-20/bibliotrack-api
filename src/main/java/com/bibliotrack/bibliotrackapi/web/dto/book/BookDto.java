package com.bibliotrack.bibliotrackapi.web.dto.book;

import lombok.Data;

@Data
public class BookDto {
  private Long id;
  private String title;
  private String author;
  private String description;
}
