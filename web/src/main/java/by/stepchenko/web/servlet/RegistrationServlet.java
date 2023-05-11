package by.stepchenko.web.servlet;

import by.stepchenko.database.entity.User;
import by.stepchenko.database.entity.enam.UserGender;
import by.stepchenko.service.UserService;
import by.stepchenko.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> created = userService.create(
                User.builder()
                        .firstName(req.getParameter("firstname"))
                        .lastName(req.getParameter("lastname"))
                        .birthdate(LocalDate.parse(req.getParameter("birthdate")))
                        .gender(UserGender.valueOf(req.getParameter("gender")))
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .build());
        resp.sendRedirect("/authorization");
    }
}