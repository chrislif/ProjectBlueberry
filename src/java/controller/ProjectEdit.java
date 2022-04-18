/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.function.ProjectManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dh687287
 */
public class ProjectEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> errorList = new ArrayList();
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        
        boolean isDeleted = ProjectManager.deleteProject(projectID, errorList);
        
        if (isDeleted = true) {
            getServletContext().getRequestDispatcher("/page/project/overview.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/page/project/projectHome.jsp").forward(request, response);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
