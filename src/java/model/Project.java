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
    private int ProjectID;
    private String projectName;
    public ArrayList<Account> contributors;
    public ArrayList<Sprint> sprints;

    public Project() {
    }

    public Project(int ProjectID, String projectName, ArrayList<Account> contributors, ArrayList<Sprint> sprints) {
        this.ProjectID = ProjectID;
        this.projectName = projectName;
        this.contributors = contributors;
        this.sprints = sprints;
    }

    

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ArrayList<Account> getContributors() {
        return contributors;
    }

    public void setContributors(ArrayList<Account> contributors) {
        this.contributors = contributors;
    }

    public ArrayList<Sprint> getSprintID() {
        return sprints;
    }

    public void setSprintID(ArrayList<Sprint> sprintID) {
        this.sprints = sprints;
    }

    
    
    
}
