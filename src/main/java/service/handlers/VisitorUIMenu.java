package service.handlers;

import java.util.Scanner;

public class VisitorUIMenu implements UIMenuEntity {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        displayMenu();
        handleMenuInput();
    }

    @Override
    public void displayMenu() {
        System.out.println("Добро пожаловать в главное меню VISITOR!");
        System.out.println("0. Выход");
    }

    @Override
    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 7: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                // ..
                case "0":
                    System.exit(0);
                    return;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите действие от 0 до 7.");
            }

            run();
        }
    }
}
