/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import controller.function.ProjectManager;
import data.AccountDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;


public class Contributor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        String contributorName = request.getParameter("contributerName");
        
        try {
            Account account = AccountDB.getAccount(contributorName);
            int accountID = account.getAccountID();
            ArrayList<Account> contributorList = ProjectManager.addContributer(projectID, accountID);
            String contributorListJSON = gson.toJson(contributorList);
            responseOut.println(contributorListJSON);
        } catch (SQLException ex){
            String message = ex.getMessage();
        }
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
