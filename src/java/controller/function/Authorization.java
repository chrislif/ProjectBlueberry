/*

 */
package controller.function;

import data.AuthDB;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import model.Account;

/**
 *
 * @author chris
 */
public class Authorization {
    
    public static Boolean IsValidLogin(String username, String password, String email, ArrayList<String> errorList) {
        Boolean isValid = true;

        if (username.isEmpty()) {
            errorList.add("Please enter a Username");
            isValid = false;
        }

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
    
    public static Account RegisterUser(String email, String password, String passwordCheck, String username, ArrayList <String> errorList){
        if (!password.equals(passwordCheck)) {
            errorList.add("Password do not match, please reenter");
            return null;
        }
        
        try {
            if (AuthDB.doesUserExist(username)) {
                errorList.add("Username invalid");
                return null;
            }
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
        
        Account user = new Account();
        user.setUserName(username);

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
        }
        catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
        
        return user;
    }
    
    public static String randomSalt(){
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
