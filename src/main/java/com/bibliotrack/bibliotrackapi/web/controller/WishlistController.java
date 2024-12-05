package com.bibliotrack.bibliotrackapi.web.controller;

import com.bibliotrack.bibliotrackapi.model.mapper.WishlistMapper;
import com.bibliotrack.bibliotrackapi.service.WishlistService;
import com.bibliotrack.bibliotrackapi.web.dto.wishlist.WishlistCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.wishlist.WishlistDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/wishlist")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class WishlistController {
  private final WishlistService wishlistService;
  private final WishlistMapper wishlistMapper;

  @PostMapping()
  public ResponseEntity<WishlistDto> createWishlist(
      @RequestBody WishlistCreationDto wishlistCreationDto) {
    var wishlist = wishlistService.createWishlist(wishlistMapper.toWishlist(wishlistCreationDto));
    return new ResponseEntity<>(wishlistMapper.toWishlistDto(wishlist), HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<List<WishlistDto>> getWishlists() {
    return new ResponseEntity<>(
        wishlistMapper.toWishlistDtoList(wishlistService.findAll()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<WishlistDto> getWishlistById(@PathVariable Long id) {
    return ResponseEntity.of(wishlistService.findById(id).map(wishlistMapper::toWishlistDto));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<WishlistDto>> getWishlistByUserId(@PathVariable Long id) {
    return new ResponseEntity<>(
        wishlistMapper.toWishlistDtoList(wishlistService.findAllByUserId(id)), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<WishlistDto> updateCompleteWishlist(
      @PathVariable Long id, @RequestBody boolean complete) {
    var updatedWishlist = wishlistService.updateCompleteWishlist(id, complete);
    return ResponseEntity.ok(wishlistMapper.toWishlistDto(updatedWishlist));
  }
}
