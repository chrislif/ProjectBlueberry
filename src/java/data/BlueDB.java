/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import model.Account;
import model.Project;

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
                //project.setContributors(getContributers(project));
                
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
    
    public static ArrayList<Account> getContributers(Project project) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Account> contributers = new ArrayList();
        
        String query = "SELECT account.accountID, account.accountName, projectPeople.tag, project.projectID FROM project "
                + "INNER JOIN projectPeople ON project.projectID = projectPeople.projectID "
                + "INNER JOIN account ON account.accountID = projectPeople.accountID "
                + "WHERE project.projectID = ? AND tag = 'contributer' ";
        
        try{
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
}
