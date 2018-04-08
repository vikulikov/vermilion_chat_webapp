package servlets;

import dao.UserDAO;
import model.User;
import model.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

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

//      Send request to InitServlet to Log In automatically after registration
        clearParameters(request);
        request.getRequestDispatcher("").forward(request, response);
    }

    private void clearParameters(HttpServletRequest request) {
//      Only email and password will be left
        request.setAttribute("first_name", "");
        request.setAttribute("login", "");
        request.setAttribute("last_name", "");
        request.setAttribute("gender", "");
        request.setAttribute("birthday", "");
    }



}
