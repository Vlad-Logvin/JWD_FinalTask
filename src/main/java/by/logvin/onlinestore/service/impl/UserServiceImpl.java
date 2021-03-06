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
            if(!ValidatorProvider.getInstance().getAuthorizationValidator().validate(info)){
                throw new ServiceException("Wrong registration information");
            }
        } catch (ValidatorException e) {
            throw new ServiceException("Validation error", e);
        }

        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        boolean isSignUp = false;
        try {
            isSignUp = userDAO.signUp(info);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isSignUp;
    }

    @Override
    public User signIn(String email, String password) throws ServiceException {
        try {
            if(!ValidatorProvider.getInstance().getAuthorizationValidator().validate(email, password)){
                throw new ServiceException("Wrong login or password");
            }
        } catch (ValidatorException e) {
            throw new ServiceException("Validation error", e);
        }


        UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
        User user = null;
        try {
            user = userDAO.signIn(email, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
