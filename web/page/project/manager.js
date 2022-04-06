"use strict";

$(document).ready(() => {
    $("#newSprintButton").click(showSprintForm);

    $("#newContributorButton").click(showContributorForm);

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

    $("#sprintOverview").empty().append(sprintHtml);
    sprintList.forEach(displaySprint);

    showStoryForm(sprintList);
    showTaskForm(sprintList);
    showEditSprintForm(sprintList);
    showEditStoryForm(sprintList);
    showEditTaskForm(sprintList);
    ;
}

function displaySprint(element) {
    var html = `
        <div class="sprintCard" id="sprintCard${element.sprintID}">
            <div class="sprintCardHeader">
                <h2 class="sprintName" id="sprintName${element.sprintID}"> ${element.sprintName} </h2>
                <p class="sprintDate" id="sprintDates${element.sprintID}"> ${element.sprintStartDate} to ${element.sprintEndDate} </p>
                <span id="editSprintButton${element.sprintID}"><img src="resources/editIcon.png"  class="editIcon" alt="Icon to edit sprint information"></span> 
                <span id="deleteSprintButton${element.sprintID}"><img src="resources/deleteIcon.png" class="deleteIcon" alt="Icon to delete sprint information"></span>
            </div>
        </div>`;
    $("#sprintOverview").append(html);

    var sprintCard = $(`#sprintCard${element.sprintID}`);
    element.stories.forEach(function (element) {
        displayStory(element, sprintCard);
    });

    html = `<button class="styledButton" id="newStoryButton${element.sprintID}" data-sprintid="${element.sprintID}">New Story</button>`;
    sprintCard.append(html);
}

function displayStory(storyElement, sprintCard) {
    var html = `
        <div class="storyCard">
            <div class="storyCardHeader">
                <h3 class="storyName">Story: ${storyElement.storyName} </h3>
                <button class="styledButton" id="newTaskButton${storyElement.storyID}" data-storyid="${storyElement.storyID}">New Task</button>
                <span id="editStoryButton${storyElement.storyID}"><img src="resources/editIcon.png"  class="editIcon" alt="Icon to edit story information"></span> 
                <span id="deleteStoryButton${storyElement.storyID}"><img src="resources/deleteIcon.png" class="deleteIcon" alt="Icon to delete story information"></span>
            </div>
            <div>
                <table class="stylizedTable" id="taskTable${storyElement.storyID}">
                    <tr>
                        <th>To-Do</th>
                        <th>Doing</th>
                        <th>Done</th>
                    </tr>
                </table>
            </div>
        </div>`;

    sprintCard.append(html);

    var taskTable = $(`#taskTable${storyElement.storyID}`);
    storyElement.tasks.forEach(function (taskElement) {
        displayTask(taskElement, taskTable, storyElement.storyID);
    });
}

function displayTask(taskElement, taskTable, storyID) {
    var html = `
        <tr>
            <td>
                <div id="editTaskLink${taskElement.taskID}" class="taskCard" data-storyid="${storyID}">
                    <h3>${taskElement.taskName}</h3>
                    <p>${taskElement.taskDetails}<p>
                </div>
            </td>
        </tr>`;

    taskTable.append(html);

}

function showSprintForm() {
    $("#mainModal").html(sprintFormModal);

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

    $("#sprintCreateButton").click(createSprint);

    $("#modalCloseButton").click(() => {
        $("#mainModal").fadeOut(500);
    });

    $("#mainModal").fadeIn(200);
}

function showContributorForm() {
    $("#mainModal").html(
            `<div id="modalBox" class="modalContent">
            <span id="modalCloseButton" class="closeButton">&times;</span>
            <div id="modalContent">
                <h2>Add A Contributer</h2><br>
    
                <label for="contributerName">Contributer Name: </label>
                <input type="text" name="contributerName" id="contributerName"><br><br>

                <button class="styledButton" id="contributorAddButton">Add Contributer</button>
            </div>
        </div>`);

    $("#contributorAddButton").click(addContributor);
    $("#modalCloseButton").click(() => {
        $("#mainModal").fadeOut(500);
    });

    $("#mainModal").fadeIn(200);
}

function showStoryForm(sprintList) {
    sprintList.forEach((sprint) => {
        //Creates the click event for the create user story form
        $("#newStoryButton" + sprint.sprintID).click(function () {

            $("#mainModal").html(storyFormModal);

            $("#storyCreateButton").attr('data-sprintid', $(this).attr("data-sprintid"));
            $("#storyCreateButton").click(createStory);

            $("#modalCloseButton").click(() => {
                $("#mainModal").fadeOut(500);
            });

            $("#mainModal").fadeIn(200);
        });
    });
}

function showTaskForm(sprintList) {
    sprintList.forEach((sprint) => {
        // Creates the click event for the create task form
        sprint.stories.forEach((story) => {
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
        $("#editSprintButton" + sprint.sprintID).click(function () {
            $("#mainModal").html(
                    `<div id="modalBox" class="modalContent">
                    <span id="modalCloseButton" class="closeButton">&times;</span>
                    <div id="modalContent">
                        <h2>Edit ${sprint.sprintName}</h2><br>
                        
                        <input type="hidden" name="sprintID" id="editSprintID" value="${sprint.sprintID}">
                        
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

            $("#completeSprintEditButton").click(editSprint);

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
            $("#editStoryButton" + story.storyID).click(function () {
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

function showEditTaskForm(sprintList) {

    sprintList.forEach((sprint) => {

        sprint.stories.forEach((story) => {

            story.tasks.forEach((task) => {
                $("#editTaskLink" + task.taskID).click(function () {
                    $("#mainModal").html(
                            `<div id="modalBox" class="modalContent">
                        <span id="modalCloseButton" class="closeButton">&times;</span>
                        <div id="modalContent">
                            <h2>Edit ${task.taskName}</h2><br>

                            <input type="hidden" id="editTaskID" value="${task.taskID}">

                            <label for="editedTaskName">Task Name: </label>
                            <input type="text" name="editedTaskName" id="editedTaskName" value="${task.taskName}"><br><br>

                            <label for="editTaskStoryRelation">Story that this Task is linked to:</label>
                            <select name="editTaskStoryRelation" id="editTaskStoryRelation">
                                <option value="${story.storyID}">${story.storyName}</option>
                            </select><br><br>

                            <label for="editedTaskDetails">Task Details: </label>
                            <input type="text" name="editedTaskDetails" id="editedTaskDetails" value="${task.taskDetails}"><br><br>

                            <label for="editedTaskPriority">Task Priority: </label>
                            <select name="editedTaskPriority" id="editedTaskPriority">
                                <option value="${task.taskPriority}">${task.taskPriority}</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select><br><br>

                            <label for="editedTaskTime">Task Time (In Hours): </label>
                            <input type="text" name="editedTaskTime" id="editedTaskTime" value="${task.taskTime}"> <br> <br>

                            <button class="styledButton" id="completeTaskEdit">Edit Task</button>

                            <button class="styledButton" id="completeTheTask" data-storyid="${story.storyID}">Complete Task</button>

                            <button class="styledButton" id="deleteTask" data-storyid="${story.storyID}">Delete Task</button>
                        </div>
                        </div>`
                            );

                    appendTaskStoryOptions(sprintList);

                    $("#completeTaskEdit").click(editTask);

                    $("#completeTheTask").click(completeTask);

                    $("#modalCloseButton").click(() => {
                        $("#mainModal").fadeOut(500);
                    });

                    $("#mainModal").fadeIn(200);
                });
            });
        });
    });

}

function updateSprint(updatedSprint) {
    $("#sprintName" + updatedSprint.sprintID).val(updatedSprint.sprintName);
    $("#sprintDates" + updatedSprint.sprintID).val(updatedSprint.sprintStartDate + " to " + updatedSprint.sprintEndDate);
}

function updateTasks(taskList, storyID) {
    $("#taskTable" + storyID).empty();

    var taskTableHTML =
            `
            <tr>
                <th>To-Do</th>
                <th>Doing</th>
                <th>Done</th>
            </tr>
            `;

    taskList.forEach((task) => {
        taskTableHTML +=
                `
                <tr>
                    <td>
                        <div id="editTaskLink${task.taskID}" class="taskCard" data-storyid="${storyID}">
                            <h3>${task.taskName}</h3>
                            <p>${task.taskDetails}<p>
                        </div>
                    </td>
                </tr>
               `;
    });

    $("#taskTable" + storyID).append(taskTableHTML);

}

function createTask() {
    $("#taskCreateButton").attr('disabled', true);
    ajaxCall('Task',
            {'projectID': project.projectID,
                'storyID': $(this).attr("data-storyid"),
                'taskName': $("#newTaskName").val(),
                'taskDetails': $("#newTaskDetails").val(),
                'taskTime': $("#newTaskTime").val(),
                'taskPriority': $("#taskPriorityLevel option:selected").val()},
            'POST', (result) => {
        $("#taskCreateButton").attr('disabled', false);
        $("#mainModal").fadeOut(500);
        var editedProject = JSON.parse(result);
        displayProject(editedProject.sprints);
    });
}

function editTask() {
    $("#completeTaskEdit").attr('disabled', true);
    ajaxCall('TaskEdit',
            {'projectID': project.projectID,
                'editedTaskID': $("#editTaskID").val(),
                'editedTaskName': $("#editedTaskName").val(),
                'editedTaskStoryRelation': $("#editTaskStoryRelation").val(),
                'editedTaskDetails': $("#editedTaskDetails").val(),
                'editedTaskTime': $("#editedTaskTime").val(),
                'editedTaskPriority': $("#editedTaskPriority option:selected").val()},
            'Post', (result) => {
                $("#completeTaskEdit").attr('disabled', false);
                var editedProject = JSON.parse(result);
                $("#mainModal").fadeOut(500);
                displayProject(editedProject.sprints);
    });
}

function completeTask() {
    ajaxCall('TaskComplete',
            {'projectID': project.projectID,
                'completedTaskID': $("#editTaskID").val(),
                'completedTaskTime': $("#editedTaskTime").val(),
                'completedTaskPriority': $("#editedTaskPriority option:selected").val()},
            'POST', () => {
        $("#mainModal").fadeOut(500);
            });
}

function createSprint() {
    $("#sprintCreateButton").attr('disabled', true);
    ajaxCall('Sprint',
            {'projectID': project.projectID,
                'storyID': $(this).attr("data-storyid"),
                'sprintNum': $("#sprintNumber option:selected").val(),
                'sprintName': $("#sprintName").val(),
                'sprintStartDate': $("#sprintStartDate").val(),
                'sprintEndDate': $("#sprintEndDate").val()},
            'POST', (result) => {
        $("#sprintCreateButton").attr('disabled', false);
        $("#mainModal").fadeOut(500);
        displayProject(JSON.parse(result));
    });
}

function addContributor() {
    $("#contributorAddButton").attr('disabled', true);
    ajaxCall('Contributor', {
        'projectID': project.projectID,
        'contributerName': $("#contributerName").val()},
            'POST', (result) => {
        $("#contributorAddButton").attr('disabled', false);
        $("#mainModal").fadeOut(500);
        var contributorList = JSON.parse(result);
        $("#contributor").empty();
        $("#contributor").append("<tr><th>Contributors</th></tr>");
        contributorList.forEach((contributor) => {
            $("#contributor").append(`<tr><td>${contributor.accountName}</td></tr>`);
        });
    });
}

function editSprint() {
    $("#completeSprintEdit").attr('disabled', true);
    ajaxCall('SprintEdit',
            {'projectID': project.projectID,
                'sprintID': $("#editSprintID").val(),
                'sprintNumber': $("#editedSprintNumber").val(),
                'sprintName': $("#editedSprintName").val(),
                'sprintStartDate': $("#editedSprintStartDate").val(),
                'sprintEndDate': $("#editedSprintEndDate").val()},
            'POST', (result) => {
        $("#completeSprintEdit").attr('disabled', false);
        var editedProject = JSON.parse(result);
        console.log(editedProject);
        $("#mainModal").fadeOut(500);
        displayProject(editedProject.sprints);
    });
}

function createStory() {
    $("#storyCreateButton").attr('disabled', true);
    ajaxCall('Story',
            {'storyName': $("#newStoryName").val(),
                'sprintID': $(this).attr("data-sprintid"),
                'storyPriority': $("#storyPriorityLevel option:selected").val()},
            'POST', (result) => {
        $("#storyCreateButton").attr('disable', false);
        $("#mainModal").fadeOut(500);
        updateStories($(this).attr("data-sprintid"), result);
    });
}

function editStory() {
    $("#completeStoryEdit").attr('disabled', true);
    ajaxCall('StoryEdit',
            {'projectID': project.projectID,
                'editedSprint': $("#editStorySprintRelation").val(),
                'editStoryID': $("#editStoryID").val(),
                'editedStoryName': $("#editedStoryName").val(),
                'editedStoryPriorityLevel': $("#editedStoryPriority option:selected").val()},
            'POST', (result => {
                $("#completeStoryEdit").attr('disabled', false);
                var editedProject = JSON.parse(result);
                $("#mainModal").fadeOut(500);
                displayProject(editedProject.sprints);
            })
            );
}

function updateStories(sprintID, storyList) {
    console.log(sprintID);
    console.log(storyList);
}

function appendStorySprintOptions(sprintList) {
    sprintList.forEach((sprint) => {
        var o = new Option(sprint.sprintName, sprint.sprintID);

        $(o).html(sprint.sprintName);

        $("#editStorySprintRelation").append(o);
    });
}

function appendTaskStoryOptions(sprintList) {
    sprintList.forEach((sprint) => {
        sprint.stories.forEach((story) => {
            var o = new Option(story.storyName, story.storyID);

            $(o).html(story.storyName);

            $("#editTaskStoryRelation").append(o);
        });
    });
}

var ajaxCall = (url, data, type, callback) => {
    $.ajax({
        type: type,
        url: url,
        data: data,
        dataType: "JSON",
        success: callback,
        error: function (jqXHR, ex) {
            console.log(jqXHR);
        }
    });
};

var contributorFormModal = `
    <div id="modalBox" class="modalContent">
        <span id="modalCloseButton" class="closeButton">&times;</span>
        <div id="modalContent">
            <h2>Add A Contributer</h2><br>

            <label for="contributerName">Contributer Name: </label>
            <input type="text" name="contributerName" id="contributerName"><br><br>

            <button class="styledButton" id="contributerAddButton">Add Contributer</button>
        </div>
    </div>`;

var sprintFormModal = `
    <div id="modalBox" class="modalContent">
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
    </div>`;

var storyFormModal = `
    <div id="modalBox" class="modalContent">
            <span id="modalCloseButton" class="closeButton">&times;</span>
            <div id="modalContent">
                <h2>Add Story</h2><br>

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
        </div>`;
