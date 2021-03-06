package model;

public class Account {
    private int accountID;
    private String accountName;
    private String email;
    private int accountXP;
    private Boolean isAdmin;

    public Account() {
        this.isAdmin = false;
    }

    public Account(int accountID, String accountName, String email, int accountXP) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.email = email;
        this.accountXP = accountXP;
        this.isAdmin = false;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}
