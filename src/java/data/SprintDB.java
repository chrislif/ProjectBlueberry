package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Sprint;

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

    public static void updateSprintName(int sprintID, String name) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET sprintName = ? WHERE sprintID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);            
            statement.setInt(2, sprintID);


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

    public static void updateSprintEnd(int sprintID, String endDate) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET sprintEnd = ? WHERE sprintID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, endDate);            
            statement.setInt(2, sprintID);

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

    public static void updateSprintStart(int sprintID, String startDate) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET sprintStart = ? WHERE sprintID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, startDate);            
            statement.setInt(2, sprintID);

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

    public static void updateSprintNum(int sprintID, int num) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE sprint SET sprintNum = ? WHERE sprintID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, num);            
            statement.setInt(2, sprintID);

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
    
    public static void deleteSprintByID(int sprintID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        String query = "DELETE FROM sprint WHERE sprintID = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, sprintID);

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
