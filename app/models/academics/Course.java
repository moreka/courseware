package models.academics;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course extends Model {

    @Id
    public Long courseId;

    public String title;
    public Integer departmentNo;
}
