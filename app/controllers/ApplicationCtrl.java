package controllers;

import play.mvc.*;

import views.html.*;

public class ApplicationCtrl extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

}
