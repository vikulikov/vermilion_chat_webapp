package servlets;

import model.User;
import templater.PageProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getUserPublicData().toString());

        response.getWriter().println(PageProcessor.getInstance().processPage("profile.ftl", user.getUserPublicData()));
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
