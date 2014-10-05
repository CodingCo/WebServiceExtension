/**
 * Created by Robert and Christopher on 02-10-2014.
 */

$(document).ready(function(){
    showAllPersons();
    showAllCourses();
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
            options += "<option id = " + role.id + " value = " + role.roleName + ">" + role.roleName + ", Semester: " + role.semester + "</option>";
        }else if(role.roleName === "Teacher") {
            options += "<option id = " + role.id + " value = " + role.roleName + ">" + role.roleName + ", Degree: " + role.degree + "</option>";
        }else {
            options += "<option id = " + role.id + " value = " + role.roleName + ">" + role.roleName + "</option>";
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
    }
    if (roleName.toLowerCase() === "teacher") {
        data += "{degree: " + degSem + ", roleName: Teacher}";
    }
    if (roleName.toLowerCase() === "assistent teacher") {
        roleName = "AssistentTeacher";
        data += "{roleName: AssistentTeacher}";
    }

    if(!roleExists(roleName)){
        $.ajax({
            url: "http://localhost:8028/roleschool/"+ personId,
            type: "PUT",
            dataType: "json",
            data: data
        }).done(function(role){
            if(role != null){
                showAllPersons(index);
                $("#semDeg").val("");
                transactionStatus("succes");
            } else{
                transactionStatus("fail");
            }
        });

    }


}

function roleExists(roleName){
    var exists = false;
    $("#roles > option").each(function(){
        if(this.text.indexOf(roleName) > -1){
            transactionStatus("roleExists");
            exists = true;
        }
    });
    return exists;
}

function checkSemDeg(){
    var roleName = $("#rolesDropDown :selected").text();
    if (roleName.toLowerCase() === "student") {
        $("#semDegHeader").html("Semester");
    }
    if (roleName.toLowerCase() === "teacher") {
        $("#semDegHeader").html("Degree");
    }
    if (roleName.toLowerCase() === "assistent teacher") {
        $("#semDegHeader").html("No attribute");
        $("#semDeg").prop("disabled", true);
    } else{
        $("#semDeg").prop("disabled", false);
    }
}

function delRole(){
    var personId = $("#persons :selected").attr("id");
    var roleName = $("#roles :selected").attr("value");

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

function checkAssignButton(){
    var roleName = $("#roles :selected").attr("value");
    if (roleName.toLowerCase() === "student") {
        $("#btn_assignCourse").html("Enroll");
    }
    if (roleName.toLowerCase() === "teacher") {
        $("#btn_assignCourse").html("Teach");
    }
    if (roleName.toLowerCase() === "assistentteacher") {
        $("#btn_assignCourse").html("Assist");
    }
}

//== COURSE PART START ==//

function showAllCourses(){
    $.ajax({
        url:"http://localhost:8028/course",
        type: "GET",
        dataType: 'json'
    }).done(function(courses){
        var options = "";
        courses.forEach(function(course){
                options += "<option id = " + course.id + ">" + course.name + "</option>";
        });
        $("#courses").html(options);
    });
}

function createCourse(){
    // ESCAPE SEQUENCE \" IMPORTANT TO A STRING WITH WHITESPACES
    // ESCAPE SEQUENCE \n IS REPLACED WITH A WHITESPACE INSTEAD, BECAUSE JPA CAN'T ADD LINEBREAKS IF THE USER HITS "ENTER" IN DESC.
    var data = "{ name: " + $("#courseName").val() + ", description: " + "\"" + $("#courseDesc").val().split("\n").join(" ") + "\"" + "}";
    $.ajax({
        url: "http://localhost:8028/course",
        type: "POST",
        data: data
    }).done(function(course){
        if(course != null){
            showAllCourses();
            $("#courseName").val("");
            $("#courseDesc").val("");
            transactionStatus("succes");
        }else{
            transactionStatus("fail");
        }
    });
}

function deleteCourse(){
    var id = $("#courses :selected").attr("id");
    $.ajax({
        url: "http://localhost:8028/course/"+ id,
        type: "DELETE",
        dataType: "json"
    }).done(function(course){
        if(course != null){
            showAllCourses();
            $("#courseName").val("");
            $("#courseDesc").val("");
            transactionStatus("succes");
        }else{
            transactionStatus("fail");
        }
    });
}

function showCourse(id){
    $.get("http://localhost:8028/course/"+id, function(course){
        $("#courseName").val(course.name);
        $("#courseDesc").val(course.description);
    });
}

function assignCourse(){
    var roleId = $("#roles :selected").attr("id");
    var courseId = $("#courses :selected").attr("id");
    //alert("assign role with id " + roleId + " to course with id " + courseId + "?");
    //alert("data is: " + "{ type: assign, roleId: " + roleId + " }")
    $.ajax({
        url: "http://localhost:8028/course/"+ courseId,
        type: "PUT",
        dataType: "json",
        data: "{ type: assign, roleId: " + roleId + " }"
    }).done(function(course){
        if(course != null){
            transactionStatus("succes");
            showCoursesAssignedToRole();
        }else{
            transactionStatus("fail");
        }
    });
}

function showCoursesAssignedToRole(){
    resetColorOnCourses();

    var roleId = $("#roles :selected").attr("id");
    var courseIds = [];
    $.ajax({
        url:"http://localhost:8028/course",
        type: "GET",
        dataType: 'json'
    }).done(function(courses){
        courses.forEach(function(course){
            var roles = course.roles;
            //alert("current course: " + course.id);
            roles.forEach(function(role){
                //alert("current role " + role.id);
                if(roleId == role.id){
                    //alert("match on selectedRoleId: " + roleId + " and currentRoleId: " + role.id + " in courseId: " + course.id);
                    courseIds.push(course.id);
                }
            });
        });

        colorTheCourses(courseIds);
        deselectAllCourses();
    });
}

function colorTheCourses(courseIds){
    //alert("going to iterate through: " + courseIds);
    //== Color the matches
    courseIds.forEach(function(courseId){
        $("#courses > option").each(function(){
            if(this.id == courseId){
                //alert("theres a match on this.id: " + this.id + " and courseId: " + courseId + " this SHOULD be colorized!");
                $(this).css("background-color", "LIGHTSKYBLUE"); // LIGHTSKYBLUE or LIGHTGREEN
            }
        });
    });
}

function deselectAllCourses(){
    var currentCourseIndex = 0;

    $("#courses > option").each(function(){
        $("#courses option")[currentCourseIndex].selected = false;
        currentCourseIndex++;
    });
}

function resetColorOnCourses(){
    $("#courses > option").each(function(){
        $(this).css("background-color", "WHITE");
    });
}

//== COURSE PART START ==//

function clearFields(){
    $("#fName").val("");
    $("#lName").val("");
    $("#phone").val("");
    $("#mail").val("");
    $("#semDeg").val("");
    $("#roles").html("");

}