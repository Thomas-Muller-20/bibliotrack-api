package com.bibliotrack.bibliotrackapi.model.variables;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReadingStatus {
  IN_PROGRESS("In Progress"),
  COMPLETED("Completed");

  private final String status;
}
