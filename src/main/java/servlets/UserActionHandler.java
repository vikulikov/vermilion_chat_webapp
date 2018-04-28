package servlets;

import model.User;
import model.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UserActionHandler", urlPatterns = {"/useraction"})
public class UserActionHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String actionValue = request.getParameter("action");
        HttpSession session = request.getSession();

        if (actionValue.equals("delete")) {
            Map<String, User> authorizedUsers = UserService.getInstance().getSessionIdToUser();
            InitServlet.logOutUser(authorizedUsers, session);
            response.sendRedirect("/");
        }
    }
}
