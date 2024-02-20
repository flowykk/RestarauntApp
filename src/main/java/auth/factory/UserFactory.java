package auth.factory;

import auth.user.Admin;
import auth.user.User;

public interface UserFactory {
    public User createUser(String userName, String passwordHash);
}

