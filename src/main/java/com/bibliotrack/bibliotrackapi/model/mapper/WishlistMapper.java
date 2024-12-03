package com.bibliotrack.bibliotrackapi.model.mapper;

import com.bibliotrack.bibliotrackapi.model.Book;
import com.bibliotrack.bibliotrackapi.model.User;
import com.bibliotrack.bibliotrackapi.model.Wishlist;
import com.bibliotrack.bibliotrackapi.web.dto.wishlist.WishlistCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.wishlist.WishlistDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "book", source = "bookId", qualifiedByName = "mapToBook")
  @Mapping(target = "user", source = "userId", qualifiedByName = "mapToUser")
  Wishlist toWishlist(WishlistCreationDto wishlistCreationDto);

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
  WishlistDto toWishlistDto(Wishlist wishlist);

  List<WishlistDto> toWishlistDtoList(List<Wishlist> all);
}
