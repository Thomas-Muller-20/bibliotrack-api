package com.bibliotrack.bibliotrackapi.repository;

import com.bibliotrack.bibliotrackapi.model.Wishlist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
  List<Wishlist> findAllByUserId(Long id);
}
