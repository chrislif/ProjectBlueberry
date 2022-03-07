/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jacks
 */
public class Sprint {
    private int sprintID;
    private String sprintName;
    private LocalDate sprintStartDate;
    private LocalDate sprintEndDate;
    public ArrayList<Story> stories;

    public Sprint() {
    }

    public Sprint(int sprintID, String sprintName, LocalDate sprintStartDate, LocalDate sprintEndDate, ArrayList<Story> stories) {
        this.sprintID = sprintID;
        this.sprintName = sprintName;
        this.sprintStartDate = sprintStartDate;
        this.sprintEndDate = sprintEndDate;
        this.stories = stories;
    }

    public int getSprintID() {
        return sprintID;
    }

    public void setSprintID(int sprintID) {
        this.sprintID = sprintID;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public LocalDate getSprintStartDate() {
        return sprintStartDate;
    }

    public void setSprintStartDate(LocalDate sprintStartDate) {
        this.sprintStartDate = sprintStartDate;
    }

    public LocalDate getSprintEndDate() {
        return sprintEndDate;
    }

    public void setSprintEndDate(LocalDate sprintEndDate) {
        this.sprintEndDate = sprintEndDate;
    }

    public ArrayList<Story> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Story> stories) {
        this.stories = stories;
    }

    
    
    
}
