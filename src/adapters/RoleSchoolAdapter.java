package adapters;

import com.google.gson.*;
import java.lang.reflect.Type;
import model.*;

public class RoleSchoolAdapter implements JsonSerializer<RoleSchool>, JsonDeserializer<RoleSchool> {

    @Override
    public JsonElement serialize(RoleSchool t, Type type, JsonSerializationContext jsc) {
        final JsonObject jsonObject = new JsonObject();
        
        jsonObject.addProperty("id", t.getId());
        if(t instanceof Student){
            Student student = (Student) t;
           jsonObject.addProperty("semester", student.getSemester());
        }
        if(t instanceof Teacher){
            Teacher teacher = (Teacher) t;
            jsonObject.addProperty("degree", teacher.getDegree());
        }
        if(t instanceof AssistentTeacher){
            AssistentTeacher at = (AssistentTeacher) t;
        }
        jsonObject.addProperty("roleName", t.getRoleName());
        return jsonObject;
    }

    @Override
    public RoleSchool deserialize(JsonElement je, Type typeOfT, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject jsonObject = je.getAsJsonObject();
        String type = jsonObject.get("roleName").getAsString();
        long id = Long.parseLong(jsonObject.get("id").getAsString());
        
        switch(type.toLowerCase()){
            case "student":
                String semester = jsonObject.get("semester").getAsString();
                Student student = new Student();
                student.setId(id);
                student.setSemester(semester);
                return student;
            case "teacher":
                String degree = jsonObject.get("degree").getAsString();
                Teacher teacher = new Teacher();
                teacher.setId(id);
                teacher.setDegree(degree);
                return teacher;
            case "assistent teacher":
                AssistentTeacher at = new AssistentTeacher();
                at.setId(id);
                return at; 
            default:
                return null;
        }
    }
    
}
