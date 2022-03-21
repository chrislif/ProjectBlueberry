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
import java.util.ArrayList;
import model.Story;

/**
 *
 * @author al725845
 */
public class StoryDB {

    public static void createStory(Story story, int sprintID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO stories (sprintID, storyName, storyPriority)"
                + "VALUES (?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, sprintID);
            statement.setString(2, story.getStoryName());
            statement.setInt(3, story.getStoryPriority());

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
    
    public static int updateStoryName(int storyID, String name) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET storyName = ? WHERE storyID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);            
            statement.setInt(2, storyID);


            return statement.executeUpdate();
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
            while (resultSet.next()) {
                story = new Story();
                story.setStoryID(resultSet.getInt("storyID"));
                story.setStoryName(resultSet.getString("storyName"));
                story.setStoryPriority(resultSet.getInt("storyPriority"));
                story.tasks = TaskDB.getTasks(story.getStoryID());

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
}
