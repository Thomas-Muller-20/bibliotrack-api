package com.bibliotrack.bibliotrackapi.exeption;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String message) {
    super(message);
  }
}
