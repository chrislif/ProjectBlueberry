/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author dh687287
 */
public class StoryEdit extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> errorList = new ArrayList();
        PrintWriter responseOut = response.getWriter();
        Gson gson = new Gson();
        
        int editedSprintID = Integer.parseInt(request.getParameter("editedSprint"));
        int editStoryID = Integer.parseInt(request.getParameter("editStoryID"));
        String editedStoryName = request.getParameter("editedStoryName");
        int editStoryPriorityLevel = Integer.parseInt(request.getParameter("editedStoryPriorityLevel"));
        
        model.Story editedStory = ProjectManager.updateStories(editedSprintID, editStoryID, editedStoryName, editStoryPriorityLevel, errorList);
        
        String editedStoryJSON = gson.toJson(editedStory);
        
        responseOut.println(editedStoryJSON);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
