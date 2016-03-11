package controllers;

import models.academics.OfferedCourse;
import models.academics.SyllabusItem;
import models.user.BasicUser;
import models.user.Student;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import security.Authenticated;
import services.OfferedCourseService;
import services.UserService;
import views.html.courseManage;
import views.html.courseSyllabus;

@Security.Authenticated(Authenticated.class)
public class CourseCtrl extends Controller {

    public Result index(String semester, Long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(semester, courseId);
        if (course == null)
            return notFound("No such course!");

        return ok("Course found!");
    }

    public Result editSyllabusPost(String semester, Long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(semester, courseId);
        if (course == null)
            return notFound("No such course!");

        DynamicForm form = Form.form().bindFromRequest();

        String _parent = form.get("parent");
        String itemTitle = form.get("syllab");
        SyllabusItem parent = null;

        if (_parent != null && !_parent.equals("-1")) {
            parent = SyllabusItem.find.byId(Long.parseLong(_parent));
        }

        SyllabusItem item = new SyllabusItem();
        item.title = itemTitle;
        item.offeredCourse = course;
        item.parent = parent;
        item.save();

        return redirect(routes.CourseCtrl.editSyllabus(semester, courseId));
    }

    public Result editSyllabus(String semester, Long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(semester, courseId);
        if (course == null)
            return notFound("No such course!");

        BasicUser currentUser = UserService.getInstance().getById(session("username"));

        return ok(courseSyllabus.render(
                course, OfferedCourseService.getInstance().firstTierSyllabusItems(course), currentUser));
    }

    public Result manageView(String semester, Long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(semester, courseId);
        if (course == null)
            return notFound("No such course!");

        return ok(courseManage.render(course));
    }

    public Result addTA(String semester, long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(semester, courseId);
        if (course == null)
            return notFound("No such course!");

        DynamicForm form = Form.form().bindFromRequest();

        BasicUser user = UserService.getInstance().getById(form.get("username"));
        if (user == null || !(user instanceof Student))
            return badRequest("Must be a student");

        course.addTeacherAssistant((Student) user);
        return redirect(routes.CourseCtrl.manageView(semester, courseId));
    }

    public Result addTokenToTA(String semester, long courseId) {
        return Results.TODO;
    }
}
