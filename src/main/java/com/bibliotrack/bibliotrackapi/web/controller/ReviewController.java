package com.bibliotrack.bibliotrackapi.web.controller;

import com.bibliotrack.bibliotrackapi.model.mapper.ReviewMapper;
import com.bibliotrack.bibliotrackapi.service.ReviewService;
import com.bibliotrack.bibliotrackapi.web.dto.review.ReviewCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.review.ReviewDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/review")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ReviewController {
  private final ReviewService reviewService;
  private final ReviewMapper reviewMapper;

  @PostMapping()
  public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewCreationDto reviewCreationDto) {
    var review = reviewService.createReview(reviewMapper.toReview(reviewCreationDto));
    return new ResponseEntity<>(reviewMapper.toReviewDto(review), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
    return ResponseEntity.of(reviewService.findById(id).map(reviewMapper::toReviewDto));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<ReviewDto>> getReviewByUserId(@PathVariable Long id) {
    return new ResponseEntity<>(
        reviewMapper.toReviewListDto(reviewService.findAllByUserId(id)), HttpStatus.OK);
  }

  @GetMapping("/book/{id}")
  public ResponseEntity<List<ReviewDto>> getReviewByBookId(@PathVariable Long id) {
    return new ResponseEntity<>(
        reviewMapper.toReviewListDto(reviewService.findAllByBookId(id)), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReviewDto> updateReading(
      @PathVariable Long id, @RequestBody ReviewCreationDto dto) {
    var updatedReview = reviewService.updateReview(id, reviewMapper.toReview(dto));
    return ResponseEntity.ok(reviewMapper.toReviewDto(updatedReview));
  }
}
