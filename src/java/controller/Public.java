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
import model.Project;

public class Public extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter responseOut = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Account currentUser = (Account)session.getAttribute("currentUser");
        ArrayList<String> errorList = new ArrayList();
        
        Gson gson = new Gson();
        String action = request.getParameter("action");
        
        switch (action) {
            
            default:
             
                break;
        }
        
        responseOut.flush();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String email;
        String password;
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        ArrayList<String> errorList = new ArrayList();
        
        switch (action) {
            
            case "toHome":
                url = "/index.jsp";
                break;
            case "toLogin":
                url = "/page/auth/login.jsp";
                break;
            case "toOverview":
                url ="/page/project/overview.jsp";
                break;
            case "toRegister":
                url = "/page/auth/register.jsp";
                break;
            case "register":
                String accountName = request.getParameter("accountName");
                password = request.getParameter("password");
                String passwordCheck = request.getParameter("passwordCheck");
                email = request.getParameter("email");

                if (Authorization.IsValidRegisteration(accountName, password, email, errorList)){
                    Account newUser = Authorization.RegisterUser(email, password, passwordCheck, accountName, errorList);
                    if (newUser != null){
                        session.setAttribute("currentUser", newUser);
                        url = "/index.jsp";
                    } else {
                        url = "/page/auth/login.jsp";
                    }
                } else {
                    url = "/page/auth/register.jsp";
                }
                break;
            default:
                url = "/index.jsp";
                break;
        }
        
        request.setAttribute("errorList", errorList);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    public Public() {}
    
    @Override
    public String getServletInfo() {
        return "Hello Public Servlet";
    }
}
