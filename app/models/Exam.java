package models;

import javax.persistence.Entity;
import java.util.Date;


@Entity
public class Exam extends GradedItem {

    @Override
    public float computeGrade(float rawGrade, Date time) {
        return rawGrade * this.percentage;
    }
}
