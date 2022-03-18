"use strict";

$(document).ready(() => {

});

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
