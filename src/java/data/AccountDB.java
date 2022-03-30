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
import java.util.Set;
import model.Account;

/**
 *
 * @author al725845
 */
public class AccountDB {
    public static ArrayList<Account> getAccounts() throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        ArrayList<Account> accountList = new ArrayList();
        
        String query = "SELECT accountID, accountName, email, accountXP "
                + "FROM account";
        try{
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            Account account;
            while(resultSet.next()){
                account = new Account();
                account.setAccountID(resultSet.getInt("accountID"));
                account.setAccountName(resultSet.getString("accountName"));
                account.setEmail(resultSet.getString("email"));
                account.setAccountXP(resultSet.getInt("accountXP"));
                
                accountList.add(account);
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
        return accountList;
    }
    
    public static Account getAccount(String accountName) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Account account = new Account();
        
        String query = "SELECT accountID from account where accountName = ?";
        
        try{
            statement = connection.prepareStatement(query);
            statement.setString(1, accountName);
            resultSet = statement.executeQuery();          
            
            resultSet.next();
            
            account.setAccountID(resultSet.getInt("accountID"));
            
            return account;
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
    }

    public static void updateAccountName(Account account, String name) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE account set accountName = ? WHERE accountID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, account.getAccountID());

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
    
    public static void updateAccountEmail(Account account, String email) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE account set email = ? WHERE accountID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setInt(2, account.getAccountID());

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
    
    public static void updateAccountPassword(Account account, String hash, String salt) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "UPDATE account SET salt = ?, hash = ? "
                + "where accountID = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, salt);
            statement.setString(2, hash);
            statement.setInt(3, account.getAccountID());

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
    
    public static int getAccountXP(Account account) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        
        String query = "SELECT accountXP FROM account WHERE accountID = ?";
        
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, account.getAccountID());
            resultSet = statement.executeQuery();
            resultSet.next();
            
            return resultSet.getInt("accountXP");
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
    
    public static void updateAccountXP(int xp, Account account) throws SQLException{
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null; 
        ResultSet resultSet = null;
        int currentXP = getAccountXP(account);
        int newXP = currentXP + xp;
        
        String query = "UPDATE account set accountXP = ? where accountName = ?";
        
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1, newXP);
            statement.setInt(2, account.getAccountXP());
            
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
