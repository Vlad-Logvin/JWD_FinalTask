package by.logvin.onlinestore.service.validator.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.service.validator.AuthorizationValidator;
import by.logvin.onlinestore.service.validator.exception.ValidatorException;

import java.util.regex.Pattern;

public class AuthorizationValidatorImpl implements AuthorizationValidator {

    @Override
    public boolean validate(String login, String password) throws ValidatorException {
        return false;
    }

    @Override
    public boolean validate(RegistrationInfo registrationInfo) throws ValidatorException {

        return false;
    }
}
