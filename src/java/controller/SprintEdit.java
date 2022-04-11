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

public class SprintEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> errorList = new ArrayList();
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        int sprintID = Integer.parseInt(request.getParameter("sprintID"));
        
        model.Project project = ProjectManager.deleteSprint(sprintID, projectID, errorList);
        
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
        int sprintID = Integer.parseInt(request.getParameter("sprintID"));
        int sprintNum = Integer.parseInt(request.getParameter("sprintNumber"));
        String sprintName = request.getParameter("sprintName");
        String sprintStartDate = request.getParameter("sprintStartDate");
        String sprintEndDate = request.getParameter("sprintEndDate");
        
        model.Project project = ProjectManager.updateSprint(projectID, sprintID, sprintNum, sprintName, sprintStartDate, sprintEndDate, errorList);
        
        String editedProjectJSON = gson.toJson(project);
        
        responseOut.println(editedProjectJSON);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
