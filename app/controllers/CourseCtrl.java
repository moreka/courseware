package controllers;

import models.academics.OfferedCourse;
import models.academics.SyllabusItem;
import models.security.AccessType;
import models.user.BasicUser;
import models.user.Student;
import models.user.TeachingAssistance;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import security.Authenticated;
import services.OfferedCourseService;
import services.TokenService;
import services.UserService;
import views.html.courseManage;
import views.html.courseSyllabus;

@Security.Authenticated(Authenticated.class)
public class CourseCtrl extends Controller {

    public Result index(Long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(courseId);
        if (course == null)
            return notFound("No such course!");

        return ok("Course found!");
    }

    public Result editSyllabusPost(Long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(courseId);
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

        return redirect(routes.CourseCtrl.editSyllabus(courseId));
    }

    public Result editSyllabus(Long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(courseId);
        if (course == null)
            return notFound("No such course!");

        BasicUser currentUser = UserService.getInstance().getById(session("username"));

        return ok(courseSyllabus.render(
                course, OfferedCourseService.getInstance().firstTierSyllabusItems(course), currentUser));
    }

    public Result manageView(Long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(courseId);
        if (course == null)
            return notFound("No such course!");

        return ok(courseManage.render(course));
    }

    public Result addTA(long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(courseId);
        if (course == null)
            return notFound("No such course!");

        DynamicForm form = Form.form().bindFromRequest();

        BasicUser user = UserService.getInstance().getById(form.get("username"));
        if (user == null || !(user instanceof Student))
            return badRequest("Must be a student");

        course.addTeacherAssistant((Student) user);
        return redirect(routes.CourseCtrl.manageView(courseId));
    }

    public Result addTokenToTA(long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(courseId);
        TeachingAssistance ta = TeachingAssistance.find.byId(Long.parseLong(request().getQueryString("taId")));
        AccessType accessType = AccessType.valueOf(request().getQueryString("t"));

        TokenService.getInstance().createTokenFor(ta.student, course, accessType);

        return redirect(routes.CourseCtrl.manageView(courseId));
    }

    public Result removeTokenFromTA(long courseId) {
        OfferedCourse course = OfferedCourseService.getInstance().getCourse(courseId);
        TeachingAssistance ta = TeachingAssistance.find.byId(Long.parseLong(request().getQueryString("taId")));
        AccessType accessType = AccessType.valueOf(request().getQueryString("t"));

        TokenService.getInstance().deleteTokenFor(ta.student, course, accessType);

        return redirect(routes.CourseCtrl.manageView(courseId));
    }
}
