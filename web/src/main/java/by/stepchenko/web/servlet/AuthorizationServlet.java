package by.stepchenko.web.servlet;

import by.stepchenko.database.entity.User;
import by.stepchenko.service.UserService;
import by.stepchenko.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.AUTHORIZATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.getByEmailAndPassword(email, password)
                .ifPresentOrElse(
                        user -> successAuthorization(req, resp, user),
                        () -> failedAuthorization(req, resp));
    }

    @SneakyThrows
    private static void successAuthorization(HttpServletRequest req, HttpServletResponse resp, User user) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/menu");
    }

    @SneakyThrows
    private static void failedAuthorization(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/authorization?error=true");
    }
}
