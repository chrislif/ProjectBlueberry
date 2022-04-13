"use strict";

$(document).ready(() => {
    
    
    displayProjectDetails();
});

function displayProjectDetails() {
    $("#projectTitle").html(`<h1>Project: ` + project.projectName + `</h1>`);
    
    $("#projectOverview").html(projectHeader);
    
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
