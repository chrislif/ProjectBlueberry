package model;

import java.util.ArrayList;

public class Story {
    private int storyID;
    private String storyName;
    private int storyPriority;
    public ArrayList<StoryTask> tasks;

    public Story() {
    }

    public Story(int storyID, String storyName, int storyPriority) {
        this.storyID = storyID;
        this.storyName = storyName;
        this.storyPriority = storyPriority;
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

    public int getStoryPriority() {
        return storyPriority;
    }

    public void setStoryPriority(int storyPriority) {
        this.storyPriority = storyPriority;
    }
}
