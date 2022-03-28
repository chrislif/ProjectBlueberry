"use strict";

$(document).ready(() => {
    $(window).click(function (e) {
        if (e.target.id === "mainModal") {
            $("#mainModal").fadeOut(100);
        }
    });
    
    ajaxGet("AdminAccounts", {}, (result) => {
        var accountList = JSON.parse(result);

        $("#accountTable").html("");
        
        accountList.forEach((element) => {
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
                            <p>ID:${element.accountID}<p>
                            <p>Name:${element.accountName}<p>
                            <p>Email:${element.email}<p>
                            <p>XP:${element.accountXP}<p>
                        </div>
                        
                    </div>`);
                
                $("#modalCloseButton").click(() => {
                    $("#mainModal").fadeOut(500);
                });

                $("#mainModal").fadeIn(200);
            });
        });
    });
});

var ajaxGet = (url, data, callback) => {
    $.ajax({
        type: "GET",
        url: url,
        data: data,
        dataType: "JSON",
        success: callback,
        error: function (jqXHR, ex) {
            console.log(jqXHR);
        }
    });
};

