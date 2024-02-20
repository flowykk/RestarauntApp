package org.example;

import auth.AuthHandler;
import auth.AuthService;
import auth.user.Admin;
import auth.user.User;
import auth.UserManager;
import auth.user.Visitor;
import service.util.modes.UserModes;
//import auth.user.UserType;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        User admin = new Admin("flowykk", "123");
//        User visitor = new Visitor("flowykk2", "123");
//
//        UserManager.addUser(admin);
//        UserManager.addUser(visitor);
//
//        AuthService authService = new AuthService();
//        authService.registerUser(UserModes.ADMIN);

        AuthService authService = new AuthService();
        AuthHandler authHandler = new AuthHandler(authService);

        authHandler.run();
    }
}