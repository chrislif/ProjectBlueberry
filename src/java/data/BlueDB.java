package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import model.Project;
import model.Sprint;
import model.Story;
import model.StoryTask;

/**
 *
 * @author al725845
 */
public class BlueDB {
    public static ArrayList<Project> generateProjectList(Account user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        ArrayList<Project> projectList = new ArrayList();
        
        String query = "SELECT account.accountID, projectPeople.tag, project.projectID, project.projectName, project.creationDate FROM project "
                     + "INNER JOIN projectPeople ON project.projectID = projectPeople.projectID "
                     + "INNER JOIN account ON account.accountID = projectPeople.accountID "
                     + "WHERE account.accountID = ? ";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(user.getAccountID()));
            
            resultSet = statement.executeQuery();
            
            Project project;
            while(resultSet.next()){
                project = new Project();
                project.setProjectID(resultSet.getInt("projectID"));
                project.setProjectName(resultSet.getString("projectName"));
                project.setProjectCreationDate(resultSet.getString("creationDate"));
                
                projectList.add(project);
            }
        } catch (SQLException ex){
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return projectList;
    }
    
    public static Project getProject(int projectID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Project project = new Project();
        
        String query = "SELECT account.accountID, projectPeople.tag, project.projectID, project.projectName, project.creationDate FROM project "
                     + "INNER JOIN projectPeople ON project.projectID = projectPeople.projectID "
                     + "INNER JOIN account ON account.accountID = projectPeople.accountID "
                     + "WHERE project.projectID = ? ";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(projectID));
            
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                project = new Project();
                project.setProjectID(resultSet.getInt("projectID"));
                project.setProjectName(resultSet.getString("projectName"));
                project.setProjectCreationDate(resultSet.getString("creationDate"));
                project.contributors = getContributers(project);
                project.managers = getManagers(project);
                project.sprints = getSprints(project.getProjectID());
            }
        } catch (SQLException ex){
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return project;
    }
    
    public static ArrayList<Account> getContributers(Project project) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Account> contributers = new ArrayList();
        
        String query = "SELECT account.accountID, account.accountName, projectPeople.tag, project.projectID FROM project "
                     + "INNER JOIN projectPeople ON project.projectID = projectPeople.projectID "
                     + "INNER JOIN account ON account.accountID = projectPeople.accountID "
                     + "WHERE project.projectID = ? AND tag = 'contributor' ";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(project.getProjectID()));
            resultSet = statement.executeQuery();
            
            Account account;
            while (resultSet.next()){
                account = new Account();
                account.setAccountID(resultSet.getInt("accountID"));
                account.setAccountName(resultSet.getString("accountName"));
                
                contributers.add(account);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return contributers;
    }
    
    public static ArrayList<Account> getManagers(Project project) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Account> managers = new ArrayList();
        
        String query = "SELECT account.accountID, account.accountName, projectPeople.tag, project.projectID FROM project "
                     + "INNER JOIN projectPeople ON project.projectID = projectPeople.projectID "
                     + "INNER JOIN account ON account.accountID = projectPeople.accountID "
                     + "WHERE project.projectID = ? AND tag = 'manager' ";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(project.getProjectID()));
            resultSet = statement.executeQuery();
            
            Account account;
            while (resultSet.next()){
                account = new Account();
                account.setAccountID(resultSet.getInt("accountID"));
                account.setAccountName(resultSet.getString("accountName"));
                
                managers.add(account);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return managers;
    }
    
    public static ArrayList<Sprint> getSprints(int projectID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Sprint> sprints = new ArrayList();
        
        String query = "SELECT * FROM sprint WHERE projectID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(projectID));
            resultSet = statement.executeQuery();
            
            Sprint sprint;
            while (resultSet.next()){
                sprint = new Sprint();
                sprint.setSprintID(resultSet.getInt("sprintID"));
                sprint.setSprintNum(resultSet.getInt("sprintNum"));
                sprint.setSprintName(resultSet.getString("sprintName"));
                sprint.setSprintStartDate(resultSet.getString("sprintStart"));
                sprint.setSprintEndDate(resultSet.getString("sprintEnd"));
                sprint.stories = getStories(sprint.getSprintID());
                
                sprints.add(sprint);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return sprints;
    }
    
    public static ArrayList<Story> getStories(int sprintID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Story> stories = new ArrayList();
        
        String query = "SELECT * FROM stories WHERE sprintID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(sprintID));
            resultSet = statement.executeQuery();
            
            Story story;
            while (resultSet.next()){
                story = new Story();
                story.setStoryID(resultSet.getInt("storyID"));
                story.setStoryName(resultSet.getString("storyName"));
                story.setStoryPriority(resultSet.getInt("storyPriority"));
                story.tasks = getTasks(story.getStoryID());
                
                stories.add(story);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return stories;
    }
    
    public static ArrayList<StoryTask> getTasks(int storyID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<StoryTask> tasks = new ArrayList();
        
        String query = "SELECT * FROM tasks WHERE storyID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(storyID));
            resultSet = statement.executeQuery();
            
            StoryTask task;
            while (resultSet.next()){
                task = new StoryTask();
                task.setTaskID(resultSet.getInt("taskID"));
                task.setTaskName(resultSet.getString("taskName"));
                task.setTaskPriority(resultSet.getInt("taskPriority"));
                task.setTaskTime(resultSet.getInt("taskTime"));
                task.setTaskDetails(resultSet.getString("taskDetails"));
                int completedInt = resultSet.getInt("taskCompleted");
                if (completedInt > 0){
                    task.setTaskCompleted(true);
                } else {
                    task.setTaskCompleted(false);
                }
                
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return tasks;
    }
}

