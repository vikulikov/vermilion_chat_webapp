package servlets;

import dao.UserDAO;
import model.Useful;
import model.User;
import model.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String sessionId = request.getSession().getId();
        Map<String, User> authorizedSessions = UserService.getInstance().getSessionIdToUser();

        if (!isAuthorized(sessionId, authorizedSessions)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            User user = authorizedSessions.get(sessionId);
            request.setAttribute("user", user);
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(email + " " + password);

        Map<String, User> authorizedUsers = UserService.getInstance().getSessionIdToUser();
        UserDAO userDAO = UserService.getInstance().getUserDAO();

        Useful.printListElements(userDAO.findAllUsers());
        User user = userDAO.searchUserByEmail(email);

        if (user != null && user.isPasswordValid(password)) {
            String sessionId = request.getSession().getId();
            authorizedUsers.put(sessionId, user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("profile?=" + user.getLogin());
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("There is not user with such e-mail and password. Please try once again.");
        }
    }

    public boolean isAuthorized(String sessionId, Map<String, User> sessionIdToUser) {
        return  sessionIdToUser.containsKey(sessionId);
    }
}
