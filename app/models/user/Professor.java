package models.user;

import models.IUniqueID;
import models.academics.OfferedCourse;
import models.security.AccessType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.List;

@Entity
@DiscriminatorValue("PRF")
public class Professor extends BasicUser {

    public Integer departmentNo;

    @OneToMany(mappedBy = "lecturer")
    public List<OfferedCourse> offeredCourses;

    public static Finder<String, Professor> find = new Finder<>(Professor.class);

    public String getLecturerCode() {
        return this.id;
    }

    @Override
    public List<AccessType> getAccessTypesTo(IUniqueID item) {
        if (item.getOwner().id.equals(id))
            return Arrays.asList(AccessType.values());
        return Arrays.asList();
    }

    @Override
    public String toString() {
        return String.format("Prof[%s, %s]", this.id, this.name);
    }
}
