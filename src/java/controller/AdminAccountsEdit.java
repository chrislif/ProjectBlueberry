/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import controller.function.AdminManager;
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

/**
 *
 * @author jb706443
 */
public class AdminAccountsEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        HttpSession session = request.getSession();
        ArrayList<String> errorList = new ArrayList();
        PrintWriter responseOut = response.getWriter();
        
        String email = request.getParameter("email");
        String accountName = request.getParameter("accountName");
        String password = request.getParameter("password");
        
        //Account editUser = AdminManager.updateAccount(account, email, accountName, password, errorList);
        
        responseOut.flush();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
