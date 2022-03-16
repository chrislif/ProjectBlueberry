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
import model.Project;

/**
 *
 * @author al725845
 */
public class ProjectDB {
    public static String createProject (String name, String creationDate) throws SQLException{
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
            
        } catch (SQLException ex){
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
    
    public static void insertContributer (int projectID, int accountID, String tag) throws SQLException{
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
        } catch (SQLException ex){
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
}
