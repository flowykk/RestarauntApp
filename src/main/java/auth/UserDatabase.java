package auth;

import auth.user.User;
import service.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static List<User> users;
    private static final String filePath = "users.json";

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
        FileHandler.save(users, filePath);
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
