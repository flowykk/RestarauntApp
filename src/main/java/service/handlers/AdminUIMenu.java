package service.handlers;

import auth.UserDatabase;
import service.RestaurantStats;
import service.food.DishService;
import service.food.FoodMenu;
import service.modes.UpdateMode;
import service.util.UserUtil;

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
        System.out.println("Добро пожаловать в главное меню ADMIN!");
        System.out.println("1. Посмотреть меню");
        System.out.println("2. Добавить блюдо в меню");
        System.out.println("3. Удалить блюдо из меню");
        System.out.println("4. Обновить количество для блюда");
        System.out.println("5. Обновить цену для блюда");
        System.out.println("6. Обновить время выполнения блюда");
        System.out.println("7. Посмотреть выручку ресторана");
        System.out.println("8. Посмотреть черный список ресторана");
        System.out.println("9. Добавить пользователя в чёрный список");
        System.out.println("10. Получить список зарегистрированных админов");
        System.out.println("11. Посмотреть самые популярные блюда");
        System.out.println("0. Выход");
    }

    @Override
    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 11: ");
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
                case "7":
                    RestaurantStats.display();
                    break;
                case "8":
                    RestaurantStats.displayBlackList();
                    break;
                case "9":
                    UserUtil.handleAddingToBlackList();
                    break;
                case "10":
                    UserDatabase.displayAdmins();
                case "11":
                    FoodMenu.displayPopular();
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите действие от 0 до 11.");
            }

            run();
        }
    }
}
