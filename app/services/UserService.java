package services;

import com.avaje.ebean.Model;
import forms.User;
import models.user.BasicUser;

import java.util.List;

public class UserService {
    private static UserService ourInstance = new UserService();

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
    }

    private Model.Finder<String, BasicUser> find = new Model.Finder<>(BasicUser.class);

    public BasicUser authenticate(User user) {
        List<BasicUser> users =
                find.where()
                        .eq("id", user.getUsername())
                        .eq("password", user.getPassword())
                        .findList();

        if (users.size() > 0)
            return users.get(0);

        return null;
    }

    public BasicUser getById(String id) {
        return find.byId(id);
    }
}
