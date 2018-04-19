package servlets;

import model.User;
import model.UserService;
import templater.PageProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String loginParameter = request.getParameter("login");
        User user = (User) request.getSession().getAttribute("user");

        if (loginParameter == null) {
            if (user != null) {
                response.sendRedirect("profile?login=" + user.getLogin());
                return;
            } else {
                response.sendRedirect("/");
                return;
            }
        }

        User requestedUser = UserService.getInstance().getUserDAO().searchUserByLogin(loginParameter);
        if (requestedUser == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("There is not such user");
            return;
        }

        Map<String, Object> userData = requestedUser.getUserPublicData();
        try {
            if (loginParameter.equals(user.getLogin())) {
                userData.put("guest_type", "owner");
            } else {
                userData.put("guest_type", "observer");
            }
        } catch (NullPointerException e) {
            userData.put("guest_type", "stranger");
        }

        response.getWriter().println(PageProcessor.getInstance().processPage("profile.ftl", userData));
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
