import auth.AuthHandler;
import auth.AuthService;
import auth.UserManager;
import auth.user.Admin;
import auth.user.User;
import auth.user.Visitor;
import service.util.Util;

public class Main {
    public static void main(String[] args) {
        User admin = new Admin("flowykk", Util.sha256("flowykk"));
        User visitor = new Visitor("flowykk2", Util.sha256("flowykk"));

        UserManager.addUser(admin);
        UserManager.addUser(visitor);
//
//        AuthService authService = new AuthService();
//        authService.registerUser(UserModes.ADMIN);

        AuthService authService = new AuthService();
        AuthHandler authHandler = new AuthHandler(authService);

        authHandler.run();

    }
}