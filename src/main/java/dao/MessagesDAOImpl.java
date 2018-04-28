package dao;

import model.Chat;
import model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAOImpl implements MessagesDAO {

    @Override
    public List<Message> getMessages(Chat chat) {
        List<Message> result = new ArrayList<>();
        String sqlRequest = "SELECT * FROM vermilion.messages WHERE chat_id = " + chat.getId() + ";";

        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long chatId = resultSet.getInt("chat_id");
                long authorId = resultSet.getInt("author_id");
                String content = resultSet.getString("content");
                Timestamp creationTime = resultSet.getTimestamp("creation_time");

                Message message = new Message(chatId, authorId, content);
                message.setId(resultSet.getInt("id"));
                message.setCreationTime(creationTime);

                result.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return result;
    }

    @Override
    public void writeMessage(Message message) {
        String sqlRequest = "INSERT INTO vermilion.messages (chat_id, author_id, content, creation_time)"
                + " VALUES (?, ?, ?, ?);";
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setLong(1, message.getChatId());
            statement.setLong(2, message.getAuthorId());
            statement.setString(3, message.getContent());
            statement.setTimestamp(4, message.getCreationTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void updateMessage(Message message) {

    }

    @Override
    public void deleteMessage(long id) {

    }
}
