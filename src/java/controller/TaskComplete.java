/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import controller.function.ProjectManager;
import data.AccountDB;
import data.TaskDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.StoryTask;

/**
 *
 * @author al725845
 */
public class TaskComplete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                ArrayList<String> errorList = new ArrayList();
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        HttpSession session = request.getSession();
        Account currentUser = (Account)session.getAttribute("currentUser");
        
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        int taskID = Integer.parseInt(request.getParameter("completedTaskID"));
        int priority = Integer.parseInt(request.getParameter("completedTaskPriority"));
        int time = Integer.parseInt(request.getParameter("completedTaskTime"));
        
        int xp = 0;
        
        if (priority == 1){
            xp = 10 * time;
        } else if (priority == 2) {
            xp = 8 * time;
        } else if (priority == 3) {
            xp = 6 * time;
        } else if (priority == 4) {
            xp = 4 * time;
        } else if (priority == 5) {
            xp = 2 * time;
        }
        
        try {
            StoryTask t = TaskDB.getTaskByID(taskID);
            
            TaskDB.updateTaskCompleted(taskID);
            
            AccountDB.updateAccountXP(xp, t.getContributor());
            
        } catch (SQLException ex){
            String message = ex.getMessage();
        }
        
        model.Project p = ProjectManager.getProject(projectID);
        
        responseOut.println(gson.toJson(p));
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
