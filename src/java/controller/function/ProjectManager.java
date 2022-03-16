/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.function;

import data.BlueDB;
import data.ProjectDB;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Project;

/**
 *
 * @author dh687287
 */
public class ProjectManager {
    
    public static ArrayList<Project> retrieveProjects(Account user, ArrayList<String> errorList) {
        try {
            return BlueDB.generateProjectList(user);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static void createProject(Account user, String projectName, ArrayList<String> errorList) {
        try {
            String projectKey = ProjectDB.createProject(projectName, LocalDate.now().toString());
            int projectID = Integer.parseInt(projectKey);
            ProjectDB.insertContributer(projectID, user.getAccountID(), "manager");
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
        }
    }
}
