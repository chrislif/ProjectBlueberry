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
    ajaxGet("Private", {'action': 'createProject','projectName': $("#projectNameText").val()}, (result) => {
        hideProjectForm();      
        retrieveProjects();
    });
}

function retrieveProjects() {
    ajaxGet("Private", {'action': 'getProjects'}, (result) => {
        projectList = JSON.parse(result);
        
        $("#projectTable").html("");
        projectList.forEach(element => (
            $("#projectTable").append(
                `<form action="Private" method="post">
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

function hideProjectForm() {
    $("#projectNameText").val("");
    $("#projectForm").slideUp(100);
    $("#projectNewButton").slideDown(100);
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
