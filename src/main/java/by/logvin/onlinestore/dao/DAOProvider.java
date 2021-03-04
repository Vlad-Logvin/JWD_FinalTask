package by.logvin.onlinestore.dao;

import by.logvin.onlinestore.dao.impl.SQLUserDAO;

public final class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private DAOProvider(){

    }

    private final UserDAO userDAO = new SQLUserDAO();

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
