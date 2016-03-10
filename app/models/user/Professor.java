package models.user;

import models.academics.OfferedCourse;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("PRF")
public class Professor extends BasicUser {

    public Integer departmentNo;

    @OneToMany(mappedBy = "lecturer")
    public List<OfferedCourse> offeredCourses;

    public static Finder<Long, Professor> find = new Finder<>(Professor.class);

    public Long getLecturerCode() {
        return this.id;
    }
}
