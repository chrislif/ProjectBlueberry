/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.function.Authorization;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
                session.setAttribute("currentUser", user);
                url = "/page/project/overview.jsp";
            } else {
                url = "/page/auth/login.jsp";
            }
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
