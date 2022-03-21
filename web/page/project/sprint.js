"use strict";

$(document).ready(() => {
    $("#newSprintButton").click(showSprintForm);
    $("#sprintCreateButton").click(createSprint);

    $(window).click(function (e) {
        if (e.target.id === "mainModal") {
            $("#mainModal").fadeOut(100);
        }
    });

    displaySprints(project.sprints);
});

function displaySprints(sprintList) {
    var sprintHtml = `
        <h2>
            Sprint Overview
        </h2>`;

    sprintList.forEach((sprint) => {
        console.log(sprint);
        sprintHtml += `
            <div class="sprintCard">
                <div class="sprintCardHeader">
                    <h2 class="sprintName">` + sprint.sprintName + ` </h2>
                    <p class="sprintDate">` + sprint.sprintStartDate + ` to ` + sprint.sprintEndDate + `</p>
                    <span><img src="resources/editIcon.png"  class="editIcon" id="editSprintButton`+sprint.sprintID+`" data-currentsprint="`+sprint+`" alt="Icon to edit sprint information"></span> 
                </div>`;

        sprint.stories.forEach((story) => {
            sprintHtml += `
                <div class="storyCard">
                    <div class="storyCardHeader">
                        <h3 class="storyName">Story: ` + story.storyName + `</h3>
                        <button class="styledButton" id="newTaskButton` + story.storyID + `" data-storyid="` + story.storyID + `">New Task</button>
                    </div>
                    <table class="stylizedTable">
                        <tr>
                            <th>Task Name</th>
                            <th>Task Priority</th>
                            <th>Task Details</th>
                        </tr>`;

            story.tasks.forEach((task) => {
                sprintHtml += `
                        <tr>
                            <td>` + task.taskName + `</td>
                            <td>` + task.taskPriority + `</td>
                            <td>` + task.taskDetails + `</td>
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
                <button class="styledButton" id="newStoryButton` + sprint.sprintID + `" data-sprintid="` + sprint.sprintID + `">New Story</button>
            </div>`;

    });

    $("#sprintOverview").empty().append(sprintHtml);

    sprintList.forEach((sprint) => {
        //Creates the click event for the create user story form
        $("#newStoryButton" + sprint.sprintID).click(function () {
            console.log($(this).attr("data-sprintid"));

            $("#mainModal").html(
                    `<div id="modalBox" class="modalContent">
                        <span id="modalCloseButton" class="closeButton">&times;</span>
                        <div id="modalContent">
                            <h2>Add A Story</h2><br>

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

                            <button class="styledButton" id="storyCreateButton">Create Story</button>
                        </div>
                    </div>`);

            $("#modalCloseButton").click(() => {
                $("#mainModal").fadeOut(500);
            });

            $("#mainModal").fadeIn(200);
        });

        // Creates the click event for the create task form
        sprint.stories.forEach((story) => {
            $("#newTaskButton" + story.storyID).click(function () {

                $("#mainModal").html(
                        `<div id="modalBox" class="modalContent">
                    <span id="modalCloseButton" class="closeButton">&times;</span>
                    <div id="modalContent">
                        <h2>Add a Task to ` + story.storyName + `</h2><br>
                
                        <label for="taskName">Task Name: </label>
                        <input type="text" name="taskName" id="newTaskNAme"> <br> <br>
                
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
                
                        <button class="styledButton" id="taskCreateButton">Create Task</button>
                    </div>
                </div>`
                        );
                
                $("#storyCreateButton").click(createStory());
                
                $("#modalCloseButton").click(() => {
                    $("#mainModal").fadeOut(500);
                });

                $("#mainModal").fadeIn(200);
            });
        });

    });

    sprintList.forEach((sprint) => {
        $("#editSprintButton" + sprint.sprintID).click(function() {
            var currentSprint = $(this).attr("data-currentsprint");
            console.log(currentSprint);
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
                </div>`
            );
        });
    });
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

function showEditSprintForm() {
    
}

function createSprint() {
    ajaxGet('private', {'action': 'createSprint',
        'projectID': project.projectID,
        'sprintNum': $("#sprintNumber option:selected").val(),
        'sprintName': $("#sprintName").val(),
        'sprintStartDate': $("#sprintStartDate").val(),
        'sprintEndDate': $("#sprintEndDate").val()},
            (result) => {
        hideSprintForm();
        displaySprints(JSON.parse(result));
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
