"use strict";

$(document).ready(() => {
    $("#newSprintButton").click(showSprintForm);
    $("#sprintCreateButton").click(createSprint);

    $("AddContributerButton").click(showContributerForm);
//    $("#contributerAddButton").click(addContributer);
    
    $(window).click(function (e) {
        if (e.target.id === "mainModal") {
            $("#mainModal").fadeOut(100);
        }
    });

    displayProject(project.sprints);
});

function displayProject(sprintList) {
    var sprintHtml = `
        <h2>
            Sprint Overview
        </h2>`;

    sprintList.forEach((sprint) => {
        sprintHtml += `
            <div class="sprintCard">
                <div class="sprintCardHeader">
                    <h2 class="sprintName" id="sprintName${sprint.sprintID}"> ${sprint.sprintName} </h2>
                    <p class="sprintDate" id="sprintDates${sprint.sprintID}"> ${sprint.sprintStartDate} to ${sprint.sprintEndDate} </p>
                    <span id="editSprintButton${sprint.sprintID}"><img src="resources/editIcon.png"  class="editIcon" alt="Icon to edit sprint information"></span> 
                    <span id="deleteSprintButton${sprint.sprintID}"><img src="resources/deleteIcon.png" class="deleteIcon" alt="Icon to delete sprint information"></span>
                </div>`;

        sprint.stories.forEach((story) => {
            sprintHtml += `
                <div class="storyCard">
                    <div class="storyCardHeader">
                        <h3 class="storyName">Story: ${story.storyName} </h3>
                        <button class="styledButton" id="newTaskButton${story.storyID}" data-storyid="${story.storyID}">New Task</button>
                        <span id="editStoryButton${story.storyID}"><img src="resources/editIcon.png"  class="editIcon" alt="Icon to edit story information"></span> 
                        <span id="deleteStoryButton${story.storyID}"><img src="resources/deleteIcon.png" class="deleteIcon" alt="Icon to delete story information"></span>
                    </div>
                    <table class="stylizedTable" id="taskTable${story.storyID}">
                        <tr>
                            <th>Task Name</th>
                            <th>Task Priority</th>
                            <th>Task Details</th>
                        </tr>`;

            story.tasks.forEach((task) => {
                sprintHtml += `
                        <tr>
                            <td> ${task.taskName} </td>
                            <td> ${task.taskPriority} </td>
                            <td> ${task.taskDetails} </td>
                        </tr>`;
            });

            sprintHtml += `
                    </table>
                    <div>
                        <table class="stylizedTable">
                            <tr>
                                <th>To-Do</th>
                                <th>Doing</th>
                                <th>Done</th>
                            </tr>
                        </table>
                    </div>
                </div>`;
        });

        sprintHtml += `
                <button class="styledButton" id="newStoryButton${sprint.sprintID}" data-sprintid="${sprint.sprintID}">New Story</button>
            </div>`;
    });

    $("#sprintOverview").empty().append(sprintHtml);
    
    showStoryForm(sprintList);
    
    showTaskForm(sprintList);
    
    showEditSprintForm(sprintList);
    
    showEditStoryForm(sprintList);
}

function showSprintForm() {
    $("#mainModal").html(
        `<div id="modalBox" class="modalContent">
            <span id="modalCloseButton" class="closeButton">&times;</span>
            <div id="modalContent">
                <h2>Add A Sprint</h2><br>

                <label for="sprintNumber">Sprint #: </label>
                <select name="sprintNumber" id="sprintNumber">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select><br><br>

                <label for="sprintName">Sprint Name: </label>
                <input type="text" name="sprintName" id="sprintName"><br><br>

                <label for="sprintStartDate">Sprint Start Date: </label>
                <input type="date" id="sprintStartDate" name="sprintStartDate"><br><br>

                <label for="sprintEndDate">Sprint End Date: </label>
                <input type="date" id="sprintEndDate" name="sprintEndDate"><br><br>

                <button class="styledButton" id="sprintCreateButton">Create Sprint</button>
            </div>
        </div>`);

    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();

    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    today = yyyy + '-' + mm + '-' + dd;

    $("#sprintStartDate").val(today);
    $("#sprintEndDate").val(today);

    $("#modalCloseButton").click(() => {
        $("#mainModal").fadeOut(500);
    });

    $("#mainModal").fadeIn(200);
}

function showContributerForm() {
        $("#mainModal").html(
        `<div id="modalBox" class="modalContent">
            <span id="modalCloseButton" class="closeButton">&times;</span>
            <div id="modalContent">
                <h2>Add A Contributer</h2><br>

                <button class="styledButton" id="contributerAddButton">Add Contributer</button>
            </div>
        </div>`);
}

function showStoryForm(sprintList) {
    sprintList.forEach((sprint) => {
        //Creates the click event for the create user story form
        $("#newStoryButton" + sprint.sprintID).click(function () {

            $("#mainModal").html(
                    `<div id="modalBox" class="modalContent">
                        <span id="modalCloseButton" class="closeButton">&times;</span>
                        <div id="modalContent">
                            <h2>Add A Story to ${sprint.sprintName}</h2><br>

                            <label for="storyName">User Story Name: </label>
                            <input type="text" name="storyName" id="newStoryName"> <br> <br>

                            <label for="priorityLevel">Priority: </label>
                            <select name="priorityLevel" id="storyPriorityLevel">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select> <br> <br>

                            <button class="styledButton" id="storyCreateButton" data-sprintid="${$(this).attr("data-sprintid")}">Create Story</button>
                        </div>
                    </div>`);
            
            $("#storyCreateButton").click(createStory);
            
            $("#modalCloseButton").click(() => {
                $("#mainModal").fadeOut(500);
            });

            $("#mainModal").fadeIn(200);
        });
    });
}

function showTaskForm(sprintList){
    sprintList.forEach((sprint) => {
        // Creates the click event for the create task form
        sprint.stories.forEach((story) => {
            console.log(story);
            $("#newTaskButton" + story.storyID).click(function () {

                $("#mainModal").html(
                        `<div id="modalBox" class="modalContent">
                    <span id="modalCloseButton" class="closeButton">&times;</span>
                    <div id="modalContent">
                        <h2>Add a Task to ${story.storyName}</h2><br>
                
                        <label for="taskName">Task Name: </label>
                        <input type="text" name="taskName" id="newTaskName"> <br> <br>
                
                        <label for="taskDetails">Task Details: </label>
                        <input type="text" name="taskDetails" id="newTaskDetails"> <br> <br>
                
                        <label for="taskTime">Task Time (In Hours): </label>
                        <input type="text" name="taskTime" id="newTaskTime"> <br> <br>
                
                        <label for="taskPriorityLevel">Priority: </label>
                        <select name="taskPriorityLevel" id="taskPriorityLevel">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select> <br> <br>
                
                        <button class="styledButton" id="taskCreateButton" data-storyid=${story.storyID}>Create Task</button>
                    </div>
                </div>`
                        );
                
                $("#taskCreateButton").click(createTask);
                
                $("#modalCloseButton").click(() => {
                    $("#mainModal").fadeOut(500);
                });

                $("#mainModal").fadeIn(200);
            });
        });
    });
}

function showEditSprintForm(sprintList) {
    sprintList.forEach((sprint) => {
        $("#editSprintButton" + sprint.sprintID).click(function() {
            $("#mainModal").html(
                `<div id="modalBox" class="modalContent">
                    <span id="modalCloseButton" class="closeButton">&times;</span>
                    <div id="modalContent">
                        <h2>Edit ${sprint.sprintName}</h2><br>

                        <label for="editedSprintNumber">Sprint #: </label>
                        <select name="editedSprintNumber" id="editedSprintNumber">
                            <option value="${sprint.sprintNum}">${sprint.sprintNum}</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select><br><br>

                        <label for="editedSprintName">Sprint Name: </label>
                        <input type="text" name="editedSprintName" id="editedSprintName" value="${sprint.sprintName}"><br><br>

                        <label for="editedSprintStartDate">Sprint Start Date: </label>
                        <input type="date" id="editedSprintStartDate" name="editedSprintStartDate" value="${sprint.sprintStartDate}"><br><br>

                        <label for="editedSprintEndDate">Sprint End Date: </label>
                        <input type="date" id="editedSprintEndDate" name="editedSprintEndDate" value="${sprint.sprintEndDate}"><br><br>

                        <button class="styledButton" id="completeSprintEditButton">Edit Sprint</button>
                    </div>
                </div>`
            );
    
            $("#modalCloseButton").click(() => {
                $("#mainModal").fadeOut(500);
            });

            $("#mainModal").fadeIn(200);
        });
    });
}

function showEditStoryForm(sprintList) {
    sprintList.forEach((sprint) => {

        sprint.stories.forEach((story) => {
            $("#editStoryButton"+story.storyID).click(function () {
                $("#mainModal").html(
                `<div id="modalBox" class="modalContent">
                    <span id="modalCloseButton" class="closeButton">&times;</span>
                    <div id="modalContent">
                        <h2>Edit ${story.storyName}</h2><br>
                        
                        <input type="hidden" id="editStoryID" value="${story.storyID}">
                
                        <label for="editStorySprintRelation">Sprint that this Story is linked to:</label>
                        <select name="editStorySprintRelation" id="editStorySprintRelation">
                            <option value="${sprint.sprintID}">${sprint.sprintName}</option>
                        </select><br><br>
                        
                        <label for="editedStoryName">Story Name: </label>
                        <input type="text" name="editedStoryName" id="editedStoryName" value="${story.storyName}"><br><br>

                        <label for="editedStoryPriority">Story Priority: </label>
                        <select name="editedStoryPriority" id="editedStoryPriority">
                            <option value="${story.storyPriority}">${story.storyPriority}</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select><br><br>

                        <button class="styledButton" id="completeStoryEdit">Edit Story</button>
                    </div>
                </div>`
            );
            
            appendStorySprintOptions(sprintList);
            
            $("#completeStoryEdit").click(editStory);
            
            $("#modalCloseButton").click(() => {
                $("#mainModal").fadeOut(500);
            });

            $("#mainModal").fadeIn(200);
            });
        });
    });
}

function updateSprint(updatedSprint) {
    $("#sprintName"+updatedSprint.sprintID).val(updatedSprint.sprintName);
    $("#sprintDates"+updatedSprint.sprintID).val(updatedSprint.sprintStartDate + " to " + updatedSprint.sprintEndDate);
}

function updateTasks(taskList, storyID){
    $("#taskTable"+storyID).empty();
    
    var taskTableHTML = 
            `
            <tr>
                <th>Task Name</th>
                <th>Task Priority</th>
                <th>Task Details</th>
            </tr>
            `;
    
    taskList.forEach((task) => {
       taskTableHTML += 
               `
                <tr>
                    <td> ${task.taskName} </td>
                    <td> ${task.taskPriority} </td>
                    <td> ${task.taskDetails} </td>
                </tr>
               `; 
    });
    
    $("#taskTable"+storyID).append(taskTableHTML);
            
}

function createTask(){
    ajaxPost('Task', {
        'storyID': $(this).attr("data-storyid"),
        'taskName' : $("#newTaskName").val(),
        'taskDetails' : $("#newTaskDetails").val(),
        'taskTime' : $("#newTaskTime").val(),
        'taskPriority' : $("#taskPriorityLevel option:selected").val()},
            (result) => {
        $("#mainModal").fadeOut(500);
        var taskList = JSON.parse(result);
        updateTasks(taskList, $(this).attr("data-storyid"));
    });
}

function createSprint() {
    ajaxPost('Sprint', {
        'projectID': project.projectID,
        'storyID' : $(this).attr("data-storyid"),
        'sprintNum': $("#sprintNumber option:selected").val(),
        'sprintName': $("#sprintName").val(),
        'sprintStartDate': $("#sprintStartDate").val(),
        'sprintEndDate': $("#sprintEndDate").val()},
            (result) => {
        hideSprintForm();
        displayProject(JSON.parse(result));
    });
}

function editSprint() {
    ajaxPost('Sprint', {'sprintNumber' : $("#editedSprintNumber").val(),
        'sprintName': $("#editedSprintName").val(),
        'sprintStartDate' : $("#editedSprintStartDate").val(),
        'sprintEndDate' : $("#editedSprintEndDate").val()}),
            (result) => {
                $("#mainModal").fadeOut(500);
                updateSprint(result);
            };
}

function createStory() {
    ajaxPost('Story', {  'storyName': $("#newStoryName").val(),
                        'sprintID': $(this).attr("data-sprintid"),
                        'storyPriority': $("#storyPriorityLevel option:selected").val()},
                            (result) => {
        $("#mainModal").fadeOut(500);
        updateStories($(this).attr("data-sprintid"), result);
    });
}

function editStory() {
    ajaxPost('StoryEdit', {'editedSprint' : $("#editStorySprintRelation").val(),
                        'editStoryID' : $("#editStoryID").val(),
                        'editedStoryName' : $("#editedStoryName").val(),
                        'editedStoryPriorityLevel' : $("#editedStoryPriority option:selected").val()},
                        (result => {
                            $("#mainModal").fadeOut(500);
                            console.log(result);
                        })
    );
}

function updateStories(sprintID, storyList) {
    console.log(sprintID);
    console.log(storyList);
}

function appendStorySprintOptions (sprintList) {
    sprintList.forEach((sprint) => {
       var o = new Option(sprint.sprintName, sprint.sprintID);
        
       $(o).html(sprint.sprintName); 
       
       $("#editStorySprintRelation").append(o);
    });
}

var ajaxGet = (url, data, callback) => {
    $.ajax({
        type: "GET",
        url: url,
        data: data,
        dataType: "JSON",
        success: callback,
        error: function (jqXHR, ex) {
            console.log(jqXHR);
        }
    });
};

var ajaxPost = (url, data, callback) => {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "JSON",
        success: callback,
        error: function (jqXHR, ex) {
            console.log(jqXHR);
        }
    });
};
