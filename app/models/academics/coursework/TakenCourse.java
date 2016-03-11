package models.academics.coursework;

import com.avaje.ebean.Model;
import models.academics.OfferedCourse;
import models.user.Student;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TakenCourse extends Model {

    @ManyToOne
    public OfferedCourse offeredCourse;

    @ManyToOne
    public Student student;
}
