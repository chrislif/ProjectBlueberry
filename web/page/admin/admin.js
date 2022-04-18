"use strict";

$(document).ready(() => {
    $(window).click(function (e) {
        if (e.target.id === "mainModal") {
            $("#mainModal").fadeOut(100);
        }
    });
    
    ajaxCall("AdminAccounts", {}, 'GET', (result) => {
        var accountList = JSON.parse(result);

        $("#accountTable").html("");
        
        accountList.forEach(createAccountButton);
    });
});

function createAccountButton(element) {
    $("#accountTable").append(`
        <button type="submit" class="gridSubContent gridButton" id="account${element.accountID}">
            <div class="gridButtonText">
                <h3>${element.accountName}</h3>
            </div>
        </button>`);

    $(`#account${element.accountID}`).click(function() {
        $("#mainModal").html(`
            <div id="modalBox" class="modalContent">
                <span id="modalCloseButton" class="closeButton">&times;</span>

                <div id="modalContent">
                    <h2>Account</h2>
                    <label for="accountID">Account ID: </label>
                    <input type="text" name="accountID" value="${element.accountID}"id="accountID" readonly><br><br>

                    <label for="accountName">Name: </label>
                    <input type="text" name="accountName" value="${element.accountName}"id="accountName" readonly><br><br>

                    <label for="email">Email: </label>
                    <input type="text" name="email" value="${element.email}"id="email" readonly><br><br>

                    <label for="accountXP">Account XP: </label>
                    <input type="text" name="accountXP" value="${element.accountXP}"id="accountXP" readonly><br><br>

                </div>
            </div>`);

        $("#modalCloseButton").click(() => {
            $("#mainModal").fadeOut(500);
        });

        $("#mainModal").fadeIn(200);
    });
}

var ajaxCall = (url, data, type, callback) => {
    $.ajax({
        type: type,
        url: url,
        data: data,
        dataType: "JSON",
        success: callback,
        error: function (jqXHR, ex) {
            console.log(jqXHR);
        }
    });
};

