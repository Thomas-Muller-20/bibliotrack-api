package com.bibliotrack.bibliotrackapi.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "username", unique = true)
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private String role;

  @OneToMany(mappedBy = "user")
  private List<Wishlist> wishlists;

  @OneToMany(mappedBy = "user")
  private List<Reading> readings;

  @OneToMany(mappedBy = "user")
  private List<Review> reviews;
}
