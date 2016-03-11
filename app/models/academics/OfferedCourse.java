package models.academics;

import com.avaje.ebean.Model;
import models.user.Professor;
import models.user.Student;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class OfferedCourse extends Model {

    @Id
    public Long id;

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.ALL)
    public Course course;

    @ManyToOne(targetEntity = Professor.class)
    public Professor lecturer;

    @OneToMany(mappedBy = "offeredCourse")
    public List<TakenCourse> takenCourses;

    public String semester;
    public String room;
    public String lectureTime;
    public Integer groupId;
    public Date finalExamTime;

    public void addStudent(Student student) {
        TakenCourse t = new TakenCourse();
        t.student = student;
        t.offeredCourse = this;
        t.save();
    }

    public static Finder<Long, OfferedCourse> find = new Finder<>(OfferedCourse.class);

}
