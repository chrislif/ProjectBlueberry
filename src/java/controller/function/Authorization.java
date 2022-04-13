package controller.function;

import data.AccountDB;
import data.AuthDB;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import model.Account;
import model.Project;

public class Authorization {

    public static Boolean IsValidLogin(String email, String password, ArrayList<String> errorList) {
        Boolean isValid = true;

        if (password.isEmpty()) {
            errorList.add("Please enter a Password");
            isValid = false;
        }

        if (email.isEmpty()) {
            errorList.add("Please enter a Email");
            isValid = false;
        }

        return isValid;
    }

    public static Boolean IsValidRegisteration(String accountName, String password, String email, ArrayList<String> errorList) {
        Boolean isValid = true;


            if (accountName == null) {
                errorList.add("Please enter a Username");
                isValid = false;
            }

            if (password == null) {
                errorList.add("Please enter a Password");
                isValid = false;
            }

            if (email == null) {
                errorList.add("Please enter a Email");
                isValid = false;
            }

            try {
                Account account = AccountDB.getAccount(accountName);
                if (AuthDB.doesUserExist(email)) {
                    errorList.add("Error Email invalid");
                    isValid = false;
                } else if (account != null) {
                    errorList.add("Error User Name invalid");
                    isValid = false;
                }
            } catch (SQLException ex) {
                errorList.add(ex.getMessage());
                isValid = false;
            }
        return isValid;
    }

    public static Account authorizeUser(String email, String password, ArrayList<String> errorList) {
        try {
            return AuthDB.loginUser(email, password);
        } catch (SQLException ex) {
            errorList.add("Invalid Credentials");
            //errorList.add(ex.getMessage());
            return null;
        }
    }

    public static Boolean accountIsAdmin(Account user, ArrayList<String> errorList) {
        try {
            return AuthDB.isAdmin(user.getAccountID());
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }

    public static Account RegisterUser(String email, String password, String passwordCheck, String accountName, ArrayList<String> errorList) {
        if (!password.equals(passwordCheck)) {
            errorList.add("Password do not match, please reenter");
            return null;
        }

        try {
            if (AuthDB.doesUserExist(email)) {
                errorList.add("Email invalid");
                return null;
            }
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }

        Account user = new Account();
        user.setAccountName(accountName);
        user.setEmail(email);

        String hash;
        String salt = randomSalt();

        try {
            hash = AuthDB.hashPassword(password, salt);
        } catch (NoSuchAlgorithmException ex) {
            errorList.add("Error: Unable to encrypt password");
            return null;
        }

        try {
            String newUserIDString = AuthDB.createAccount(user, salt, hash);
            user.setAccountID(Integer.parseInt(newUserIDString));
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }

        return user;
    }

    public static Boolean isContributerOnProject(Project project, Account account) {
        try {
            return AccountDB.isContributor(account, project);
        } catch (SQLException ex) {
            String mess = ex.getMessage();
            return null;
        }
    }

    public static String randomSalt() {
        String alphanumericList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * alphanumericList.length());
            salt.append(alphanumericList.charAt(index));
        }
        return salt.toString();
    }

}
