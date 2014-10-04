/**
 * Created by simon on 03/10/14.
 */


function transactionStatus(type) {
    var succesMessage = '<div class="alert alert-success" role="alert">The transaction was a succes</div>';
    var failMessage = '<div class="alert alert-warning" role="alert">The transaction failed</div>';
    var failRoleMessage = '<div class="alert alert-warning" role="alert">Role already exists</div>';

    var element = document.getElementById("transaction_status");
    if (type === "fail") {
        element.innerHTML = failMessage;
    } else if (type === "succes") {
        element.innerHTML = succesMessage;
    } else {
        element.innerHTML = failRoleMessage;
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
        var isJson =  IsJsonString(json);
		if(isJson){
			var users = JSON.parse(json);
	        var allUsers = "";
        }
        if(!isJson){
            $("#users").html('<li class="list-group-item list-group-item-danger">No users online</li>');
        }else{
			for(var i = 0; i < users.length; ++i){
				allUsers += '<li class="list-group-item list-group-item-success">'+ users[i].name +'</li>';	
			}
			$("#users").html(allUsers);
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
        $("#logmessages").html(text);

    });
}

