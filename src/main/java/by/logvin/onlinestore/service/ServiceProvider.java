package by.logvin.onlinestore.service;

import by.logvin.onlinestore.service.impl.UserServiceImpl;

public class ServiceProvider {
    public final static ServiceProvider instance = new ServiceProvider();

    private ServiceProvider(){

    }

    private final UserService userService = new UserServiceImpl();

    public ServiceProvider getInstance(){
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }
}
