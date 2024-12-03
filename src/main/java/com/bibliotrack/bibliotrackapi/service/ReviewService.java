package com.bibliotrack.bibliotrackapi.service;

import com.bibliotrack.bibliotrackapi.model.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
  Review createReview(Review review);

  Optional<Review> findById(Long id);

  List<Review> findAllByUserId(Long id);

  List<Review> findAllByBookId(Long id);

  Review updateReview(Long id, Review review);
}
