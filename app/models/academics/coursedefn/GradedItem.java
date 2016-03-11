package models.academics.coursedefn;

import com.avaje.ebean.Model;
import models.academics.coursework.Submission;
import models.user.Student;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class GradedItem extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String title;
    public Float percentage;

    @ManyToOne(cascade = CascadeType.ALL)
    public GradedItem parent;

    @OneToMany(mappedBy = "parent")
    public List<GradedItem> childGradedItems;


    @OneToMany(mappedBy = "gradedItem")
    public List<Submission> submissions;

    public abstract boolean isActive();

    public abstract float computeGrade(Student student);
}
