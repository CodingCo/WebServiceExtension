/**
 * Created by Robert and Christopher on 02-10-2014.
 */

$(document).ready(function(){
    showAllPersons();
    getAcademies();
    bindEvents();
});

var index;

function showPerson (id){
    $.get("http://localhost:8028/person/"+id, function(person){
        $("#fName").val(person.firstName);
        $("#lName").val(person.lastName);
        $("#mail").val(person.mail);
        $("#phone").val(person.phone);
        showRole(person);
    });
}

function showRole(person){

    var roles = person.roles;
    var options = "";

    roles.forEach(function(role){

        if(role.roleName === "Student") {
            options += "<option id = " + role.roleName + ">" + role.roleName + ", Semester: " + role.semester + "</option>";
        }else if(role.roleName === "Teacher") {
            options += "<option id = " + role.roleName + ">" + role.roleName + ", Degree: " + role.degree + "</option>";
        }else {
            options += "<option id = " + role.roleName + ">" + role.roleName + "</option>";
        }

    });

    $("#roles").html(options);

}

function addPerson(){
    var data = "{ firstName: " + $("#fName").val() + ", lastName: " + $("#lName").val() +
        ", mail: " + $("#mail").val() + ", phone: " + $("#phone").val()+ "}";
    $.ajax({
        url: "http://localhost:8028/person",
        type: "POST",
        data: data
    }).done(function(user){
        showAllPersons(index);
        if(user != null){
            transactionStatus("succes");
        }else{
            transactionStatus("fail");
        }
    });

}

function deletePerson(){
    var id = $("#persons :selected").attr("id");
    $.ajax({
        url: "http://localhost:8028/person/"+ id,
        type: "DELETE",
        dataType: "json",
        data: "{ data: person }"
    }).done(showAllPersons(index));


}

function addRole(){

    var roleName = $("#rolesDropDown :selected").text();
    var personId = $("#persons :selected").attr("id");
    var degSem = $("#semDeg").val();
    var data = "";

    if (roleName.toLowerCase() === "student") {
        data += "{semester: " + degSem + ", roleName: Student}";
    }else if (roleName.toLowerCase() === "teacher") {
        data += "{degree: " + degSem + ", roleName: Teacher}";
    }else {
        data += "{roleName: AssistentTeacher}";
    }

    $.ajax({
        url: "http://localhost:8028/roleschool/"+ personId,
        type: "PUT",
        dataType: "json",
        data: data
    }).done(showAllPersons(index));

}

function delRole(){
    var personId = $("#persons :selected").attr("id");
    var roleName = $("#roles :selected").attr("id");

    $.ajax({
        url: "http://localhost:8028/roleschool/"+personId,
        type: "DELETE",
        dataType: "json",
        data: "{roleName: "+roleName+"}"
    }).done(showAllPersons(index));


}

function updatePerson(){
    var personId = $("#persons :selected").attr("id");
    var data = "{ firstName: " + $("#fName").val() + ", lastName: " + $("#lName").val() +
        ", mail: " + $("#mail").val() + ", phone: " + $("#phone").val()+ "}";
    $.ajax({
        url: "http://localhost:8028/person/"+ personId,
        type: "PUT",
        dataType: "json",
        data: data
    }).done( showAllPersons(index));
}

function showAllPersons (index){
    index = $("#persons")[0].selectedIndex;

    $.ajax({
        url:"http://localhost:8028/person",
        type: "GET",
        dataType: 'json'

    }).done(function(persons){

        var options = "";
        persons.forEach(function(person){

            options += "<option id =" + person.id + ">" + person.id + ": " + person.firstName[0] + ", " + person.lastName + "</option>";

        });
        $("#persons").html(options);

        if( $("#persons").has("option").length === 0 ) {
            clearFields();
        }

        $("#persons option")[index].selected = true;
        var personId = $("#persons :selected").attr("id");
        showPerson(personId);
    });

}

function clearFields(){
    $("#fName").val("");
    $("#lName").val("");
    $("#phone").val("");
    $("#mail").val("");
    $("#semDeg").val("");
    $("#roles").html("");

}