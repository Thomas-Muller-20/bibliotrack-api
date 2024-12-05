package com.bibliotrack.bibliotrackapi.web.dto.review;

import java.util.Date;
import lombok.Data;

@Data
public class ReviewDto {
  private Long id;

  private int rating;

  private String reviewText;

  private Date createdAt;

  private Date updatedAt;

  private Long userId;

  private Long bookId;
}
