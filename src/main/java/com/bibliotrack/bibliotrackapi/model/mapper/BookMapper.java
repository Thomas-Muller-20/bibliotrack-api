package com.bibliotrack.bibliotrackapi.model.mapper;

import com.bibliotrack.bibliotrackapi.model.Book;
import com.bibliotrack.bibliotrackapi.web.dto.book.BookCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.book.BookDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
  @Mapping(target = "reviews", ignore = true)
  @Mapping(target = "readings", ignore = true)
  @Mapping(target = "wishlists", ignore = true)
  @Mapping(target = "id", ignore = true)
  Book toBook(BookCreationDto bookCreationDto);

  BookDto toBookDTO(Book book);

  List<BookDto> toBookDTOList(List<Book> allBooks);
}
