package services;

import com.avaje.ebean.Model;
import models.IUniqueID;
import models.security.AccessType;
import models.security.Token;
import models.user.BasicUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TokenService {
    private TokenService() {}

    private static TokenService instance = new TokenService();

    public static TokenService getInstance() {
        return instance;
    }

    public static Model.Finder<Long, Token> find = new Model.Finder<>(Token.class);

    public Token createTokenFor(BasicUser user, IUniqueID item, AccessType accessType) {
        Token token = getAccessTokenTo(user, item, accessType);
        if (token == null) {
            token = new Token();
            token.user = user;
            token.uniqueID = item.getUID();
            token.accessType = accessType;
            token.save();
        }
        return token;
    }

    public Token getAccessTokenTo(BasicUser user, IUniqueID item, AccessType accessType) {
        return find.where()
            .eq("user.id", user.id)
            .eq("uniqueID", item.getUID())
            .eq("accessType", accessType)
            .findUnique();
    }

    public List<AccessType> getAccessTokensTo(BasicUser user, IUniqueID item) {
        return find.where()
                .eq("user.id", user.id)
                .eq("uniqueID", item.getUID())
                .findList().stream().map(token -> token.accessType).collect(Collectors.toList());
    }
}
