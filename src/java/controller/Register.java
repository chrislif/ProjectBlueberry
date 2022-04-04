package controller;

import controller.function.Authorization;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url;
        
        HttpSession session = request.getSession();
        ArrayList<String> errorList = new ArrayList();
        
        String accountName = request.getParameter("accountName");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");
        String email = request.getParameter("email");

        if (Authorization.IsValidRegisteration(accountName, password, email, errorList)) {
            Account newUser = Authorization.RegisterUser(email, password, passwordCheck, accountName, errorList);
            if (newUser != null) {
                session.setAttribute("currentUser", newUser);
                url = "/index.jsp";
            } else {
                url = "/page/auth/login.jsp";
            }
        } else {
            url = "/page/auth/register.jsp";
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
