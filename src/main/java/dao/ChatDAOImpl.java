package dao;

import model.Chat;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatDAOImpl implements ChatDAO {

    @Override
    public Chat getChatById(long id) {
        Chat chat = null;
        String sqlRequest = "SELECT * FROM vermilion.chats WHERE id = ?;";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long chatId = resultSet.getLong("id");
                long userTwoId = resultSet.getLong("user_two_id");
                long userOneId = resultSet.getLong("user_one_id");
                chat = new Chat(userOneId, userTwoId);
                chat.setId(chatId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return chat;
    }

    @Override
    public Chat getChat(User userOne, User userTwo) {
        long firstUserId = userOne.getId();
        long secondUserId = userTwo.getId();

        if (firstUserId > secondUserId) {
            long tempId = firstUserId;
            firstUserId = secondUserId;
            secondUserId = tempId;
        }

        Chat chat = null;
        String sqlRequest = "SELECT * FROM vermilion.chats WHERE user_one_id = ? AND user_two_id = ?;";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setLong(1, firstUserId);
            statement.setLong(2, secondUserId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long userOneId = resultSet.getLong("user_one_id");
                long userTwoId = resultSet.getLong("user_two_id");

                chat = new Chat(userOneId, userTwoId);
                chat.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return chat;
    }

    @Override
    public List<Chat> getUserChats(User user) {
        List<Chat> result = new ArrayList<>();
        String sqlRequest = "SELECT * FROM vermilion.chats WHERE user_one_id = " + user.getId()
                + " OR user_two_id = " + user.getId() + ";";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long userOneId = resultSet.getLong("user_one_id");
                long userTwoId = resultSet.getLong("user_two_id");

                Chat chat = new Chat(userOneId, userTwoId);
                chat.setId(id);

                result.add(chat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public void initChat(Chat chat) {
        long firstUserId = chat.getUserOneId();
        long secondUserId = chat.getUserTwoId();

        if (firstUserId > secondUserId) {
            long tempId = firstUserId;
            firstUserId = secondUserId;
            secondUserId = tempId;
        }
        String sqlRequest = "INSERT INTO vermilion.chats (user_one_id, user_two_id) VALUES "
                + " (?, ?);";
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setLong(1, firstUserId);
            statement.setLong(2, secondUserId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void deleteChat(long id) {

    }
}
