package models.academics.coursedefn;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PPHASE")
public class ProjectPhase extends Deliverable {
}
