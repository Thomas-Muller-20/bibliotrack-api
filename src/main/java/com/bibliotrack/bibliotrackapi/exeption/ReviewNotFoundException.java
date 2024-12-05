package com.bibliotrack.bibliotrackapi.exeption;

public class ReviewNotFoundException extends RuntimeException {
  public ReviewNotFoundException(String message) {
    super(message);
  }
}
