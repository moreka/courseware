package models.academics;

import com.avaje.ebean.Model;
import models.IUniqueID;
import models.academics.coursework.TakenCourse;
import models.user.BasicUser;
import models.user.Professor;
import models.user.Student;
import models.user.TeachingAssistance;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class OfferedCourse extends Model implements IUniqueID {

    @Id
    public Long id;

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.ALL)
    public Course course;

    @ManyToOne(targetEntity = Professor.class)
    public Professor lecturer;

    @OneToMany(mappedBy = "offeredCourse")
    public List<TakenCourse> takenCourses;

    @OneToMany(mappedBy = "offeredCourse")
    public List<SyllabusItem> syllabusItems;

    @OneToMany(mappedBy = "offeredCourse")
    public List<TeachingAssistance> tas;

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

    public void addTeacherAssistant(Student student) {
        if (isTeacherAssistant(student))
            return;

        TeachingAssistance ta = new TeachingAssistance();
        ta.offeredCourse = this;
        ta.student = student;
        ta.save();
    }

    public boolean isTeacherAssistant(Student student) {
        for (TeachingAssistance ta : tas) {
            if (ta.student.id.equals(student.id))
                return true;
        }
        return false;
    }

    public static Finder<Long, OfferedCourse> find = new Finder<>(OfferedCourse.class);

    @Override
    public String getUID() {
        return String.format("%s:%d", this.getClass().getSimpleName(), this.id);
    }

    @Override
    public BasicUser getOwner() {
        return this.lecturer;
    }
}
