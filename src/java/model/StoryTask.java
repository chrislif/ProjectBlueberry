/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jacks
 */
public class StoryTask {
    private int taskID;
    private String taskName;
    private int taskPriority;
    private int taskTime;
    private String taskDetails;
    private Boolean taskCompleted;

    public StoryTask() {
    }

    public StoryTask(int taskID, String taskName, int taskPriority, int taskTime, String taskDetails, Boolean taskCompleted) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskPriority = taskPriority;
        this.taskTime = taskTime;
        this.taskDetails = taskDetails;
        this.taskCompleted = taskCompleted;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public int getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(int taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public Boolean getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(Boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }
}
