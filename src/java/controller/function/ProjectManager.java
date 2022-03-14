/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.function;

import data.BlueDB;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import model.Project;

/**
 *
 * @author dh687287
 */
public class ProjectManager {
    
    public static ArrayList<Project> retrieveProjects(Account user, ArrayList<String> errorList) {
        try {
            return BlueDB.generateProjectList(user);
        } catch (SQLException ex) {
            errorList.add(ex.getMessage());
            return null;
        }
    }
}
