"use strict";

$(document).ready(() => {

});

function createStory() {
    ajaxGet('private', {'action': 'createStory',
                        'storyName': $("#newStoryName").val(),
                        'sprintID': $(this).attr("data-sprintid"),
                        'storyPriority': $("#storyPriorityLevel option:selected").val()},
                            (result) => {
        $("#mainModal").fadeOut(500);
        updateStories($(this).attr("data-sprintid"), result);
    });
}

function updateStories(sprintID, storyList) {
    console.log(sprintID);
    console.log(storyList);
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
