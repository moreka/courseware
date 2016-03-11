package models.user;

import com.avaje.ebean.Model;
import models.academics.OfferedCourse;

import javax.persistence.*;

@Entity
public class TeachingAssistance extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    public OfferedCourse offeredCourse;

}
