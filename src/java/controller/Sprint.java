package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.function.ProjectManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Sprint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));

        ArrayList<model.Sprint> sprintList = ProjectManager.retrieveSprints(projectID);

        String sprintListJSON = gson.toJson(sprintList);

        responseOut.println(sprintListJSON);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        int sprintNum = Integer.parseInt(request.getParameter("sprintNum"));
        String sprintName = request.getParameter("sprintName");
        String sprintStartDate = request.getParameter("sprintStartDate");
        String sprintEndDate = request.getParameter("sprintEndDate");

        ProjectManager.createSprint(projectID, sprintNum, sprintName, sprintStartDate, sprintEndDate);

        ArrayList<model.Sprint> sprintList = ProjectManager.retrieveSprints(projectID);

        String sprintListJSON = gson.toJson(sprintList);

        responseOut.println(sprintListJSON);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
