package services;

//import models.academics.coursedefn.GradedItem;
import models.academics.coursedefn.GradedItem;
import models.academics.coursework.Submission;
import models.user.Student;

import java.util.List;

public class SubmissionService {
    private static SubmissionService ourInstance = new SubmissionService();

    public static SubmissionService getInstance() {
        return ourInstance;
    }

    private SubmissionService() {
    }

    public Submission getLastSubmissionFor(Student student, GradedItem deliverable) {
        List<Submission> list = Submission.find
                .where()
                .eq("gradedItem", deliverable)
                .eq("student", student).findList();
        if (list.size() == 0)
            return null;
        return list.get(list.size() - 1);
    }
}
