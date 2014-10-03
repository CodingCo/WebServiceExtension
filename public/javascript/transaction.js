/**
 * Created by simon on 03/10/14.
 */


function transactionStatus(type) {
    var succesMessage = '<div class="alert alert-success" role="alert">The transaction was a succes</div>';
    var failMessage = '<div class="alert alert-warning" role="alert">The transaction failed</div>';

    var element = document.getElementById("transaction_status");
    if (type === "fail") {
        element.innerHTML = failMessage;
    } else {
        element.innerHTML = succesMessage;
    }
    window.setTimeout(function () {
        document.getElementById("transaction_status").innerHTML = "";
    }, 3000);
}


function getAcademies() {
    $.get("http://localhost:8028/academy", function (json) {
        var academies = json;
        var stringToAdd = "";
        console.log(academies);
        for (var i = 0; i < json.length; i++) {
            stringToAdd += "<option id =" + json[i].name + ">" + json[i].name + "</option>";
        }
        $("#academy").html(stringToAdd)
    });
}



function getOnlineUsers(){
    $.get("http://localhost:8028/log/online", function(json){
        var users = json;
        var allUsers = "";
        var isJson =  IsJsonString(json);
        if(!isJson){
            $("#users").html('<li class="list-group-item list-group-item-danger">No users online</li>');
        }else{
            users.forEach(function(user){
                allUsers += '<li class="list-group-item list-group-item-success">'+ user.name +'</li>';
            });
        }
    });

}

function IsJsonString(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}

function getLogMessages(){
    $.get("http://localhost:8028/log/log", function(text){
        console.log(text);
        $("#logmessages").html(text);

    });
}

