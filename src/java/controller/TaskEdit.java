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

public class TaskEdit extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> errorList = new ArrayList();
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        int taskID = Integer.parseInt(request.getParameter("taskID"));
        
        model.Project project = ProjectManager.deleteTask(taskID, projectID, errorList);
        
        String editedProjectJSON = gson.toJson(project);
        
        responseOut.println(editedProjectJSON);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> errorList = new ArrayList();
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        int taskID = Integer.parseInt(request.getParameter("editedTaskID"));
        int contributorID = Integer.parseInt(request.getParameter("editedTaskContributorRelation"));
        int editedStoryID = Integer.parseInt(request.getParameter("editedTaskStoryRelation"));
        String editedTaskName = request.getParameter("editedTaskName");
        String editedTaskDetails = request.getParameter("editedTaskDetails");
        int editedTaskTime = Integer.parseInt(request.getParameter("editedTaskTime"));
        int editTaskPriority = Integer.parseInt(request.getParameter("editedTaskPriority"));
        
        model.Project project = ProjectManager.updateTasks(projectID, taskID, contributorID, editedStoryID, editedTaskName, editedTaskDetails, editTaskPriority, editedTaskTime, errorList);
        
        String editedProjectJSON = gson.toJson(project);
        
        responseOut.println(editedProjectJSON);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
