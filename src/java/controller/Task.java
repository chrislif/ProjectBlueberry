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
import model.StoryTask;

public class Task extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        Boolean isContributor = (Boolean) session.getAttribute("isContributor");

        if (!isContributor) {
            int projectID = Integer.parseInt(request.getParameter("projectID"));
            int storyID = Integer.parseInt(request.getParameter("storyID"));
            String taskName = request.getParameter("taskName");
            String taskDetails = request.getParameter("taskDetails");
            int taskTime = Integer.parseInt(request.getParameter("taskTime"));
            int taskPriority = Integer.parseInt(request.getParameter("taskPriority"));

            model.Project project = ProjectManager.createTask(projectID, storyID, taskName, taskDetails, taskPriority, taskTime);

            String projectJSON = gson.toJson(project);
            

            responseOut.println(projectJSON);
        } else {
            int projectID = Integer.parseInt(request.getParameter("projectID"));
            model.Project project = ProjectManager.getProject(projectID);
            String projectJSON = gson.toJson(project);
            
            responseOut.println(projectJSON);
            responseOut.println(gson.toJson("Error - Invalid credentials"));
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
