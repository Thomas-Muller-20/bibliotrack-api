package com.bibliotrack.bibliotrackapi.service;

import com.bibliotrack.bibliotrackapi.model.Reading;
import com.bibliotrack.bibliotrackapi.model.variables.ReadingStatus;
import java.util.List;
import java.util.Optional;

public interface ReadingService {
  Reading createReading(Reading reading);

  List<Reading> findAllByUserId(Long id);

  Optional<Reading> findById(Long id);

  Reading updateReadingStatus(Long id, ReadingStatus status);
}
