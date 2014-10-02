/**
 * Created by Robert & Christopher on 02-10-2014.
 */
function bindEvents(){
    $("#btn_add").bind("click", addPerson);
    $("#btn_del").bind("click", deletePerson);
    $("#btn_addRole").bind("click", addRole);
    $("#persons").bind("click", function(e) {
       var id = e.target.id;
        alert(id);
        if(isNaN(id)){
            return;
        }
        showPerson(id);
    });

}