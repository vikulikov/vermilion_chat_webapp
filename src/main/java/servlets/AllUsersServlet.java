package servlets;

import dao.UserDAO;
import model.User;
import model.UserService;
import templater.PageProcessor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AllUsersServlet", urlPatterns = {"/users"})
public class AllUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        UserDAO userDAO = UserService.getInstance().getUserDAO();
        List<User> allUsers = userDAO.findAllUsers();

        List<Map<String, Object>> usersInfo = new ArrayList<>();
        allUsers.forEach(user ->  usersInfo.add(user.getUserPublicData()));

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("users_info", usersInfo);

        if (sessionUser == null) responseData.put("guest_type", "stranger");
        else responseData.put("guest_type", "observer");

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
        response.getWriter().print(PageProcessor.getInstance().processPage("users.ftl", responseData));


    }
}
