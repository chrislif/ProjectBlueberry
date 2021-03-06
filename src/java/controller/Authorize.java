package controller;

import controller.function.Authorization;
import data.AuthDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

public class Authorize extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String url = "/page/auth/login.jsp";
        
        Account currentUser = null;
        session.setAttribute("currentUser", currentUser);
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String email;
        String password;
        
        HttpSession session = request.getSession();
        ArrayList<String> errorList = new ArrayList();

        email = request.getParameter("email");
        password = request.getParameter("password");

        if (Authorization.IsValidLogin(email, password, errorList)) {
            Account user = Authorization.authorizeUser(email, password, errorList);
            if (user != null) {
                user.setIsAdmin(Authorization.accountIsAdmin(user, errorList));
                session.setAttribute("currentUser", user);
                url = "/page/project/overview.jsp";
            } else {
                errorList.add("Invalid Credentials");
                url = "/page/auth/login.jsp";
            }
        } else {
            url = "/page/auth/login.jsp";
        }
        session.setAttribute("errorList", errorList);
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
