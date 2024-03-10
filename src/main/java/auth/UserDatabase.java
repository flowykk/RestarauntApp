package auth;

import auth.user.Admin;
import auth.user.User;
import auth.user.Visitor;
import service.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static List<User> users;

    private static List<Admin> admins;
    private static List<Visitor> visitors;
    private static final String filePath = "users.json";

    public static List<User> getAll() {
        if (users == null) {
            users = new ArrayList<User>();
        }

        return users;
    }

    public static void addUser(User user) {
        if (users == null) users = new ArrayList<User>();
        if (admins == null) admins = new ArrayList<Admin>();
        if (visitors == null) visitors = new ArrayList<Visitor>();

        users.add(user);
        if (user.getUserType().equals("ADMIN")) admins.add(new Admin(user.getUserName(), user.getPassword()));
        else visitors.add(new Visitor(user.getUserName(), user.getPassword()));

        FileHandler.save(users, filePath);

        FileHandler.save(admins, "admins.json");
        FileHandler.save(visitors, "visitors.json");
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public static void displayAdmins() {
        System.out.println();
        if (admins == null) admins = new ArrayList<Admin>();
        for (Admin admin : admins) {
            admin.displayInfo();
        }
        System.out.println();
    }

    public static void displayVisitors() {
        System.out.println();
        if (visitors == null) visitors = new ArrayList<Visitor>();
        for (Visitor visitor : visitors) {
            visitor.displayInfo();
        }
        System.out.println();
    }
}
