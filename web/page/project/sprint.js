"use strict";

$(document).ready(() => {
    $("#newSprintButton").click(showSprintForm);
    $("#sprintCancelButton").click(hideSprintForm);
    $("#sprintCreateButton").click(createSprint);
    
    displaySprints(project.sprints);
});

function displaySprints(sprintList) {
    var sprintHtml = `
        <h2>
            Sprint Overview
        </h2>`;
    
    sprintList.forEach((sprint) => {
        console.log(sprint);
        sprintHtml += 
            `<div class="sprintCard">
                <div class="sprintCardHeader">
                    <h2 class="sprintName">` + sprint.sprintName + `</h2>
                    <p class="sprintDate">` + sprint.sprintStartDate + ` to ` + sprint.sprintEndDate + `</p>
                    <h3>`+ sprint.sprintNum + `</h3> 
                </div>
                <div class="storyCard">
                    <h3 class="storyName">` + sprint.sprintName + `</h3>
                </div>
            </div>`;
    });
    
    $("#sprintOverview").empty().append(sprintHtml);
}

function showSprintForm() {
    $("#sprintForm").slideDown(100);
    $("#newSprintButton").hide();

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
}

function hideSprintForm() {
    $("#sprintNumber").val(1);
    $("#sprintName").val("");

    $("#sprintForm").slideUp(100);
    $("#newSprintButton").fadeIn(100);
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
