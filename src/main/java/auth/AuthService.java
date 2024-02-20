package auth;

import auth.factory.AdminFactory;
import auth.factory.UserFactory;
import auth.factory.VisitorFactory;
import auth.user.Admin;
import auth.user.User;
//import auth.user.UserType;
import service.util.Util;
import service.util.modes.InfoModes;
import service.util.modes.UserModes;

public class AuthService {
    private UserFactory userFactory;

    public boolean registerUser(UserModes mode) {
        System.out.println("Введите данные для регистрации.");
        String username = Util.handleInfoInput(
                "Введите имя пользователя: ",
                "Имя пользователя введено некорректно!\n" +
                        "Имя пользователя должно состоять из не менее чем 6-ти латинских букв или цифр!",
                InfoModes.USERNAME
        );

        if (username.isEmpty()) {
            return false;
        }

        if (UserManager.getAll().stream().anyMatch(user -> user.getUserName().equals(username))) {
            System.out.println("Пользователь с именем \"" + username + "\" уже существует.");
            return false;
        }

        String password = Util.handleInfoInput(
                "Придумайте свой пароль: ",
                "Пароль введён некорректно!\n" +
                        "Пароль должен состоять из не менее чем 4-ёх латинских букв или цифр!",
                InfoModes.PASSWORD
        );
        String hashedPassword = Util.sha256(password);

        User newUser;
        if (mode == UserModes.VISITOR) {
            userFactory = new VisitorFactory();
        } else {
            userFactory = new AdminFactory();
        }

        newUser = userFactory.createUser(username, hashedPassword);
        UserManager.addUser(newUser);
        System.out.println("Пользователь \"" + username + "\" успешно зарегистрирован.");

        return true;
    }

    public boolean authenticateUser() {
        System.out.println("Введите данные для авторизации.");
        String username = Util.handleInfoInput(
                "Введите имя пользователя: ",
                "Имя пользователя введено некорректно!\n" +
                        "Имя пользователя должно состоять из не менее чем 6-ти латинских букв или цифр!",
                InfoModes.USERNAME
        );

        if (username.isEmpty()) {
            return false;
        }

        User user = UserManager.getAll().stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElse(null);

        String password = Util.handleInfoInput(
                "Введите пароль: ",
                "Пароль введён некорректно!\n" +
                        "Пароль должен состоять из не менее чем 4-ёх латинских букв или цифр!",
                InfoModes.PASSWORD
        );
        String hashedPassword = Util.sha256(password);

        if (user != null && user.getPassword().equals(hashedPassword)) {
            String userType = user.getUserTypeValue();
            System.out.println("Авторизация произошла успешно. Добро пожаловать, " + userType + " " + user.getUserName() + "!");
            return true;
        } else {
            System.out.println("Неправильно введено имя пользователя или пароль. Попробуйте ещё раз.");
            return false;
        }
    }
}