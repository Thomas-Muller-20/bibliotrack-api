package com.bibliotrack.bibliotrackapi.service;

import com.bibliotrack.bibliotrackapi.model.Wishlist;
import java.util.List;
import java.util.Optional;

public interface WishlistService {
  Wishlist createWishlist(Wishlist wishlist);

  List<Wishlist> findAll();

  Optional<Wishlist> findById(Long id);

  List<Wishlist> findAllByUserId(Long id);

  Wishlist updateCompleteWishlist(Long id, boolean complete);
}
