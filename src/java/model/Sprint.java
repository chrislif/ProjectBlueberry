package model;

import java.util.ArrayList;

public class Sprint {
    private int sprintID;
    private int sprintNum;
    private String sprintName;
    private String sprintStartDate;
    private String sprintEndDate;
    public ArrayList<Story> stories;

    public Sprint() {
    }

    public Sprint(int sprintID, int sprintNum, String sprintName, String sprintStartDate, String sprintEndDate) {
        this.sprintID = sprintID;
        this.sprintNum = sprintNum;
        this.sprintName = sprintName;
        this.sprintStartDate = sprintStartDate;
        this.sprintEndDate = sprintEndDate;
    }

    public int getSprintID() {
        return sprintID;
    }

    public void setSprintID(int sprintID) {
        this.sprintID = sprintID;
    }

    public int getSprintNum() {
        return sprintNum;
    }

    public void setSprintNum(int sprintNum) {
        this.sprintNum = sprintNum;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public String getSprintStartDate() {
        return sprintStartDate;
    }

    public void setSprintStartDate(String sprintStartDate) {
        this.sprintStartDate = sprintStartDate;
    }

    public String getSprintEndDate() {
        return sprintEndDate;
    }

    public void setSprintEndDate(String sprintEndDate) {
        this.sprintEndDate = sprintEndDate;
    }
}
