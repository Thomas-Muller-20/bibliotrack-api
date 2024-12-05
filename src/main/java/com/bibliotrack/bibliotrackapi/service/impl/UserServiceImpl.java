package com.bibliotrack.bibliotrackapi.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bibliotrack.bibliotrackapi.config.JWTTokenProvider;
import com.bibliotrack.bibliotrackapi.exeption.InvalidPasswordException;
import com.bibliotrack.bibliotrackapi.exeption.UserAlreadyExistsException;
import com.bibliotrack.bibliotrackapi.exeption.UserNotFoundException;
import com.bibliotrack.bibliotrackapi.model.User;
import com.bibliotrack.bibliotrackapi.repository.UserRepository;
import com.bibliotrack.bibliotrackapi.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final JWTTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<DecodedJWT> signIn(String username, String password) {
    var user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        "User with username %s not found".formatted(username)));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new InvalidPasswordException("Invalid password");
    }
    return jwtTokenProvider.toDecodedJWT(
        jwtTokenProvider.generateToken(user.getId(), username, user.getRole()));
  }

  @Override
  public User signUp(User user) {
    existsByUsername(user);
    user.setRole("ROLE_USER");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return user;
  }

  private void existsByUsername(User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new UserAlreadyExistsException(
          "Username %s is already in use".formatted(user.getUsername()));
    }
  }
}
