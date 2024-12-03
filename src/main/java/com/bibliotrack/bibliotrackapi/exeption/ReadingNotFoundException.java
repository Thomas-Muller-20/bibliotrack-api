package com.bibliotrack.bibliotrackapi.exeption;

public class ReadingNotFoundException extends RuntimeException {
  public ReadingNotFoundException(String message) {
    super(message);
  }
}
