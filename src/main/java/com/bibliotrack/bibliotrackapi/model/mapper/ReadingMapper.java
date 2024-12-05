package com.bibliotrack.bibliotrackapi.model.mapper;

import com.bibliotrack.bibliotrackapi.model.Book;
import com.bibliotrack.bibliotrackapi.model.Reading;
import com.bibliotrack.bibliotrackapi.model.User;
import com.bibliotrack.bibliotrackapi.web.dto.reading.ReadingCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.reading.ReadingDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ReadingMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "book", source = "bookId", qualifiedByName = "mapToBook")
  @Mapping(target = "user", source = "userId", qualifiedByName = "mapToUser")
  Reading toReading(ReadingCreationDto readingCreationDto);

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
  ReadingDto toReadingDto(Reading reading);

  List<ReadingDto> toReadingListDto(List<Reading> allByUserId);
}
