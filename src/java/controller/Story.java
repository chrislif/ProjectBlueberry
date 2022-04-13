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

public class Story extends HttpServlet {

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
            int sprintID = Integer.parseInt(request.getParameter("sprintID"));
            String storyName = request.getParameter("storyName");
            int storyPriority = Integer.parseInt(request.getParameter("storyPriority"));

            model.Project project = ProjectManager.createStory(projectID, sprintID, storyName, storyPriority);

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
