package controller.function;

import data.BlueDB;
import data.ProjectDB;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Project;
import model.Sprint;
import model.Story;

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
            ProjectDB.createSprint(newSprint, projectID);
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
    
    public static ArrayList<Story> createStory(int sprintID, String storyName, int storyPriority, ArrayList<String> errorList) {
        Story newStory = new Story(0, storyName, storyPriority);
        
        try {
            ProjectDB.createStory(newStory, sprintID);
            
            return retrieveStories(sprintID, errorList);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static ArrayList<Story> retrieveStories(int sprintID, ArrayList<String> errorList) {
        
        try {
            return BlueDB.getStories(sprintID);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
}
