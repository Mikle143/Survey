package exception;

import lombok.Getter;
import validator.Error;

import java.util.List;

public class ValidationException extends RuntimeException {
    @Getter
    private final List<Error> errorList;

    public ValidationException(String message, List<Error> errorList) {
        super("ошибка валидации");
        this.errorList = errorList;
    }
}
