package models.academics.coursework;

import com.avaje.ebean.Model;
import models.user.BasicUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Grading extends Model {
    @Id
    public Long id;

    public Float rawGrade;
    public Date date;

    @ManyToOne
    public BasicUser grader;

    @ManyToOne
    public Submission submission;

    public static Finder<Long, Grading> find = new Finder<>(Grading.class);
}
