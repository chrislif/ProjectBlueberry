package model;

public class StoryTask {
    private int taskID;
    private String taskName;
    private int taskPriority;
    private int taskTime;
    private String taskDetails;
    private int taskStatus;
    private Account contributor;

    public StoryTask() {
    }

    public StoryTask(int taskID, String taskName, int taskPriority, int taskTime, String taskDetails, int taskStatus) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskPriority = taskPriority;
        this.taskTime = taskTime;
        this.taskDetails = taskDetails;
        this.taskStatus = taskStatus;
        this.contributor = null;
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

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Account getContributor() {
        return contributor;
    }

    public void setContributor(Account contributor) {
        this.contributor = contributor;
    }
}
