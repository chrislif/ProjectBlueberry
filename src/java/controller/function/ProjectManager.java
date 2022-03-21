package controller.function;

import data.BlueDB;
import data.ProjectDB;
import data.SprintDB;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Project;
import model.Sprint;

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
    
    public static Project getProject(int projectID, ArrayList<String> errorList) {
        try {
            return BlueDB.getProject(projectID);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static void createSprint(int projectID, int sprintNum, String sprintName, String startDate, String endDate, ArrayList<String> errorList) {
        Sprint newSprint = new Sprint(0, sprintNum, sprintName, startDate, endDate);
        
        try {
            SprintDB.createSprint(newSprint, projectID);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
        }
    }
    
    public static ArrayList<Sprint> retrieveSprints(int projectID, ArrayList<String> errorList) {
        try {
            return BlueDB.getSprints(projectID);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
}
