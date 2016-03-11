package models.academics.coursedefn;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class GradingStrategy extends Model{
    @Id
    public Long id;
    public Date deadline;

    public abstract float computeFinalGrade(float rawGrade, Date time);
}
