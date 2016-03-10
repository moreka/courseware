package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public abstract class GradedItem extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String title;
    public Float percentage;

    public abstract float computeGrade(float rawGrade, Date time);
}
