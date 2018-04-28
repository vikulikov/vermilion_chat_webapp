package dao;

import model.Chat;
import model.User;

import java.util.List;

public interface ChatDAO {
    Chat getChatById(long id);
    Chat getChat(User userOne, User userTwo);
    List<Chat> getUserChats(User user);
    void initChat(Chat chat);
    void deleteChat(long id);
}
