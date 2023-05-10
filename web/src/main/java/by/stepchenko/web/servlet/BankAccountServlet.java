package by.stepchenko.web.servlet;

import by.stepchenko.database.entity.BankAccount;
import by.stepchenko.database.entity.User;
import by.stepchenko.service.BankAccountService;
import by.stepchenko.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/bankaccounts")
public class BankAccountServlet extends HttpServlet {

    private final BankAccountService bankAccountService = BankAccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        req.setAttribute("bankaccount", bankAccountService.findByUserId(Long.parseLong(id)));
        req.getRequestDispatcher(PagesUtil.BANK_ACCOUNTS).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = (User) req.getSession().getAttribute("user");
        Optional<BankAccount> created = bankAccountService.create(Long.parseLong(id), BankAccount.builder().build());
        resp.sendRedirect(("/bankaccounts?userId=").concat(user.getId().toString()));
    }
}
