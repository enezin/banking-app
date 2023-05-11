package by.stepchenko.web.servlet;

import by.stepchenko.database.entity.CreditCard;
import by.stepchenko.service.CreditCardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/creditcardsmanagement")
public class CreditCardsManagementServlet extends HttpServlet {

    private final CreditCardService creditCardService = CreditCardService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("creditcardid");
        req.getSession().setAttribute("creditcard", creditCardService.findById(Long.parseLong(id)));
        req.getRequestDispatcher("/WEB-INF/jsp/creditcardsmanagement.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CreditCard creditCard = (CreditCard) req.getSession().getAttribute("creditcard");
        if ("delete".equalsIgnoreCase(action)) {
            if (creditCardService.delete(creditCard.getId())) {
                String id = (String) req.getSession().getAttribute("bankAccountId");
                resp.sendRedirect(("/creditcards?accountid=").concat(id));
            } else {
                resp.sendRedirect(("//creditcardsmanagement?creditcardid=").concat(creditCard.getId().toString()).concat("&errordelete=true"));
            }
        }
    }
}