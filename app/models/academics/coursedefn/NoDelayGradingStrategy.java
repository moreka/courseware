package models.academics.coursedefn;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("NODELAY")
public class NoDelayGradingStrategy extends GradingStrategy {
    @Override
    public float computeFinalGrade(float rawGrade, Date time) {
        if (time.compareTo(deadline) > 0)
            return 0;
        return rawGrade;
    }
}
