package models;

import models.user.BasicUser;

public interface IUniqueID {
    String getUID();
    BasicUser getOwner();
}
