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
            case "toOverview":
                url ="/page/project/overview.jsp";
                break;
            case "authorize":
                email = request.getParameter("email");
                password = request.getParameter("password");
                
                if (Authorization.IsValidLogin(email, password, errorList)){
                    Account user = Authorization.authorizeUser(email, password, errorList);
                    if (user != null){
                        session.setAttribute("currentUser", user);
                        url = "/page/project/overview.jsp";
                    } else {
                        url = "/page/auth/login.jsp";
                    }
                }
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
