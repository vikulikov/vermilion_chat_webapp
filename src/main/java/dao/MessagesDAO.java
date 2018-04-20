package dao;

import model.Chat;
import model.Message;

import java.util.List;

public interface MessagesDAO {
    List<Message> getMessages(Chat chat);
    void writeMessage(Message message);
    void updateMessage(Message message);
    void deleteMessage(long id);
}
