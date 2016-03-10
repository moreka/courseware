package controllers;

import models.user.Student;
import models.user.TeachingAssistance;
import play.mvc.*;
import views.html.*;


public class UserCtrl extends Controller {

    public Result login() {
//
//        Student student = new Student();
//
//        student.studentID = "90106103";
//        student.level = "BSc";
//        student.major = "CE";
//        student.username = "moreka";
//        student.password = "moreka";
//        student.email = "m.r.karimi.j@gmail.com";
//        student.fullName = "Mohammad Reza Karimi";
//
//        student.save();

        TeachingAssistance ta = new TeachingAssistance();
        ta.course = "Signal";

        Student student = Student.find.all().get(0);
        student.teachingAssistanceList.add(ta);

        student.save();

        return ok(login.render());
    }

}
