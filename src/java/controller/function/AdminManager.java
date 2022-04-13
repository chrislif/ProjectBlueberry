package controller.function;

import static controller.function.Authorization.randomSalt;
import data.AccountDB;
import data.AuthDB;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;

public class AdminManager {

    public static ArrayList<Account> GetAllAccounts() {
        try {
            return AccountDB.getAccounts();
        } catch (SQLException ex) {
            return null;
        }
    }

    public static Account updateAccount(Account account, String email, String accountName, String password, ArrayList<String> errorList) {

        try {
            AccountDB.updateAccountEmail(account, email);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }

        account.setEmail(email);

        try {
            AccountDB.updateAccountName(account, accountName);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }

        account.setAccountName(accountName);

        String hash;
        String salt = randomSalt();

        try {
            hash = AuthDB.hashPassword(password, salt);
        } catch (NoSuchAlgorithmException ex) {
            errorList.add("Error: Unable to encrypt password");
            return null;
        }

        try {
            AccountDB.updateAccountPassword(account, hash, salt);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
        }

        return account;
    }
}
