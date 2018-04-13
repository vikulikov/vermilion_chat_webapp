package main;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static UserDAO userDAO = new UserDAOImpl();

    public static void main(String[] args) {
        for (User user : userDAO.findAllUsers()) {
            System.out.println(user);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("login", "Hello");

    }
}
