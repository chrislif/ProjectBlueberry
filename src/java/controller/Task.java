package controller;

import com.google.gson.Gson;
import controller.function.ProjectManager;
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
import model.StoryTask;

public class Task extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int storyID = Integer.parseInt(request.getParameter("storyID"));
        String taskName = request.getParameter("taskName");
        String taskDetails = request.getParameter("taskDetails");
        int taskTime = Integer.parseInt(request.getParameter("taskTime"));
        int taskPriority = Integer.parseInt(request.getParameter("taskPriority"));
        
        ArrayList<StoryTask> taskList = ProjectManager.createTask(storyID, taskName, taskDetails, taskPriority, taskTime);

        String taskListJSON = gson.toJson(taskList);

        responseOut.println(taskListJSON);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
