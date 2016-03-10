package models;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;


@Entity
public class GradingGroup extends GradedItem {

    public List<GradedItem> gradedItems;

    @Override
    public float computeGrade(float rawGrade, Date time) {
        return 0;
    }
}
