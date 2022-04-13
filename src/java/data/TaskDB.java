package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import model.StoryTask;

public class TaskDB {

    public static void createTask(StoryTask task, int storyID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO tasks (taskName, storyID, taskPriority, taskTime, taskDetails, taskStatus)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, task.getTaskName());
            statement.setInt(2, storyID);
            statement.setInt(3, task.getTaskPriority());
            statement.setInt(4, task.getTaskTime());
            statement.setString(5, task.getTaskDetails());
            statement.setInt(6, task.getTaskStatus());

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

    public static void updateTaskName(int taskID, String name) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskName = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);            
            statement.setInt(2, taskID);

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

    public static void updateTaskDetails(int taskID, String details) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskDetails = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, details);            
            statement.setInt(2, taskID);

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

    public static void updateTaskPriority(int taskID, int priority) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskPriority = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, priority);            
            statement.setInt(2, taskID);

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

    public static void updateTaskTime(int taskID, int time) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskTime = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, time);            
            statement.setInt(2, taskID);

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

    public static void updateTaskCompleted(int taskID, Boolean completed) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskCompleted = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, completed);            
            statement.setInt(2, taskID);

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

    public static void updateTaskCompleted(int taskID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskStatus = 2 WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);          
            statement.setInt(1, taskID);

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
    public static void assignUser(int userID, int taskID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        String query = "update tasks "
                + "set assignedUserID = ? "
                + "where taskID = ? ";
        
        try {  
            
            statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, taskID);
            
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
    
    public static void deleteTaskByID(StoryTask task) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        String query = "DELETE FROM tasks WHERE taskID = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, task.getTaskID());

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
    
    public static StoryTask getTaskByID(int taskID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StoryTask task = new StoryTask();
        
        String query = "SELECT * FROM tasks WHERE taskID = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, Integer.toString(taskID));
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                
                task = new StoryTask();
                task.setTaskID(resultSet.getInt("taskID"));
                task.setTaskName(resultSet.getString("taskName"));
                task.setTaskPriority(resultSet.getInt("taskPriority"));
                task.setTaskTime(resultSet.getInt("taskTime"));
                task.setTaskDetails(resultSet.getString("taskDetails"));
                task.setTaskStatus(resultSet.getInt("taskStatus"));
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
        return task;
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
            while (resultSet.next()) {
                task = new StoryTask();
                task.setTaskID(resultSet.getInt("taskID"));
                task.setTaskName(resultSet.getString("taskName"));
                task.setTaskPriority(resultSet.getInt("taskPriority"));
                task.setTaskTime(resultSet.getInt("taskTime"));
                task.setTaskDetails(resultSet.getString("taskDetails"));
                task.setTaskStatus(resultSet.getInt("taskStatus"));

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
