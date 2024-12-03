package com.bibliotrack.bibliotrackapi.web.dto.reading;

import com.bibliotrack.bibliotrackapi.model.variables.ReadingStatus;
import lombok.Data;

@Data
public class ReadingDto {
  private Long id;

  private ReadingStatus status;

  private Long userId;

  private Long bookId;
}
