package com.bibliotrack.bibliotrackapi.web.controller;

import com.bibliotrack.bibliotrackapi.model.mapper.JWTTokenMapper;
import com.bibliotrack.bibliotrackapi.model.mapper.UserMapper;
import com.bibliotrack.bibliotrackapi.service.UserService;
import com.bibliotrack.bibliotrackapi.web.controller.auth.JWTToken;
import com.bibliotrack.bibliotrackapi.web.dto.user.UserCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.user.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService userService;
  private final UserMapper userMapper;
  private final JWTTokenMapper jwtTokenMapper;

  @PostMapping("/sign-up")
  public ResponseEntity<UserDto> signUp(@RequestBody @Valid UserCreationDto userDto) {
    var newUser = userService.signUp(userMapper.toUser(userDto));
    return new ResponseEntity<>(userMapper.toUserDTO(newUser), HttpStatus.CREATED);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<JWTToken> signIn(@RequestBody @Valid UserCreationDto userDto) {
    return ResponseEntity.of(
        userService
            .signIn(userDto.getUsername(), userDto.getPassword())
            .map(jwtTokenMapper::toPayload));
  }
}
