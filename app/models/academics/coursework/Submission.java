package models.academics.coursework;

import com.avaje.ebean.Model;
import models.academics.coursedefn.GradedItem;
import models.user.Student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Submission extends Model {

    @Id
    public Long id;
    public Date date;

    @ManyToOne
    public GradedItem gradedItem;

    @ManyToOne
    public Student student;

    @OneToMany(mappedBy = "submission")
    public List<Grading> gradings;

    public float getRawGrade() {
        if (gradings.size() == 0)
            return 0;
        return gradings.get(gradings.size() - 1).rawGrade;
    }

    public static Finder<Long, Submission> find = new Finder<>(Submission.class);
}
