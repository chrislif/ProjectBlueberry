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
public class Story {
    private int storyID;
    private String storyName;
    private String storyDescription;
    private int storyPriority;
    public ArrayList<Task> tasks;

    public Story() {
    }

    public Story(int storyID, String storyName, String storyDescription, int storyPriority, ArrayList<Task> tasks) {
        this.storyID = storyID;
        this.storyName = storyName;
        this.storyDescription = storyDescription;
        this.storyPriority = storyPriority;
        this.tasks = tasks;
    }

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryDescription() {
        return storyDescription;
    }

    public void setStoryDescription(String storyDescription) {
        this.storyDescription = storyDescription;
    }

    public int getStoryPriority() {
        return storyPriority;
    }

    public void setStoryPriority(int storyPriority) {
        this.storyPriority = storyPriority;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    
}
