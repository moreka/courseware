package models.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADM")
public class Admin extends BasicUser {

    public static Finder<String, Admin> find = new Finder<>(Admin.class);

    @Override
    public String toString() {
        return String.format("Admin[%s, %s]", id, name);
    }
}
