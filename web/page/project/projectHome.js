"use strict";

$(document).ready(() => {
    $("#newSprintButton").click(showSprintForm);
    $("#sprintCancelButton").click(hideSprintForm);
    
    console.log(project);
    
    $("#projectTitle").html(`<h1>Project: ` + project.projectName + `</h1>`);
    
    var contributorHtml = `
        <br>
        <div class="dualTableWrapper">
            <div class="dualTableContent"> 
                <table class="contributorTable">
                    <tr>
                        <th>Managers</th>
                    </tr>`;
    project.managers.forEach((manager) => {
        contributorHtml += "<tr><td>" + manager.accountName + "</td></tr>";
    });
    
    contributorHtml += `
                </table>
            </div>
            <div class="dualTableContent"> 
                <table class="contributorTable">
                    <tr>
                        <th>Contributors</th>
                    </tr>`
    
    project.contributors.forEach((contributor) => {
        contributorHtml += "<tr><td>" + contributor.accountName + "</td></tr>";
    });
    
    contributorHtml += `
                </table>
            </div>
        </div>`;
    
    $("#projectOverview").html(`
        <p>
            ID: ` + project.projectID + `
        </p>` + contributorHtml);
});

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
