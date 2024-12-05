package com.bibliotrack.bibliotrackapi.repository;

import com.bibliotrack.bibliotrackapi.model.Reading;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
  List<Reading> findAllByUserId(Long id);
}
