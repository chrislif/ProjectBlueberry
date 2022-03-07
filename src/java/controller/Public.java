package controller;

import controller.function.Authorization;
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
public class Public extends HttpServlet {
    
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
            case "toRegister":
                url = "/page/auth/register.jsp";
                break;
            case "register":
                String username = request.getParameter("username");
                password = request.getParameter("password");
                String passwordCheck = request.getParameter("passwordCheck");
                email = request.getParameter("email");

                if (Authorization.IsValidLogin(username, password, email, errorList)){
                    Account newUser = Authorization.RegisterUser(email, password, passwordCheck, username, errorList);
                    if (newUser != null){
                        session.setAttribute("currentUser", newUser);
                        url = "/index.jsp";
                    } else {
                        url = "/page/auth/register.jsp";
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
