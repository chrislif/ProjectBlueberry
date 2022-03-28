"use strict";

$(document).ready(() => {
   ajaxGet("AdminAccounts", {}, (result) => {
       console.log(result);
       var accountList = JSON.parse(result);
       
       $("#accountTable").html("<table>");
        
        
        
       $("#accountTable").apppend("</table>");
       
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

