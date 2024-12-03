package com.bibliotrack.bibliotrackapi.web.dto.wishlist;

import java.util.Date;
import lombok.Data;

@Data
public class WishlistCreationDto {
  private Long userId;

  private Long bookId;

  private Date dateAdded;

  private int priority;

  private boolean isCompleted;
}
