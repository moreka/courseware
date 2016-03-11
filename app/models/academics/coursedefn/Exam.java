package models.academics.coursedefn;

import models.academics.coursework.Submission;
import models.user.Student;
import services.SubmissionService;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EXAM")
public class Exam extends GradedItem {

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public float computeGrade(Student student) {
        Submission s = SubmissionService.getInstance().getLastSubmissionFor(student, this);
        if (s == null)
            return 0;
        return s.getRawGrade();
    }
}
