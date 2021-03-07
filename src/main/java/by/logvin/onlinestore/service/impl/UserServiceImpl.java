package by.logvin.onlinestore.service.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.DAOProvider;
import by.logvin.onlinestore.dao.UserDAO;
import by.logvin.onlinestore.dao.exception.DAOException;
import by.logvin.onlinestore.service.UserService;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.validator.ValidatorProvider;
import by.logvin.onlinestore.service.validator.exception.ValidatorException;

public class UserServiceImpl implements UserService {
    @Override
    public boolean signUp(RegistrationInfo info) throws ServiceException {

        try {
            if(ValidatorProvider.getInstance().getAuthorizationValidator().validate(info)){
                throw new ServiceException("Wrong login or password");
            }
        } catch (ValidatorException e) {
            throw new ServiceException("Validation error", e);
        }

        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean b = false;
        try {
            b = userDAO.signUp(info);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return b;
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        try {
            if(ValidatorProvider.getInstance().getAuthorizationValidator().validate(login, password)){
                throw new ServiceException("Wrong login or password");
            }
        } catch (ValidatorException e) {
            throw new ServiceException("Validation error", e);
        }


        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        User user = null;
        try {
            user = userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
