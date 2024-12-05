package com.bibliotrack.bibliotrackapi.service.impl;

import com.bibliotrack.bibliotrackapi.exeption.*;
import com.bibliotrack.bibliotrackapi.model.Reading;
import com.bibliotrack.bibliotrackapi.model.variables.ReadingStatus;
import com.bibliotrack.bibliotrackapi.repository.ReadingRepository;
import com.bibliotrack.bibliotrackapi.service.BookService;
import com.bibliotrack.bibliotrackapi.service.ReadingService;
import com.bibliotrack.bibliotrackapi.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReadingServiceImpl implements ReadingService {
  private final ReadingRepository readingRepository;
  private final BookService bookService;
  private final UserService userService;

  @Override
  public Reading createReading(Reading reading) {
    isAvailableReading(reading);
    return readingRepository.save(reading);
  }

  @Override
  public List<Reading> findAllByUserId(Long id) {
    return readingRepository.findAllByUserId(id);
  }

  @Override
  public Optional<Reading> findById(Long id) {
    return readingRepository.findById(id);
  }

  @Override
  public Reading updateReadingStatus(Long id, ReadingStatus status) {
    try {
      Reading existingReading =
          findById(id)
              .orElseThrow(
                  () ->
                      new ReadingNotFoundException(
                          String.format("Reading with id %s not found", id)));

      existingReading.setStatus(status);

      readingRepository.save(existingReading);
      return existingReading;
    } catch (Exception e) {
      throw new ChangeException("Error editing wishlist");
    }
  }

  private void isAvailableReading(Reading reading) {
    userService
        .findById(reading.getUser().getId())
        .orElseThrow(
            () ->
                new UserNotFoundException(
                    "User with id %s not found".formatted(reading.getUser().getId())));
    bookService
        .findById(reading.getBook().getId())
        .orElseThrow(
            () ->
                new BookNotFoundException(
                    "Book with id %s not found".formatted(reading.getUser().getId())));
  }
}
