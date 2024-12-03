package com.bibliotrack.bibliotrackapi.web.controller;

import com.bibliotrack.bibliotrackapi.model.mapper.UserMapper;
import com.bibliotrack.bibliotrackapi.service.UserService;
import com.bibliotrack.bibliotrackapi.web.dto.user.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping()
  public ResponseEntity<List<UserDto>> allUsers() {
    return new ResponseEntity<>(userMapper.toUserDTOList(userService.getAllUsers()), HttpStatus.OK);
  }
}
