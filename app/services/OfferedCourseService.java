package services;

import models.academics.OfferedCourse;
import models.academics.SyllabusItem;

import java.util.List;

public class OfferedCourseService {
    private static OfferedCourseService ourInstance = new OfferedCourseService();

    public static OfferedCourseService getInstance() {
        return ourInstance;
    }

    private OfferedCourseService() {
    }

    public OfferedCourse getCourse(Long offeredCourseId) {
        return OfferedCourse.find.byId(offeredCourseId);
    }

    public OfferedCourse getCourse(String semester, Long courseId) {
        return OfferedCourse.find
                .where()
                .eq("semester", semester)
                .eq("course.courseId", courseId)
                .findUnique();
    }

    public List<SyllabusItem> firstTierSyllabusItems(OfferedCourse course) {
        return SyllabusItem.find
                .where().isNull("parent")
                .eq("offeredCourse", course)
                .orderBy("itemOrder")
                .findList();
    }
}
