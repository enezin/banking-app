package by.stepchenko.web.servlet;

import by.stepchenko.database.entity.User;
import by.stepchenko.database.entity.enam.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user.getRole() == Role.ADMINISTRATOR) {
            req.getRequestDispatcher("/WEB-INF/jsp/adminmenu.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/usermenu.jsp").forward(req, resp);
        }
    }
}
