package security;


import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class AdminSecured extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context ctx) {
        String username = ctx.session().get("username");
        if (username == null || !username.equals("admin"))
            return null;
        return "admin";
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return super.onUnauthorized(ctx);
    }
}