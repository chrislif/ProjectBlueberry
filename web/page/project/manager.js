"use strict";

$(document).ready(() => {
    displayProjectDetails();
    
    $("#newSprintButton").click(showSprintForm);

    $("#newContributorButton").click(showContributorForm);

    $(window).click(function (e) {
        if (e.target.id === "mainModal") {
            $("#mainModal").fadeOut(100);
        }
    });
    displayProject(project.sprints);
});

function displayProjectDetails() {
    $("#projectTitle").html(`<h1 class="projectTitle">Project: ` + project.projectName + `</h1>`
            + `<form action="ProjectEdit" method="POST" class="projectDeleteBtn">
                <input type="hidden" name="projectID" value="${project.projectID}"/>
                <input type="image" src="resources/deleteIcon.png" class="deleteIcon" alt="Icon to delete your whole project"/>
                </form>`);
    
    $("#projectOverview").html(projectHeader);
    
    $("#deleteProjectBtn").click(deleteProject);
    
    var projectManagerTableHtml = ``;
    project.managers.forEach((manager) => {
        projectManagerTableHtml += `
            <tr>
                <td>${manager.accountName}</td>
            </tr>`;
    });
    $("#managersTable").append(projectManagerTableHtml);
    
    
    var projectContribtorTableHtml = ``;
    project.contributors.forEach((contributor) => {
        projectContribtorTableHtml += `
            <tr>
                <td>${contributor.accountName}</td>
            </tr>`;
    });
    $("#contributorsTable").append(projectContribtorTableHtml);
}

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
                <div id="editSprintButton${element.sprintID}"><img src="resources/editIcon.png"  class="editIcon" alt="Icon to edit sprint information"></div> 
                <div data-sprintid="${element.sprintID}" id="deleteSprintButton${element.sprintID}"><img src="resources/deleteIcon.png" class="deleteIcon" alt="Icon to delete sprint information"></div>
            </div>
        </div>`;
    $("#sprintOverview").append(html);

    $("#deleteSprintButton" + element.sprintID).click(deleteSprint);

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
                <div id="editStoryButton${storyElement.storyID}"><img src="resources/editIcon.png"  class="editIcon" alt="Icon to edit story information"></div> 
                <div id="deleteStoryButton${storyElement.storyID}" data-storyid="${storyElement.storyID}"><img src="resources/deleteIcon.png" class="deleteIcon" alt="Icon to delete story information"></div>
            </div>
            <div class="taskManagementContainer">
                <table class="stylizedTaskTable" id="taskTable${storyElement.storyID}ToDo">
                    <tr>
                        <th>To-Do</th>
                    </tr>
                </table>
    
                <table class="stylizedTaskTable" id="taskTable${storyElement.storyID}Doing">
                    <tr>
                        <th>Doing</th>
                    </tr>
                </table>
    
                <table class="stylizedTaskTable" id="taskTable${storyElement.storyID}Done">
                    <tr>
                        <th>Done</th>
                    </tr>
                </table>
            </div>
        </div>`;

    sprintCard.append(html);

    $("#deleteStoryButton" + storyElement.storyID).click(deleteStory);

    var taskTable = $(`#taskTable${storyElement.storyID}`);
    storyElement.tasks.forEach(function (taskElement) {
        if (taskElement.taskStatus === 0){
            taskTable = $(`#taskTable${storyElement.storyID}ToDo`);
            displayTask(taskElement, taskTable, storyElement.storyID);
        } else if (taskElement.taskStatus === 1) {
            taskTable = $(`#taskTable${storyElement.storyID}Doing`);
            displayTask(taskElement, taskTable, storyElement.storyID);
        } else if (taskElement.taskStatus === 2) {
            taskTable = $(`#taskTable${storyElement.storyID}Done`);
            displayTask(taskElement, taskTable, storyElement.storyID);
        }
    });
}

function displayTask(taskElement, taskTable, storyID) {
    var html = `
        <tr>
            <td>
                <div id="editTaskLink${taskElement.taskID}" class="taskCard" data-storyid="${storyID}">
                    <h3>${taskElement.taskName}</h3>
                    <p>${taskElement.taskDetails}<p>
                    <p>${taskElement.contributor.accountName}</p>
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
                    
                            <label for="editTaskContributorRelation">Who is working on this Task:</label>
                            <select name="editTaskContributorRelation" id="editTaskContributorRelation">
                                <option value="0">Unassigned</option>
                            </select><br><br>

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

                            <button class="styledButton" id="deleteTask" data-taskid="${task.taskID}">Delete Task</button>
                        </div>
                        </div>`
                            );
                    appendTaskContributorOptions();

                    appendTaskStoryOptions(sprintList);

                    $("#deleteTask").click(deleteTask);

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
function deleteProject() {
    ajaxCall("ProjectDelete", 
        {"projectID" : project.projectID},
        "Get", (result)=> {
            
        }
    );
}


function createSprint() {
    if ($("#sprintName").val().trim() === "") {
        $("#nameValidation").html("Enter a sprint name");
        console.log("error");
    } else {
        $("#sprintCreateButton").attr('disabled', true);
        ajaxCall('Sprint',
                {'projectID': project.projectID,
                    'storyID': $(this).attr("data-storyid"),
                    'sprintNum': $("#sprintNumber option:selected").val(),
                    'sprintName': $("#sprintName").val(),
                    'sprintStartDate': $("#sprintStartDate").val(),
                    'sprintEndDate': $("#sprintEndDate").val()},
                'POST', (result) => {
            var editedProject = JSON.parse(result);
            $("#sprintCreateButton").attr('disabled', false);
            $("#mainModal").fadeOut(500);
            displayProject(editedProject.sprints);
        });
    }
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

function deleteSprint() {
    ajaxCall('SprintEdit',
            {'projectID': project.projectID,
                'sprintID': $(this).attr('data-sprintid')},
            'GET', (result) => {
        var editedProject = JSON.parse(result);
        displayProject(editedProject.sprints);
    }
    );
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
        displayProject(editedProject.sprints); //Set a length for validation of task components
    });
}

function editTask() {
    $("#completeTaskEdit").attr('disabled', true);
    console.log($("#editTaskContributorRelation option:selected").val());
    ajaxCall('TaskEdit',
            {'projectID': project.projectID,
                'editedTaskID': $("#editTaskID").val(),
                'editedTaskContributorRelation' : $("#editTaskContributorRelation option:selected").val(),
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

function deleteTask() {
    ajaxCall('TaskEdit',
            {'projectID': project.projectID,
                'taskID': $(this).attr('data-taskid')},
            'GET', (result) => {
        var editedProject = JSON.parse(result);
        displayProject(editedProject.sprints);
        $("#mainModal").fadeOut(500);
    }
    );
}

function addContributor() {
    $("#contributorAddButton").attr('disabled', true);
    ajaxCall('Contributor', {
        'projectID': project.projectID,
        'contributerName': $("#contributerName").val()},
            'POST', handleAddContributorResult);
}

function handleAddContributorResult(result) {
    $("#contributorAddButton").attr('disabled', false);
    if (JSON.parse(result) == "false") {
        console.log("didnt work");
    } else {
        $("#mainModal").fadeOut(500);
        
        var contributorList = JSON.parse(result);
        
        console.log(contributorList);
        
        $("#contributorsTable").html("<tr><th>Contributors</th></tr>");
        
        contributorList.forEach((contributor) => {
            $("#contributorsTable").append(`<tr><td>${contributor.accountName}</td></tr>`);
        });
    }
}

function createStory() {
    if ($("#newStoryName").val().trim() === "") {
        $("#storyValidation").html("Enter a story name");
    } else {
        $("#storyCreateButton").attr('disabled', true);
        ajaxCall('Story',
                {'projectID': project.projectID,
                    'storyName': $("#newStoryName").val(),
                    'sprintID': $(this).attr("data-sprintid"),
                    'storyPriority': $("#storyPriorityLevel option:selected").val()},
                'POST', (result) => {
            $("#storyCreateButton").attr('disable', false);
            $("#mainModal").fadeOut(500);
            var editedProject = JSON.parse(result);
            displayProject(editedProject.sprints);
        });
    }
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

function deleteStory() {
    ajaxCall('StoryEdit',
            {'projectID': project.projectID,
                'storyID': $(this).attr('data-storyid')},
            'GET', (result) => {
        var editedProject = JSON.parse(result);
        displayProject(editedProject.sprints);
    }
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

function appendTaskContributorOptions() {
    project.managers.forEach((manager) => {
        var o = new Option(manager.accountName, manager.accountID);

        $(o).html(manager.accountName);

        $("#editTaskContributorRelation").append(o);
    });

    project.contributors.forEach((contributor) => {
        var o = new Option(contributor.accountName, contributor.accountID);

        $(o).html(contributor.accountName);

        $("#editTaskContributorRelation").append(o);
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
            <input type="text" name="sprintName" id="sprintName"><span id="nameValidation" style="color: red"></span><br><br>

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
                <input type="text" name="storyName" id="newStoryName"><span id="storyValidation" style="color: red"></span> <br> <br>

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

var projectHeader = `
    <h2>
        Overview
    </h2>
    <p>
        Project ID:
    </p>
    <div id="projectManageButton">
        <button class="styledButton" id="newSprintButton">New Sprint</button>
        <button class="styledButton" id="newContributorButton">Add Contributor</button>
    </div>

    <div class="dualTableWrapper">
        <div class="dualTableContent"> 
            <table class="stylizedTable" id="managersTable">
                <tr>
                    <th>Managers</th>
                </tr>
            </table>
        </div>
        <div class="dualTableContent"> 
            <table class="stylizedTable" id="contributorsTable">
                <tr>
                    <th>Contributors</th>
                </tr>
            </table>
        </div>
    </div>`;
