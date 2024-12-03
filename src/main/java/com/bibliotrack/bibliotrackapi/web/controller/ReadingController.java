package com.bibliotrack.bibliotrackapi.web.controller;

import com.bibliotrack.bibliotrackapi.model.mapper.ReadingMapper;
import com.bibliotrack.bibliotrackapi.model.variables.ReadingStatus;
import com.bibliotrack.bibliotrackapi.service.ReadingService;
import com.bibliotrack.bibliotrackapi.web.dto.reading.ReadingCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.reading.ReadingDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/reading")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ReadingController {
  private final ReadingService readingService;
  private final ReadingMapper readingMapper;

  @PostMapping()
  public ResponseEntity<ReadingDto> createReading(
      @RequestBody ReadingCreationDto readingCreationDto) {
    var reading = readingService.createReading(readingMapper.toReading(readingCreationDto));
    return new ResponseEntity<>(readingMapper.toReadingDto(reading), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReadingDto> getReadingById(@PathVariable Long id) {
    return ResponseEntity.of(readingService.findById(id).map(readingMapper::toReadingDto));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<ReadingDto>> getReadingByUserId(@PathVariable Long id) {
    return new ResponseEntity<>(
        readingMapper.toReadingListDto(readingService.findAllByUserId(id)), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReadingDto> updateReading(
      @PathVariable Long id, @RequestBody ReadingStatus status) {
    var updatedWishlist = readingService.updateReadingStatus(id, status);
    return ResponseEntity.ok(readingMapper.toReadingDto(updatedWishlist));
  }
}
