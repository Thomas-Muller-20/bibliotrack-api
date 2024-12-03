package com.bibliotrack.bibliotrackapi.exeption;

public class InvalidPasswordException extends RuntimeException {
  public InvalidPasswordException(String message) {
    super(message);
  }
}
