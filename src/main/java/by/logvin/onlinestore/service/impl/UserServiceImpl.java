package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.service.UserService;
import by.logvin.onlinestore.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public boolean signUp(RegistrationInfo info) throws ServiceException {
        return false;
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        return null;
    }
}
