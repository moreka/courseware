package models.academics.coursedefn;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("LIN")
public class LinearDropOffGradingStrategy extends GradingStrategy {

    public Date hardDeadline;

    @Override
    public float computeFinalGrade(float rawGrade, Date time) {
        float totalSeconds = (hardDeadline.getTime() - deadline.getTime()) / 1000;
        float passedSeconds = (time.getTime() - deadline.getTime()) / 1000;

        if (passedSeconds > totalSeconds)
            return 0;
        else if (passedSeconds < 0)
            return rawGrade;
        else
            return (1f - passedSeconds / totalSeconds) * rawGrade;

    }
}
