package models.user;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BasicUser extends Model {

    @Id
    public Long id;

    public String password;
    public String name;
    public Date birthDate;

    public static Finder<Long, BasicUser> find = new Finder<>(BasicUser.class);

}
