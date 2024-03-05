package service.handlers;

import service.food.DishService;
import service.food.FoodMenu;
import service.modes.UpdateMode;

import java.util.Scanner;

public class AdminUIMenu implements UIMenuEntity {
    private final Scanner scanner = new Scanner(System.in);
    private DishService dishService = new DishService();

    @Override
    public void run() {
        displayMenu();
        handleMenuInput();
    }

    @Override
    public void displayMenu() {
        System.out.println("Добро пожаловать в главное меню админа ресторана!");
        System.out.println("1. Посмотреть меню");
        System.out.println("2. Добавить блюдо в меню");
        System.out.println("3. Удалить блюдо из меню");
        System.out.println("4. Обновить количество для блюда");
        System.out.println("5. Обновить цену для блюда");
        System.out.println("6. Обновить время выполнения блюда");
        System.out.println("7. Посмотреть статистику рестарана");
        System.out.println("8. Посмотреть черный список ресторана");
        System.out.println("9. Получить список зарегистрированных админов");
        System.out.println("0. Выход");
    }

    @Override
    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 6: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    FoodMenu.display();
                    break;
                case "2":
                    FoodMenu.display();
                    dishService.create();

                    break;
                case "3":
                    FoodMenu.display();
                    dishService.delete();

                    break;
                case "4":
                    FoodMenu.display();
                    dishService.update(UpdateMode.COUNT);

                    break;
                case "5":
                    FoodMenu.display();
                    dishService.updatePrice();

                    break;
                case "6":
                    FoodMenu.display();
                    dishService.update(UpdateMode.TIME);

                    break;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите действие от 0 до 7.");
            }

            run();
        }
    }
}
