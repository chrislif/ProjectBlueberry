"use strict";

$(document).ready(() => {
    $("#newStoryButton").click(showStoryForm);
    $("#storyCancelButton").click(hideStoryForm);
    $("#storyCreateButton").click(createStory);
    
});

function showStoryForm() {
    $("#userStoryForm").slideDown(100);
    $("#newStoryButton").hide();
}

function hideStoryForm() {
    $("#userStoryForm").slideUp(100);
    $("#newStoryButton").show();
}

function createStory() {
    ajaxGet('private', {'action': 'createStory',
                        'projectID': project.projectID,
                        'storyName': $("#newStoryName").val(),
                        'sprintNum': $("#storySprintNum option:selected").val(),
                        'storyPriority': $("#storyPriorityLevel option:selected").val()},
                            (result) => {
        hideStoryForm();
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
            console.log(ex);
        }
    });
};
