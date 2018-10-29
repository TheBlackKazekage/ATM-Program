package controller;

import DBConnection.DBConnection;
import bean.AuthI;
import model.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "Login")
public class Login extends HttpServlet {
    @Inject
    AuthI auth;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNo = req.getParameter("phoneNo");
        String password = req.getParameter("password");

        boolean status = auth.login(phoneNo, password);

        HttpSession session = req.getSession();

        session.setAttribute("phoneNo", phoneNo);
        session.setAttribute("password", password);

        if(status){
            resp.sendRedirect("transact.html");
        }else{
            resp.sendRedirect("index.jsp");
        }
    }
}
