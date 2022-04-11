package controller.function;

import com.google.gson.Gson;
import data.ProjectDB;
import data.SprintDB;
import data.StoryDB;
import data.TaskDB;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    
    public static Project deleteSprint (int sprintID, int projectID, ArrayList<String> errorList) {
        try {
            SprintDB.deleteSprintByID(sprintID);
            model.Project project = getProject(projectID);
            
            return project;
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static Project updateSprint(int projectID, int sprintID, int sprintNum, String sprintName, String sprintStartDate, String sprintEndDate, ArrayList<String> errorList) {
        try {
            SprintDB.updateSprintName(sprintID, sprintName);
            SprintDB.updateSprintNum(sprintID, sprintNum);
            SprintDB.updateSprintStart(sprintID, sprintStartDate);
            SprintDB.updateSprintEnd(sprintID, sprintEndDate);
            
            model.Sprint updatedSprint = new Sprint(sprintID, sprintNum, sprintName, sprintStartDate, sprintEndDate);
            
            ArrayList<Story> sprintStories = retrieveStories(sprintID);
            
            updatedSprint.stories = sprintStories;
            
            model.Project project = getProject(projectID);
            
            return project;
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static Project createStory(int projectID, int sprintID, String storyName, int storyPriority) {
        Story newStory = new Story(0, storyName, storyPriority);
        
        try {
            StoryDB.createStory(newStory, sprintID);
            
            model.Project project = ProjectDB.getProject(projectID);
            
            return project;
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
    
    public static Project updateStories(int projectID, int sprintID, int storyID, String storyName, int storyPriorityLevel, ArrayList<String> errorList){
        try {
            StoryDB.updateStorySprintID(storyID, sprintID);
            StoryDB.updateStoryName(storyID, storyName);
            StoryDB.updateStoryPriority(storyID, storyPriorityLevel);
            
            model.Story updatedStory = new Story(storyID, storyName, storyPriorityLevel);
            
            ArrayList <StoryTask> tasks = TaskDB.getTasks(storyID);
            
            updatedStory.tasks = tasks;
            
            model.Project project = getProject(projectID);
            
            return project;
        } catch(SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static Project deleteStory (int storyID, int projectID, ArrayList<String> errorList) {
        try {
            StoryDB.deleteStoryByID(storyID);
            model.Project project = getProject(projectID);
            
            return project;
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static Project createTask(int projectID, int storyID, String taskName, String taskDetails, int taskPriority, int taskTime) {
        StoryTask newTask = new StoryTask(0, taskName, taskPriority, taskTime, taskDetails, 0);
        
        try {
            TaskDB.createTask(newTask, storyID);
            
            model.Project project = getProject(projectID);
            
            return project;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public static Project updateTasks(int projectID, int taskID, int storyID, String taskName, String taskDetails, int taskPriorityLevel, int taskTime, ArrayList<String> errorList){
        try {
            TaskDB.updateTaskName(taskID, taskName);
            TaskDB.updateTaskDetails(taskID, taskDetails);
            TaskDB.updateTaskPriority(taskID, taskPriorityLevel);
            TaskDB.updateTaskTime(taskID, taskTime);
            
            model.StoryTask updatedTask = new StoryTask(taskID, taskName, taskPriorityLevel, taskTime, taskDetails, 0);
            
            model.Project project = getProject(projectID);
            
            return project;
        } catch(SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static Project deleteTask (int taskID, int projectID, ArrayList<String> errorList) {
        try {
            TaskDB.deleteTaskByID(taskID);
            model.Project project = getProject(projectID);
            
            return project;
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
    
    public static ArrayList<Account> addContributer(int projectID, int accountID){
        
        try{
            ProjectDB.insertContributer(projectID, accountID, "contributor");
            Project project = new Project();
            project.setProjectID(projectID);
            return ProjectDB.getContributers(project);
        } catch (SQLException ex){
            String message = ex.getMessage();
            int i = 0;
            return null;
        }
        
    }
}
