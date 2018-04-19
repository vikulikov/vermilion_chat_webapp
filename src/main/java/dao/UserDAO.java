package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAllUsers();
    List<User> searchUsersByName(String name);
    User searchUserByEmail(String email);
    User searchUserByLogin(String login);
    void addUser(User user);
    void updateUserData(User user);
    void deleteUser(Long id);
}
