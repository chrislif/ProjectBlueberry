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
            int sprintID = Integer.parseInt(request.getParameter("sprintID"));
            String storyName = request.getParameter("storyName");
            int storyPriority = Integer.parseInt(request.getParameter("storyPriority"));

            ArrayList<model.Story> storyList = ProjectManager.createStory(sprintID, storyName, storyPriority);

            String storyListJSON = gson.toJson(storyList);

            responseOut.println(storyListJSON);
        } else {
            int sprintID = Integer.parseInt(request.getParameter("sprintID"));

            ArrayList<model.Story> storyList = ProjectManager.retrieveStories(sprintID);

            String storyListJSON = gson.toJson(storyList);

            responseOut.println(storyListJSON);
            responseOut.println(gson.toJson("Error - Invalid credentials"));
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
