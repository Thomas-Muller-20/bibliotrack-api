package com.bibliotrack.bibliotrackapi.exeption;

public class WishListNotFoundException extends RuntimeException {
  public WishListNotFoundException(String message) {
    super(message);
  }
}
