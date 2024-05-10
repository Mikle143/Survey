package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder

public class CreateUserDto {
    String name;
    String login;
    String password;
    Integer roleId;

}
