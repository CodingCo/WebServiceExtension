// JS
function createTable(data){
    var persons = data;
    var res = "<table border = 1>";
    res += "<tr><th>FirstName</th><th>LastName</th><th>Mail</th><th>Phone</th></tr>";

    if(persons instanceof Array){
        for(var i = 0; i < persons.length; i++){
            res += "<tr>";
            res += "<td>"  + persons[i].firstName    +  "</td>";
            res += "<td>"  + persons[i].lastName  +  "</td>";
            res += "<td>"  + persons[i].mail   +  "</td>";
            res += "<td>"  + persons[i].phone   +  "</td>";
            res += "</tr>";
        }
    }else{
        res += "<tr>";
        res += "<td>"  + persons.firstName    +  "</td>";
        res += "<td>"  + persons.lastName  +  "</td>";
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
    $.get("http://localhost:8028/person/1", function(data){
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
        url: "http://127.0.0.1:8028/person",
        type: "DELETE",
        data: personId
    }).done( showAllPersons());
}