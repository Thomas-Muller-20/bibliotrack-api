package com.bibliotrack.bibliotrackapi.exeption;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String message) {
    super(message);
  }
}
