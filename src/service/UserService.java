package service;

import dao.impl.UserDao;
import dto.CreateUserDto;
import dto.UserDto;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CreateUserMapper;
import mapper.UserMapper;
import validator.CreateUserValidator;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UserService {
    public static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper=UserMapper.getInstance();

    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException("ошибка валидации", validationResult.getErrorList());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getId();

    }
    public void delete(Integer userId){
        userDao.delete(userId);
    }

    public Optional<UserDto>login(String login, String password){
        return userDao.findByLoginPassword(login, password)
                .map(userMapper::mapFrom);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

}
