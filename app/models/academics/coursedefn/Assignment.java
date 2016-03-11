package models.academics.coursedefn;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ASSIGN")
public class Assignment extends Deliverable {
}
