package by.stepchenko.web.servlet;

import by.stepchenko.database.entity.CreditCard;
import by.stepchenko.service.CreditCardService;
import by.stepchenko.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/creditcards")
public class CreditCardServlet extends HttpServlet {

    private final CreditCardService creditCardService = CreditCardService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("accountid");
        req.getSession().setAttribute("bankAccountId", id);
        req.setAttribute("creditcards", creditCardService.findByAccountId(Long.parseLong(id)));
        req.getRequestDispatcher(PagesUtil.CREDIT_CARDS).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String) req.getSession().getAttribute("bankAccountId");
        Optional<CreditCard> created = creditCardService.create(Long.parseLong(id), CreditCard.builder().build());
        resp.sendRedirect(("/creditcards?accountid=").concat(id));
    }
}