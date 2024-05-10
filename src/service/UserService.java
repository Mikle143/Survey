package service;

import dao.impl.UserDao;
import dto.CreateUserDto;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CreateUserMapper;
import validator.CreateUserValidator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UserService {
    public static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException("ошибка валидации", validationResult.getErrorList());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getId();

    }

    public static UserService getInstance() {
        return INSTANCE;
    }

}
