package models.user;

import models.IUniqueID;
import models.security.AccessType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;

@Entity
@DiscriminatorValue("ADM")
public class Admin extends BasicUser {

    public static Finder<String, Admin> find = new Finder<>(Admin.class);

    @Override
    public List<AccessType> getAccessTypesTo(IUniqueID item) {
        return Arrays.asList(AccessType.values());
    }

    @Override
    public String toString() {
        return String.format("Admin[%s, %s]", id, name);
    }
}
