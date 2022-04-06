"use strict";

var projectList;

$(document).ready(() => {
   $("#projectNewButton").click(showProjectForm); 
   $("#projectCreateButton").click(createProject);
   $("#projectCancelButton").click(hideProjectForm);
   
   retrieveProjects();
});

function showProjectForm() {
    $("#projectForm").slideDown(100);
    $("#projectNewButton").slideUp(100);
}

function createProject() {
    $("#projectCreateButton").prop('disabled', true);
    ajaxCall("Project", {'projectName': $("#projectNameText").val()}, "POST", (result) => {
        $("#projectCreateButton").prop('disabled', false);
        hideProjectForm();      
        retrieveProjects();
    });
}

function retrieveProjects() {
    ajaxCall("ProjectList", {}, "GET", (result) => {
        projectList = JSON.parse(result);
        
        $("#projectTable").html("");
        projectList.forEach(createProjectButton);
    });
}

function createProjectButton(element) {
    $("#projectTable").append(`
        <form action="Project" method="GET">
            <input type="hidden" name="projectID" value="` + element.projectID + `">
            <button type="submit" class="gridSubContent gridButton">
                <div class="gridButtonText">
                    <h3>Project: ${element.projectName}</h3>
                    <p>ID: ${element.projectID}</p>
                    <p>Date: ${element.projectCreationDate}</p>
                </div>
            </button>
        </form>`
    );
}

function hideProjectForm() {
    $("#projectNameText").val("");
    $("#projectForm").slideUp(100);
    $("#projectNewButton").slideDown(100);
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
