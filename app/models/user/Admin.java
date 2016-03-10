package models.user;

import com.avaje.ebean.Model;

import javax.persistence.Entity;

@Entity
public class Admin extends Model {

    public String username;
    public String password;

    public void importDataFromEdu() { }

    public static Finder<Long, Admin> find = new Finder<>(Admin.class);
}
