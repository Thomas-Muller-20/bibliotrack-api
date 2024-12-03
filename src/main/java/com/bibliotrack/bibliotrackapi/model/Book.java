package com.bibliotrack.bibliotrackapi.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Books")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  private String title;
  private String author;
  private String description;

  @OneToMany(mappedBy = "book")
  private List<Wishlist> wishlists;

  @OneToMany(mappedBy = "book")
  private List<Reading> readings;

  @OneToMany(mappedBy = "book")
  private List<Review> reviews;
}
