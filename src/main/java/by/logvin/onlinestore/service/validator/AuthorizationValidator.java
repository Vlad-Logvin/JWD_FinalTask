package by.logvin.onlinestore.service.validator;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.service.validator.exception.ValidatorException;

import java.util.Map;

public interface AuthorizationValidator {
    boolean validate(String login, String password) throws ValidatorException;
    boolean validate(RegistrationInfo registrationInfo) throws ValidatorException;
}
