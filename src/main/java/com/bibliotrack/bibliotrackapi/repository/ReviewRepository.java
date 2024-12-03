package com.bibliotrack.bibliotrackapi.repository;

import com.bibliotrack.bibliotrackapi.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findAllByUserId(Long id);

  List<Review> findAllByBookId(Long id);
}
