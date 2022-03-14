"use strict";

$(document).ready(() => {
   $("#projectNewButton").click(showProjectForm); 
   $("#projectCreateButton").click(createProject);
   
   retrieveProjects();
});
    
function showProjectForm() {
    $("#projectForm").slideDown(100);
    $("#projectNewButton").hide();
}

function createProject() {
    $("#projectForm").slideUp(100);
    $("#projectNewButton").fadeIn(100);
}

function retrieveProjects() {
    
    
    ajaxGet("private", {'action': 'getProjects'}, (result) => {
        $("#projectTable").html(result);
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