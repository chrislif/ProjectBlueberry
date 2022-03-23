package controller.function;

import data.ProjectDB;
import data.SprintDB;
import data.StoryDB;
import data.TaskDB;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Project;
import model.Sprint;
import model.Story;
import model.StoryTask;

public class ProjectManager {
    
    public static ArrayList<Project> retrieveProjects(Account user) {
        try {
            return ProjectDB.generateProjectList(user);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static ArrayList<Project> createProject(Account user, String projectName) {
        try {
            String projectKey = ProjectDB.createProject(projectName, LocalDate.now().toString());
            int projectID = Integer.parseInt(projectKey);
            ProjectDB.insertContributer(projectID, user.getAccountID(), "manager");
            
            return retrieveProjects(user);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static Project getProject(int projectID) {
        try {
            return ProjectDB.getProject(projectID);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static ArrayList<Sprint> createSprint(int projectID, int sprintNum, String sprintName, String startDate, String endDate) {
        Sprint newSprint = new Sprint(0, sprintNum, sprintName, startDate, endDate);
        
        try {
            SprintDB.createSprint(newSprint, projectID);
            
            return retrieveSprints(projectID);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static ArrayList<Sprint> retrieveSprints(int projectID) {
        try {
            return SprintDB.getSprints(projectID);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static ArrayList<Story> createStory(int sprintID, String storyName, int storyPriority) {
        Story newStory = new Story(0, storyName, storyPriority);
        
        try {
            StoryDB.createStory(newStory, sprintID);
            
            return retrieveStories(sprintID);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static ArrayList<Story> retrieveStories(int sprintID) {
        try {
            return StoryDB.getStories(sprintID);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static ArrayList<StoryTask> createTask(int storyID, String taskName, String taskDetails, int taskPriority, int taskTime) {
        StoryTask newTask = new StoryTask(0, taskName, taskPriority, taskTime, taskDetails, false);
        
        try {
            TaskDB.createTask(newTask, storyID);
            
            return TaskDB.getTasks(storyID);
        } catch (SQLException ex) {
            return null;
        }
    }
}
