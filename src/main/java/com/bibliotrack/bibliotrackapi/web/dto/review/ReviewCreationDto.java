package com.bibliotrack.bibliotrackapi.web.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ReviewCreationDto {
  @Min(value = 1, message = "Rating must be between 1 and 5")
  @Max(value = 5, message = "Rating must be between 1 and 5")
  private int rating;

  private String reviewText;

  private Long userId;

  private Long bookId;
}
