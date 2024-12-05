package com.bibliotrack.bibliotrackapi.model.mapper;

import com.bibliotrack.bibliotrackapi.model.Book;
import com.bibliotrack.bibliotrackapi.model.Review;
import com.bibliotrack.bibliotrackapi.model.User;
import com.bibliotrack.bibliotrackapi.web.dto.review.ReviewCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.review.ReviewDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "book", source = "bookId", qualifiedByName = "mapToBook")
  @Mapping(target = "user", source = "userId", qualifiedByName = "mapToUser")
  Review toReview(ReviewCreationDto reviewCreationDto);

  @Named("mapToBook")
  static Book mapToBook(Long id) {
    if (id == null) {
      return null;
    }
    Book book = new Book();
    book.setId(id);
    return book;
  }

  @Named("mapToUser")
  static User mapToUser(Long id) {
    if (id == null) {
      return null;
    }
    User user = new User();
    user.setId(id);
    return user;
  }

  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "bookId", source = "book.id")
  ReviewDto toReviewDto(Review review);

  List<ReviewDto> toReviewListDto(List<Review> allByBookId);
}
