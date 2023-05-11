package by.stepchenko.web.servlet;

import by.stepchenko.database.entity.User;
import by.stepchenko.service.BankAccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/bankaccountsmanagement")
public class BankAccountsManagementServlet extends HttpServlet {

    private final BankAccountService bankAccountService = BankAccountService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("accountid");
        req.getSession().setAttribute("bankAccountId", id);
        var bankAccount = bankAccountService.findById(Long.parseLong(id));
        req.setAttribute("bankaccount", bankAccount);
        req.getSession().setAttribute("balance", bankAccount.get(0).getBalance());
        req.getSession().setAttribute("creditlimit", bankAccount.get(0).getCreditLimit());
        req.getRequestDispatcher("/WEB-INF/jsp/bankaccountsmanagement.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String action = req.getParameter("action");
        String id = (String) req.getSession().getAttribute("bankAccountId");
        if ("put".equalsIgnoreCase(action)) {
            String transfer = req.getParameter("putMoney");
            Double balance = Double.parseDouble(req.getSession().getAttribute("balance").toString());
            Double creditLimit = Double.parseDouble(req.getSession().getAttribute("creditlimit").toString());
            if (!bankAccountService.replenishment(Long.parseLong(id), transfer, balance, creditLimit)) {
                resp.sendRedirect(("/bankaccountsmanagement?accountid=").concat(id).concat("&error=true"));
            } else {
                resp.sendRedirect(("/bankaccountsmanagement?accountid=").concat(id));
            }
        } else if ("delete".equalsIgnoreCase(action)) {
            if (bankAccountService.delete(Long.parseLong(id))) {
                resp.sendRedirect(("/bankaccounts?userId=").concat(user.getId().toString()));
            } else {
                resp.sendRedirect(("/bankaccountsmanagement?accountid=").concat(id).concat("&errordelete=true"));
            }
        }
    }
}
