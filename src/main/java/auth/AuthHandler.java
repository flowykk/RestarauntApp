package auth;


import service.ConsoleUI;
import service.handlers.MenuEntity;
import service.modes.UserModes;

import java.util.Scanner;

public class AuthHandler implements MenuEntity {
    private AuthService authService;
    private ConsoleUI consoleUI;
    private Scanner scanner;

    public AuthHandler(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        displayMenu();
        handleMenuInput();
    }

    public void displayMenu() {
        System.out.println("Войдите или зарегистрируйтесь в аккаунт");
        System.out.println("1. Войти в аккаунт");
        System.out.println("2. Создать аккаунт Посетителя");
        System.out.println("3. Создать аккаунт Админа");
        System.out.println("0. Выход");
    }

    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 3: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    MenuEntity menu = authService.authenticateUser();
                    if (menu != null) {
                        menu.run();
                    } else {
                        System.out.println("Авторизация не удалась!");
                    }
                    break;
                case "2":
                    if (authService.registerUser(UserModes.VISITOR)) {
                        run();
                    } else {
                        System.out.println("Регистрация не удалась!");
                    }
                    break;
                case "3":
                    if (authService.registerUser(UserModes.ADMIN)) {
                        run();
                    } else {
                        System.out.println("Регистрация не удалась!");
                    }
                    break;
                case "0":
                    System.exit(0);
                    return;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите действие от 0 до 2.");
            }
        }
    }
}
