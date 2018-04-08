package main;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static UserDAO userDAO = new UserDAOImpl();

    public static void main(String[] args) {
        for (User user : userDAO.findAllUsers()) {
            System.out.println(user);
        }
    }



}
