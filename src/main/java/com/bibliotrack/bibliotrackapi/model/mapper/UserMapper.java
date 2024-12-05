package com.bibliotrack.bibliotrackapi.model.mapper;

import com.bibliotrack.bibliotrackapi.model.User;
import com.bibliotrack.bibliotrackapi.web.dto.user.UserCreationDto;
import com.bibliotrack.bibliotrackapi.web.dto.user.UserDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toUserDTO(User user);

  @Mapping(target = "reviews", ignore = true)
  @Mapping(target = "readings", ignore = true)
  @Mapping(target = "wishlists", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "id", ignore = true)
  User toUser(UserCreationDto userDto);

  List<UserDto> toUserDTOList(List<User> allUsers);
}
