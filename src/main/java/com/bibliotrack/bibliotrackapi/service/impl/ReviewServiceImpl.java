package com.bibliotrack.bibliotrackapi.service.impl;

import com.bibliotrack.bibliotrackapi.exeption.BookNotFoundException;
import com.bibliotrack.bibliotrackapi.exeption.ChangeException;
import com.bibliotrack.bibliotrackapi.exeption.ReviewNotFoundException;
import com.bibliotrack.bibliotrackapi.exeption.UserNotFoundException;
import com.bibliotrack.bibliotrackapi.model.Review;
import com.bibliotrack.bibliotrackapi.repository.ReviewRepository;
import com.bibliotrack.bibliotrackapi.service.BookService;
import com.bibliotrack.bibliotrackapi.service.ReviewService;
import com.bibliotrack.bibliotrackapi.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  private final BookService bookService;
  private final UserService userService;

  @Override
  public Review createReview(Review review) {
    isAvailableReading(review);
    return reviewRepository.save(review);
  }

  @Override
  public Optional<Review> findById(Long id) {
    return reviewRepository.findById(id);
  }

  @Override
  public List<Review> findAllByUserId(Long id) {
    return reviewRepository.findAllByUserId(id);
  }

  @Override
  public List<Review> findAllByBookId(Long id) {
    return reviewRepository.findAllByBookId(id);
  }

  @Override
  public Review updateReview(Long id, Review review) {
    try {
      Review existingReview =
          findById(id)
              .orElseThrow(
                  () ->
                      new ReviewNotFoundException(String.format("Book with id %s not found", id)));

      existingReview.setReviewText(review.getReviewText());
      existingReview.setRating(review.getRating());
      existingReview.setUpdatedAt(new Date());
      reviewRepository.save(existingReview);
      return existingReview;
    } catch (Exception e) {
      throw new ChangeException("Error editing book");
    }
  }

  private void isAvailableReading(Review review) {
    userService
        .findById(review.getUser().getId())
        .orElseThrow(
            () ->
                new UserNotFoundException(
                    "User with id %s not found".formatted(review.getUser().getId())));
    bookService
        .findById(review.getBook().getId())
        .orElseThrow(
            () ->
                new BookNotFoundException(
                    "Book with id %s not found".formatted(review.getUser().getId())));
  }
}
