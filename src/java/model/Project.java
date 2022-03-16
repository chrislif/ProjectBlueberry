/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jacks
 */
public class Project {
    private int projectID;
    private String projectName;
    private String projectCreationDate;
    public ArrayList<Account> contributors;
    public ArrayList<Account> managers;
    public ArrayList<Sprint> sprints;

    public Project() { }

    public Project(int projectID, String projectName, String projectCreationDate, ArrayList<Account> contributors, ArrayList<Account> managers, ArrayList<Sprint> sprints) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectCreationDate = projectCreationDate;
        this.contributors = contributors;
        this.managers = managers;
        this.sprints = sprints;
    }

    public String getProjectCreationDate() {
        return projectCreationDate;
    }

    public void setProjectCreationDate(String projectCreationDate) {
        this.projectCreationDate = projectCreationDate;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int ProjectID) {
        this.projectID = ProjectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
