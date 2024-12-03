package com.bibliotrack.bibliotrackapi.service.impl;

import com.bibliotrack.bibliotrackapi.exeption.BookNotFoundException;
import com.bibliotrack.bibliotrackapi.exeption.ChangeException;
import com.bibliotrack.bibliotrackapi.exeption.UserNotFoundException;
import com.bibliotrack.bibliotrackapi.exeption.WishListNotFoundException;
import com.bibliotrack.bibliotrackapi.model.Wishlist;
import com.bibliotrack.bibliotrackapi.repository.WishlistRepository;
import com.bibliotrack.bibliotrackapi.service.BookService;
import com.bibliotrack.bibliotrackapi.service.UserService;
import com.bibliotrack.bibliotrackapi.service.WishlistService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService {
  private final WishlistRepository wishlistRepository;
  private final BookService bookService;
  private final UserService userService;

  @Override
  public Wishlist createWishlist(Wishlist wishlist) {
    isAvailableWishlist(wishlist);
    return wishlistRepository.save(wishlist);
  }

  @Override
  public List<Wishlist> findAll() {
    return wishlistRepository.findAll();
  }

  @Override
  public Optional<Wishlist> findById(Long id) {
    return wishlistRepository.findById(id);
  }

  @Override
  public List<Wishlist> findAllByUserId(Long id) {
    return wishlistRepository.findAllByUserId(id);
  }

  @Override
  public Wishlist updateCompleteWishlist(Long id, boolean complete) {
    try {
      Wishlist existingWishlist =
          findById(id)
              .orElseThrow(
                  () ->
                      new WishListNotFoundException(
                          String.format("Wishlist with id %s not found", id)));

      existingWishlist.setCompleted(complete);

      wishlistRepository.save(existingWishlist);
      return existingWishlist;
    } catch (Exception e) {
      throw new ChangeException("Error editing wishlist");
    }
  }

  private void isAvailableWishlist(Wishlist wishlist) {
    userService
        .findById(wishlist.getUser().getId())
        .orElseThrow(
            () ->
                new UserNotFoundException(
                    "User with id %s not found".formatted(wishlist.getUser().getId())));
    bookService
        .findById(wishlist.getBook().getId())
        .orElseThrow(
            () ->
                new BookNotFoundException(
                    "Book with id %s not found".formatted(wishlist.getUser().getId())));
  }
}
