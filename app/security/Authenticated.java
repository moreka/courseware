package security;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class Authenticated extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("username");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return super.onUnauthorized(ctx);
    }
}
