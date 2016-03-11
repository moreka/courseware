package models.user;

import com.avaje.ebean.Model;
import models.IUniqueID;
import models.security.AccessType;
import models.security.Token;
import play.api.mvc.AcceptExtractors;
import services.TokenService;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BasicUser extends Model {

    @Id
    public String id;

    public String password;
    public String name;
    public Date birthDate;

    public boolean hasAccessTo(IUniqueID item, AccessType accessType) {
        return getAccessTypesTo(item).contains(accessType);
    }

    public List<AccessType> getAccessTypesTo(IUniqueID item) {
        return TokenService.getInstance().getAccessTokensTo(this, item);
    }

    public abstract String toString();

    public static Finder<Long, BasicUser> find = new Finder<>(BasicUser.class);

}
