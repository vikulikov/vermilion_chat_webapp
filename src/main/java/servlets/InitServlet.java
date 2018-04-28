package servlets;

import dao.UserDAO;
import model.JSONHandler;
import model.Useful;
import model.User;
import model.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Map<String, User> authorizedUsers = UserService.getInstance().getSessionIdToUser();
        UserDAO userDAO = UserService.getInstance().getUserDAO();

        Useful.printListElements(userDAO.findAllUsers());
        User user = userDAO.searchUserByEmail(email);

        JSONHandler JSONResponse = new JSONHandler();
        if (user != null && user.isPasswordValid(password)) {
            logInUser(request, authorizedUsers, user);
            JSONResponse.appendData("redirect", "profile");
        } else {
            JSONResponse.appendData("message",
                    "There is not user with such e-mail and password. Please try once again.");
        }
        System.out.println(JSONResponse.getJSON());

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(JSONResponse.getJSON());
        out.flush();
    }

    public boolean isAuthorized(String sessionId, Map<String, User> sessionIdToUser) {
        return  sessionIdToUser.containsKey(sessionId);
    }

    static void logInUser(HttpServletRequest request,
                          Map<String, User> authorizedUsers,
                          User user) {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            logOutUser(authorizedUsers, session);
        }

        authorizedUsers.put(session.getId(), user);
        request.getSession().setAttribute("user", user);

    }

    static void logOutUser(Map<String, User> authorizedUsers, HttpSession session) {
        authorizedUsers.remove(session.getId());
        session.removeAttribute("user");
    }
}
