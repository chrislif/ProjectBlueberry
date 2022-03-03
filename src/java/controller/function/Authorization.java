/*

 */
package controller.function;

import data.AuthDB;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class Authorization {
    
    public static String testDBConnection() {
        try {
            Boolean flag = AuthDB.createAccount("Chris");
            if (flag) {
                return "yay";
            }
            else {
                return "boo";
            }
        }
        catch (SQLException ex) {
            return ex.getMessage();
        }
    }
    
}
