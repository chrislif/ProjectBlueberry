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
import model.StoryTask;

/**
 *
 * @author al725845
 */
public class TaskDB {

    public static void createTask(StoryTask task, int storyID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO tasks (taskName, storyID, taskPriority, taskTime, taskDetails, taskCompleted)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, task.getTaskName());
            statement.setInt(2, storyID);
            statement.setInt(3, task.getTaskPriority());
            statement.setInt(4, task.getTaskTime());
            statement.setString(5, task.getTaskDetails());
            statement.setBoolean(6, task.getTaskCompleted());

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

    public static int updateTaskName(int taskID, String name) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskName = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);            
            statement.setInt(2, taskID);

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

    public static int updateTaskDetails(int taskID, String details) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskDetails = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, details);            
            statement.setInt(2, taskID);

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

    public static int updateTaskPriority(int taskID, int priority) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskPriority = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, priority);            
            statement.setInt(2, taskID);

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

    public static int updateTaskTime(int taskID, int time) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskTime = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, time);            
            statement.setInt(2, taskID);

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

    public static int updateTaskCompleted(int taskID, Boolean completed) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET taskCompleted = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setBoolean(1, completed);            
            statement.setInt(2, taskID);

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

    public static int updateTaskCompleted(int taskID, int storyID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE tasks SET soryID = ? WHERE taskID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, storyID);            
            statement.setInt(2, taskID);

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
                int completedInt = resultSet.getInt("taskCompleted");
                if (completedInt > 0) {
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
