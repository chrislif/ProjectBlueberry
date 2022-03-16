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