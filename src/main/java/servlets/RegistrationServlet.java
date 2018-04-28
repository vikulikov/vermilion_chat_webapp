package servlets;

import dao.UserDAO;
import model.User;
import model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
//      ID equals to 0 due to it's unnecessarily while adding to database.
        User user = new User(0,
                request.getParameter("first_name"),
                request.getParameter("login"),
                request.getParameter("password"),
                request.getParameter("last_name"),
                request.getParameter("email"),
                Integer.valueOf(request.getParameter("gender")),
                request.getParameter("birthday"));

        UserDAO userDAO = UserService.getInstance().getUserDAO();
        userDAO.addUser(user);

        Map<String, User> authorizedUsers = UserService.getInstance().getSessionIdToUser();
        InitServlet.logInUser(request, authorizedUsers, user);
        response.sendRedirect("profile?=" + user.getLogin());
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
