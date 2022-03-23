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

public class Story extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int sprintID = Integer.parseInt(request.getParameter("sprintID"));
        String storyName = request.getParameter("storyName");
        int storyPriority = Integer.parseInt(request.getParameter("storyPriority"));

        ArrayList<model.Story> storyList = ProjectManager.createStory(sprintID, storyName, storyPriority);

        String storyListJSON = gson.toJson(storyList);

        responseOut.println(storyListJSON);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
