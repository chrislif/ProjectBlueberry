/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jacks
 */
public class Account {
    private int accountID;
    private String userName;
    private String email;
    private int accountXP;

    public Account() {
    }

    public Account(int accountID, String userName, String email, int accountXP) {
        this.accountID = accountID;
        this.userName = userName;
        this.email = email;
        this.accountXP = accountXP;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccountXP() {
        return accountXP;
    }

    public void setAccountXP(int accountXP) {
        this.accountXP = accountXP;
    }
    
    
}
