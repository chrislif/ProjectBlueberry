"use strict";

$(document).ready(() => {
    $("#newSprintButton").click(showSprintForm);
    $("#sprintCancelButton").click(hideSprintForm);
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
