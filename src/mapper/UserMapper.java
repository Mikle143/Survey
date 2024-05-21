package mapper;

import dto.UserDto;
import entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<UserEntity, UserDto>{
    private static final UserMapper INSTANCE=new UserMapper();
    @Override
    public UserDto mapFrom(UserEntity object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .login(object.getLogin())
                .roleId(object.getRoleId())
                .build();
    }
    public static UserMapper getInstance(){
        return INSTANCE;
    }
}
