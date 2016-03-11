package models.academics;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course extends Model {

    @Id
    public Long courseId;

    public String title;
    public Integer departmentNo;
}
