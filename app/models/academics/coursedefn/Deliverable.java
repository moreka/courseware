package models.academics.coursedefn;

import models.academics.coursework.Submission;
import models.user.Student;
import services.SubmissionService;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class Deliverable extends GradedItem {

    @OneToOne
    public GradingStrategy gradingStrategy;

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public float computeGrade(Student student) {
        Submission submission = SubmissionService.getInstance().getLastSubmissionFor(student, this);
        if (submission == null)
            return 0;

        return gradingStrategy.computeFinalGrade(submission.getRawGrade(), submission.date);
    }
}
