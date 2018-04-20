package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    private List<User> getUsersWithRequest(String sqlRequest) {
        List<User> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getInt("gender"),
                        resultSet.getString("birthday"));

                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return result;

    }

    @Override
    public List<User> findAllUsers() {
        String sqlRequest = "SELECT * FROM vermilion.users;";
        return getUsersWithRequest(sqlRequest);
    }

    @Override
    public List<User> searchUsersByName(String name) {
        String sqlRequest = "SELECT * FROM vermilion.users WHERE name LIKE '" + name.trim() + "%';";
        return getUsersWithRequest(sqlRequest);
    }

    @Override
    public User searchUserByEmail(String email) {
        String sqlRequest = "SELECT * FROM vermilion.users WHERE email = '" + email.trim() + "';";
        List<User> users = getUsersWithRequest(sqlRequest);

        if (users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public User searchUserByLogin(String login) {
        String sqlRequest = "SELECT * FROM vermilion.users WHERE login = '" + login.trim() + "';";
        List<User> users = getUsersWithRequest(sqlRequest);

        if (users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public void addUser(User user) {
        String sqlRequest = "INSERT INTO vermilion.users (name, last_name, email, login, password, gender, birthday)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?);";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getGender());
            statement.setString(7, user.getBirthday());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void updateUserData(User user) {

    }

    @Override
    public void deleteUser(Long id) {
        String sqlRequest = "DELETE FROM vermilion.users WHERE id = " + id + ";";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }


}
