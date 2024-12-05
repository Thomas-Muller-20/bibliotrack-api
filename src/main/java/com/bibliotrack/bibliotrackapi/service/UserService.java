package com.bibliotrack.bibliotrackapi.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bibliotrack.bibliotrackapi.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
  Optional<User> findById(Long id);

  Optional<DecodedJWT> signIn(String username, String password);

  User signUp(User user);

  List<User> getAllUsers();
}
