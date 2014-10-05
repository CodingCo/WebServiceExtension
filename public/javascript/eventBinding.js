/**
 * Created by Robert & Christopher on 02-10-2014.
 */
function bindEvents(){
    $("#btn_add").bind("click", addPerson);
    $("#btn_del").bind("click", deletePerson);
    $("#btn_addRole").bind("click", addRole);
    $("#btn_deleteRole").bind("click", delRole);
    $("#persons").bind("click", function(e) {
       var id = e.target.id;
        if(isNaN(id)){
            return;
        }
        showPerson(id);
    });
    $("#rolesDropDown").bind("change", checkSemDeg);
    $("#roles").bind("click", checkAssignButton);
    $("#btn_save").bind("click", updatePerson);

    $("#btn_createCourse").bind("click", createCourse);
    $("#btn_deleteCourse").bind("click", deleteCourse);
    $("#courses").bind("click", function(e) {
        var id = e.target.id;
        if(isNaN(id)){
            return;
        }
        showCourse(id);
    });
    
}