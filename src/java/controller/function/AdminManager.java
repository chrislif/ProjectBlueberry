package controller.function;

import data.AccountDB;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;


public class AdminManager {
    public static ArrayList<Account> GetAllAccounts(){
        try{
            return AccountDB.getAccounts();
        } catch (SQLException ex) {
            return null;
        }
    }
}
