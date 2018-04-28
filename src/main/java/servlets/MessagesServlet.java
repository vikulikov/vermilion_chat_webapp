package servlets;

import dao.*;
import model.Chat;
import model.Message;
import model.User;
import model.UserService;
import templater.PageProcessor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MessagesServlet", urlPatterns = {"/messages/*"})
public class MessagesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String uri = request.getRequestURI();
        // return value must be messages/<login>
        if (uri.equals("/")) {
            // returns a page with all users's chats
            return;
        }

        String[] uriParts = uri.split("/");
        String counterUserLogin = uriParts[2];

        UserDAO userDAO = UserService.getInstance().getUserDAO();
        User counterUser = userDAO.searchUserByLogin(counterUserLogin);

        ChatDAO chatDAO = new ChatDAOImpl();
        Chat chat = chatDAO.getChat(user, counterUser);
        if (chat == null) {
            Chat newChat = new Chat(user.getId(), counterUser.getId());
            chatDAO.initChat(newChat);
            // get just created chat from database to know chat's id
            chat = chatDAO.getChat(user, counterUser);
        }

        MessagesDAO messagesDAO = new MessagesDAOImpl();
        List<Message> chatMessages = messagesDAO.getMessages(chat);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("guest_type", "owner");    // must be included everywhere because it is used in main_layout.ftl template
        responseData.put("messages", chatMessages);
        responseData.put("chat_id", chat.getId());
        responseData.put("user", user.getUserPublicData());
        responseData.put("counter_user", counterUser.getUserPublicData());

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(PageProcessor.getInstance().processPage("message.ftl", responseData));
    }

}
