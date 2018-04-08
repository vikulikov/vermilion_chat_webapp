package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    public List<User> findAllUsers();

    public List<User> searchUsersByName(String name);

    public User searchUserByEmail(String email);

    public void addUser(User user);

    public void updateUserData(User user);

    public void deleteUser(Long id);

}
