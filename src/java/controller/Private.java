package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author chris
 */
public class Private extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url;
        
        String action = request.getParameter("action");
        
        switch (action) {
            
            default:
                url = "/index.jsp";
                break;
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    public Private() {}
    
    @Override
    public String getServletInfo() {
        return "Hello Private Servlet";
    }
}
