package controllers;

import security.AdminSecured;
import exceptions.AuthenticationError;
import play.mvc.*;

import services.EduDataImportService;
import services.EduSystemGateway;

import java.io.File;

public class AdminCtrl extends Controller {

    @Security.Authenticated(AdminSecured.class)
    public Result index() {
        return ok();
    }

    @Security.Authenticated(AdminSecured.class)
    public Result loadEdu() {
        try {

            File currentSemesterData = EduSystemGateway.getInstance().getCurrentSemesterData();
            EduDataImportService.getInstance().importData(currentSemesterData);

            return redirect(routes.AdminCtrl.index());
        }
        catch (AuthenticationError e) {
            e.printStackTrace();
            return forbidden();
        }
        catch (Exception e) {
            e.printStackTrace();
            return internalServerError("Oops!");
        }
    }

}
