package auth;

import auth.UserFileHandler;
import auth.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager{
    private static List<User> users;

    public static List<User> getAll() {
        if (users == null) {
            users = new ArrayList<User>();
        }

        return users;
    }

    public static void addUser(User user) {
        if (users == null) {
            users = new ArrayList<User>();
        }

        users.add(user);
        UserFileHandler.saveUsers(users);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public boolean isValidCredentials(String username, String password) {
        User user = getUserByUsername(username);

        return user != null && user.getPassword().equals(password);
    }
}
