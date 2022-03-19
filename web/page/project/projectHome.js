"use strict";

$(document).ready(() => {
    displayProjectDetails();
});

function displayProjectDetails() {
    $("#projectTitle").html(`<h1>Project: ` + project.projectName + `</h1>`);
    
    var contributorHtml = `
        <br>
        <div class="dualTableWrapper">
            <div class="dualTableContent"> 
                <table class="stylizedTable">
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
                <table class="stylizedTable">
                    <tr>
                        <th>Contributors</th>
                    </tr>`;
    
    project.contributors.forEach((contributor) => {
        contributorHtml += "<tr><td>" + contributor.accountName + "</td></tr>";
    });
    
    contributorHtml += `
                </table>
            </div>
        </div>`;
    
    $("#projectOverview").html(`
        <h2>
            Overview
        </h2>
        <p>
            Project ID: ` + project.projectID + `
        </p>
         <button class="styledButton" id="newSprintButton">New Sprint</button>
        ` + contributorHtml);
}
