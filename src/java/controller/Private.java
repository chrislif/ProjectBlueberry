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
import model.Account;
import model.Project;

/**
 *
 * @author chris
 */
public class Private extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Account currentUser = (Account)session.getAttribute("currentUser");
        ArrayList<String> errorList = new ArrayList();
        
        Gson gson = new GsonBuilder().setDateFormat("yyyy-Mm-dd").create();
        String action = request.getParameter("action");
        
        switch (action) {
            
            case "getProjects":
                ArrayList<Project> projectList = ProjectManager.retrieveProjects(currentUser, errorList);
                
                String projectListJSON = gson.toJson(projectList);
                
                responseOut.println(projectListJSON);
                break;
            
            default:
                
                break;
        }
        
        responseOut.flush();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url;
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        ArrayList<String> errorList = new ArrayList();
        
        Account currentUser = (Account) session.getAttribute("currentUser");
        
        switch (action) {
            case "toOverview":
                url ="/page/project/overview.jsp";
                break;
            case "logout":
                url = "/page/auth/login.jsp";
                currentUser = null;
                session.setAttribute("currentUser", currentUser);
                break;
            default:
                url = "/index.jsp";
                break;
        }
        
        request.setAttribute("errorList", errorList);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    public Private() {}
    
    @Override
    public String getServletInfo() {
        return "Hello Private Servlet";
    }
}
