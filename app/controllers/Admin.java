package controllers;

import controllers.security.AdminSecured;
import exceptions.AuthenticationError;
import play.mvc.*;

import services.EduDataImportService;
import services.EduSystemGateway;

import java.io.File;

public class Admin extends Controller {

    @Security.Authenticated(AdminSecured.class)
    public Result index() {
        return ok();
    }

    @Security.Authenticated(AdminSecured.class)
    public Result loadEdu() {
        try {

            File currentSemesterData = EduSystemGateway.getInstance().getCurrentSemesterData();
            EduDataImportService.getInstance().importData(currentSemesterData);

            return redirect(controllers.routes.Admin.index());
        }
        catch (AuthenticationError e) {
            e.printStackTrace();
            return forbidden();
        }
        catch (Exception e) {
            e.printStackTrace();
            return internalServerError();
        }
    }

}
