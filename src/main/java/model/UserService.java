package model;

import dao.UserDAO;
import dao.UserDAOImpl;

import java.util.HashMap;
import java.util.Map;

//  Singleton
public class UserService {
    private static UserService instance;
    private final Map<String, User> sessionIdToUser;
    private final UserDAO userDAO;

    private UserService() {
        sessionIdToUser = new HashMap<>();
        userDAO = new UserDAOImpl();
    }

    public static UserService getInstance() {
        if (instance == null) instance = new UserService();
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public Map<String, User> getSessionIdToUser() {
        return sessionIdToUser;
    }

}
