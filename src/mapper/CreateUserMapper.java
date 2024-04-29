package mapper;

import dto.CreateUserDto;
import entity.UserEntity;

public class CreateUserMapper implements Mapper<CreateUserDto, UserEntity> {

    public static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public UserEntity mapFrom(CreateUserDto object) {
        return UserEntity.builder()
                .name(object.getName())
                .login(object.getLogin())
                .password(object.getPassword())
                .roleId(object.getRoleId())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
