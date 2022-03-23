/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import controller.function.ProjectManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

public class Project extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        HttpSession session = request.getSession();
        Account currentUser = (Account)session.getAttribute("currentUser");
        
        ArrayList<model.Project> projectList = ProjectManager.retrieveProjects(currentUser);

        String projectListJSON = gson.toJson(projectList);

        responseOut.println(projectListJSON);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        HttpSession session = request.getSession();
        Account currentUser = (Account)session.getAttribute("currentUser");
        
        String projectName = request.getParameter("projectName");

        ArrayList<model.Project> projectList = ProjectManager.createProject(currentUser, projectName);

        responseOut.println(gson.toJson(projectList));
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
