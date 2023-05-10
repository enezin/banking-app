package by.stepchenko.web.servlet;

import by.stepchenko.service.UserService;
import by.stepchenko.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("users", userService.findAll());
            req.getRequestDispatcher(PagesUtil.USERS).forward(req, resp);
        } else {
            req.setAttribute("user", userService.getById(Long.parseLong(id)));
            req.getRequestDispatcher(PagesUtil.USER).forward(req, resp);
        }
    }
}