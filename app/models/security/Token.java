package models.security;

import com.avaje.ebean.Model;
import models.user.BasicUser;

import javax.persistence.*;

@Entity
public class Token extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public AccessType accessType;

    public String uniqueID;

    @ManyToOne
    public BasicUser user;
}