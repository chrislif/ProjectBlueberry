package controller;

import com.google.gson.Gson;
import controller.function.Authorization;
import controller.function.ProjectManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

public class Project extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        HttpSession session = request.getSession();
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        
        model.Project project = ProjectManager.getProject(projectID);
        
        Account currentUser = (Account)session.getAttribute("currentUser");
        Boolean isContributor = Authorization.isContributerOnProject(project, currentUser);
        
        request.setAttribute("isContributor", gson.toJson(isContributor));

        request.setAttribute("project", gson.toJson(project));
        
        String url = "/page/project/projectHome.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        HttpSession session = request.getSession();
        Account currentUser = (Account)session.getAttribute("currentUser");
        
        String projectName = request.getParameter("projectName");

        ArrayList<model.Project> projectList = ProjectManager.createProject(currentUser, projectName);

        responseOut.println(gson.toJson(projectList));
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
