package models.user;

import models.academics.OfferedCourse;
import models.academics.coursework.TakenCourse;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("STU")
public class Student extends BasicUser {

    public Integer departmentNo;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<TeachingAssistance> teachingAssistanceList;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    public List<TakenCourse> takenCourses;

    public static Finder<String, Student> find = new Finder<>(Student.class);

    public String getStudentID() {
        return this.id;
    }

    public void takeCourse(OfferedCourse offeredCourse) {
        TakenCourse t = new TakenCourse();
        t.student = this;
        t.offeredCourse = offeredCourse;
        t.save();
    }

    @Override
    public String toString() {
        return String.format("Stud[%s, %s]", this.id, this.name);
    }
}
