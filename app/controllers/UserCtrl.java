package controllers;

import forms.User;
import models.user.BasicUser;
import play.data.Form;
import play.mvc.*;
import security.Authenticated;
import services.UserService;
import views.html.*;


public class UserCtrl extends Controller {

    public Result doLogin() {
        Form<User> loginForm = Form.form(User.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            BasicUser user = UserService.getInstance().authenticate(loginForm.get());
            if (user == null) {
                loginForm.reject("No such user exists");
                return badRequest(login.render(loginForm));
            }
            session("username", user.id);

            return ok(index.render("Logged in"));
        }
    }

    public Result login() {
        return ok(login.render(Form.form(User.class)));
    }

    public Result logout() {
        session().clear();
        return redirect("/");
    }

}
