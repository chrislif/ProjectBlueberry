/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
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
public class ProjectDB {

    public static String createProject(String name, String creationDate) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String keyValue = "";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "INSERT INTO project (projectName, creationDate)"
                + "VALUES (?, ?)";

        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, creationDate);

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.next();
                    keyValue = resultSet.getString(1);

                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return keyValue;
    }

    public static void insertContributer(int projectID, int accountID, String tag) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO projectPeople (projectID, accountID, tag)"
                + "VALUES (?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, projectID);
            statement.setInt(2, accountID);
            statement.setString(3, tag);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException ex) {
                throw ex;
            }
        }
    }
    
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
                project.sprints = SprintDB.getSprints(project.getProjectID());
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
    
    public static void updateProjectName(Project project, String name) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        String query = "UPDATE project SET projectName = ? where projectID = ?";
        
        try{
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, project.getProjectID());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            try {
                if (resultSet != null && statement != null) {
                    resultSet.close();
                    statement.close();
                }
                pool.freeConnection(connection);
            } catch (SQLException e) {
                throw e;
            }
        }
    }
}
