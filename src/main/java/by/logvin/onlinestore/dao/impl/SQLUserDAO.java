package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.UserDAO;
import by.logvin.onlinestore.dao.exception.DAOException;

public class SQLUserDAO implements UserDAO {
    @Override
    public boolean registration(RegistrationInfo info) throws DAOException {
        return false;
    }

    @Override
    public User signIn(String login, String password) throws DAOException {
        return null;
    }
}
