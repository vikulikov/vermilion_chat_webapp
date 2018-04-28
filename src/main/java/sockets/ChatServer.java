package sockets;

import dao.ChatDAO;
import dao.ChatDAOImpl;
import dao.MessagesDAO;
import dao.MessagesDAOImpl;
import model.Chat;
import model.JSONHandler;
import model.Message;
import model.User;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/messages/{login}",
                configurator = WebSocketHttpConfiguration.class)
public class ChatServer {
    private static Map<String, Session> openedSessions = new HashMap<>();
    private Session wsSession;
    private HttpSession httpSession;
    private User user;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("login") String login) {
        this.wsSession = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.user = (User) httpSession.getAttribute("user"); // Not necessary to check because
                                                                // websocket may be accessed only by authorized users
        openedSessions.put(user.getLogin(), wsSession);
        System.out.println(openedSessions);
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("login") String login) {
        JSONHandler json = new JSONHandler();
        Map<String, String> messageInfo = json.parse(message);
        messageInfo.put("fullName", user.getName() + " " + user.getLastName());
        messageInfo.put("login", user.getLogin());

        long chatId = Long.valueOf(messageInfo.get("chatId"));
        long authorIf = Long.valueOf(messageInfo.get("authorId"));
        String content = messageInfo.get("content");

        Message userMessage = new Message(chatId, authorIf, content);

        MessagesDAO messagesDAO = new MessagesDAOImpl();
        messagesDAO.writeMessage(userMessage);

//        ChatDAO chatDAO = new ChatDAOImpl();
//        Chat chat = chatDAO.getChatById(chatId);
        try {
            if (openedSessions.containsKey(login)) {
                Session counterUserSession = openedSessions.get(login);
                if (counterUserSession.isOpen())
                    counterUserSession.getBasicRemote().sendText(json.getJSON(messageInfo));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        try {
            session.close();
            openedSessions.remove(user.getLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable exception) {
        exception.printStackTrace();

    }
}
