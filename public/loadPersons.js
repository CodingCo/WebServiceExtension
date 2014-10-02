// JS
function createTable(data){
    var persons = data;
    var res = "<table border = 1>";
    res += "<tr><th>Id</th><th>FirstName</th><th>LastName</th><th>Mail</th><th>Phone</th></tr>";

    if(persons instanceof Array){
        for(var i = 0; i < persons.length; i++){
            res += "<tr>";
            res += "<td>"  + persons[i].id   +  "</td>";
            res += "<td>"  + persons[i].firstName    +  "</td>";
            res += "<td>"  + persons[i].lastName  +  "</td>";
            res += "<td>"  + persons[i].mail   +  "</td>";
            res += "<td>"  + persons[i].phone   +  "</td>";
            res += "</tr>";
        }
    }else{
        res += "<tr>";
        res += "<td>"  + persons.id   +  "</td>";
        res += "<td>"  + persons.firstName    +  "</td>";
        res += "<td>"  + persons.lastName  +  "</td>";
        res += "<td>"  + persons.roles[0].id  +  "</td>";
        res += "<td>"  + persons.mail   +  "</td>";
        res += "<td>"  + persons.phone   +  "</td>";
        res += "</tr>";
    }
    res += "</table>";
        return res;
}

function showAllPersons (){

    $.ajax({
        url:"http://localhost:8028/person",
        error: function (xhr, ajaxOptions, thrownError) {
        alert(xhr.status);
        alert(thrownError);
      },
      dataType: 'json'

    }).done(function(data){
        var res = createTable(data);
        $("#personTable").html(res);
    });

}

function showPerson (){
    $.get("http://localhost:8028/person/"+$("#personToFind").val(), function(data){
        var res = createTable(data);
        $("#personTable").html(res);
    });
}

function addPerson(){
    var data = "{ firstName: " + $("#firstName").val() + ", lastName: " + $("#lastName").val() + 
                ", mail: " + $("#mail").val() + ", phone: " + $("#phone").val()+ "}";

    $.ajax({
        url: "http://localhost:8028/person",
        type: "POST",
        data: data
    }).done( showAllPersons());

}

function deletePerson(){

    var personId = $("#personToDelete").val();

    $.ajax({
        url: "http://localhost:8028/person/"+ personId,
        type: "DELETE"
    }).done( showAllPersons());
}

function addRole(){

    var roleName = $("#roleName").val();
    var personId = $("#personToRole").val();
    var data = "";

    if (roleName.toLowerCase() === "student") {
        data += "{semester: "+$("#semester").val() + ", roleName: Student}";
    }else if (roleName.toLowerCase() === "teacher") {
        data += "{degree: "+$("#degree").val() + ", roleName: Teacher}";
    }else if (roleName.toLowerCase() === "student") {
        data += "{"+$("#roleName").val()+"}";
    }

    $.ajax({
        url: "http://localhost:8028/person/"+ personId,
        type: "PUT",
        dataType: "json",
        data: data
    }).done( showAllPersons());
}