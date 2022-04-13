package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Sprint;
import model.Story;
import model.StoryTask;

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
    
    public static void updateStorySprintID(int storyID, int sprintID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE stories SET sprintID = ? WHERE storyID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, sprintID);            
            statement.setInt(2, storyID);


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
    
    public static void updateStoryName(int storyID, String name) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE stories SET storyName = ? WHERE storyID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);            
            statement.setInt(2, storyID);


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
    
    public static void updateStoryPriority(int storyID, int priority) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE stories SET storyPriority = ? WHERE storyID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, priority);            
            statement.setInt(2, storyID);


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
    
    public static void deleteStoryByID(Story story) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        String query = "DELETE FROM stories WHERE storyID = ?";
        
        for(StoryTask task : story.tasks){
            TaskDB.deleteTaskByID(task);
        }
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, story.getStoryID());

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
    
    public static Story getStoryByID(int storyID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Story story = new Story();
        
        String query = "SELECT * FROM stories WHERE storyID = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(storyID));
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                
                story = new Story();
                story.setStoryID(resultSet.getInt("storyID"));
                story.setStoryName(resultSet.getString("storyName"));
                story.setStoryPriority(resultSet.getInt("storyPriority"));
                story.tasks = TaskDB.getTasks(story.getStoryID());
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
        return story;
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
