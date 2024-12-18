package com.bibliotrack.bibliotrackapi.web.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreationDto {
  @NotBlank(message = "Specify title")
  private String title;

  @NotBlank(message = "Specify author")
  private String author;

  @NotBlank(message = "Specify description")
  @Size(min = 6, message = "Enter at least 6 characters")
  private String description;
}
