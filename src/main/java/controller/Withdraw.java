package controller;

import DBConnection.DBConnection;
import bean.AtmI;
import model.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Withdraw", urlPatterns = "Withdraw")
public class Withdraw extends HttpServlet {

    @Inject
    AtmI atm;

    @Inject
    DBConnection db;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String amount = req.getParameter("amount");

        HttpSession session = req.getSession();
        String phone = (String) session.getAttribute("phoneNo");
        String password = (String) session.getAttribute("password");
        User user = db.getUser(phone, password);

        boolean status = atm.withdraw(user, Double.parseDouble(amount));

        String balance = atm.checkBalance(user);

        if(!status){
            resp.getWriter().println("successful " + balance);
        }else{
            resp.getWriter().println("Unsuccessful");
        }
    }
}
