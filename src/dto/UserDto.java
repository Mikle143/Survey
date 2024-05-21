package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    String login;
    Integer roleId;
}
