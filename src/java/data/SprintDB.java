/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Sprint;

/**
 *
 * @author al725845
 */
public class SprintDB {

    public static void createSprint(Sprint sprint, int projectID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;

        String query = "INSERT INTO sprint (projectID, sprintNum, sprintName, sprintStart, sprintEnd)"
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, projectID);
            statement.setInt(2, sprint.getSprintNum());
            statement.setString(3, sprint.getSprintName());
            statement.setString(4, sprint.getSprintStartDate());
            statement.setString(5, sprint.getSprintEndDate());

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

    public static int updateSprintName(int sprintID, String name) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET sprintName = ? WHERE sprintID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, sprintID);
            statement.setString(2, name);

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

    public static int updateSprintEnd(int sprintID, Date endDate) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET sprintEnd = ? WHERE sprintID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, sprintID);
            statement.setDate(2, endDate);

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

    public static int updateSprintStart(int sprintID, Date startDate) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET sprintStart = ? WHERE sprintID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, sprintID);
            statement.setDate(2, startDate);

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

    public static int updateSprintNum(int sprintID, int num) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET sprintNum = ? WHERE sprintID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, sprintID);
            statement.setInt(2, num);

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
            while (resultSet.next()) {
                sprint = new Sprint();
                sprint.setSprintID(resultSet.getInt("sprintID"));
                sprint.setSprintNum(resultSet.getInt("sprintNum"));
                sprint.setSprintName(resultSet.getString("sprintName"));
                sprint.setSprintStartDate(resultSet.getString("sprintStart"));
                sprint.setSprintEndDate(resultSet.getString("sprintEnd"));
                sprint.stories = StoryDB.getStories(sprint.getSprintID());

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
}