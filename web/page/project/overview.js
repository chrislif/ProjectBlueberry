"use strict";

var projectList;

$(document).ready(() => {
   $("#projectNewButton").click(showProjectForm); 
   $("#projectCreateButton").click(createProject);
   $("#projectCancelButton").click(hideProjectForm);
   
   retrieveProjects();
});

class Project {
    constructor(projectID, projectName, creationDate) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.creationDate = creationDate;
    }
}

function showProjectForm() {
    $("#projectForm").slideDown(100);
    $("#projectNewButton").hide();
}

function hideProjectForm() {
    $("#projectName").val("");

    $("#projectForm").slideUp(100);
    $("#projectNewButton").fadeIn(100);
}

function createProject() {
    $("#projectForm").slideUp(100);
    $("#projectNewButton").fadeIn(100);
}

function retrieveProjects() {
    
    
    ajaxGet("private", {'action': 'getProjects'}, (result) => {
        projectList = JSON.parse(result);
        
        projectList.forEach(element => (
            $("#projectTable").append(
                `<form action="private" method="post">
                    <input type="hidden" name="action" value="toProject">
                    <input type="hidden" name="projectID" value="` + element.projectID + `">
                    <button type="submit" class="gridSubContent gridButton">
                        <div class="gridButtonText">
                            <h3>Project: `+ element.projectName + `</h3>
                            <p>ID: ` + element.projectID + `</p>
                            <p>Date: ` + element.projectCreationDate + `</p>
                        </div>
                    </button>
                </form>`
            )
        ));
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
            $("#projectTable").html(jqXHR.responseText);
        }
    });
};