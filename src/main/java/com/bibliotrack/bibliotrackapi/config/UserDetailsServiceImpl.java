package com.bibliotrack.bibliotrackapi.config;

import com.bibliotrack.bibliotrackapi.exeption.UserNotFoundException;
import com.bibliotrack.bibliotrackapi.model.User;
import com.bibliotrack.bibliotrackapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
    var user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        "User with username %s not found".formatted(username)));
    return new UserDetailsImpl((User) user);
  }
}
