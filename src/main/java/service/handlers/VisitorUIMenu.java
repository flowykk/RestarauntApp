package service.handlers;

import service.food.Dish;
import service.food.DishService;
import service.food.FoodMenu;
import service.order.Order;
import service.order.OrderDatabase;
import service.order.OrderService;

import java.util.Scanner;

public class VisitorUIMenu implements UIMenuEntity {
    private final Scanner scanner;
    private final OrderService orderService;
    private final DishService dishService;

    public VisitorUIMenu() {
        scanner = new Scanner(System.in);
        orderService = new OrderService();
        dishService = new DishService();
    }

    @Override
    public void run() {
        displayMenu();
        handleMenuInput();
    }

    @Override
    public void displayMenu() {
        System.out.println("Добро пожаловать в главное меню VISITOR!");
        System.out.println("1. Посмотреть меню");
        System.out.println("2. Посмотреть заказы");
        System.out.println("3. Сделать заказ");
        System.out.println("4. Добавить блюдо в заказ");
        System.out.println("5. Убрать блюдо из заказа");
        System.out.println("6. Отменить заказ");
        System.out.println("7. Заплатить за заказ");
        System.out.println("8. Добавить отзыв на блюдо");
        System.out.println("0. Выход");
    }

    @Override
    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 8: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    FoodMenu.display();
                    break;
                case "2":
                    OrderDatabase.display();
                    break;
                case "3":
                    FoodMenu.display();
                    orderService.create();
                    break;
                case "4":
                    OrderDatabase.display();
                    orderService.addDishToOrder();
                    break;
                case "5":
                    OrderDatabase.display();
                    orderService.deleteDishFromOrder();
                    break;
                case "6":
                    OrderDatabase.display();
                    orderService.cancel();
                    break;
                case "7":
                    OrderDatabase.display();
                    orderService.pay();
                    break;
                case "8":
                    FoodMenu.display();
                    dishService.addFeedBack();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, выберите действие от 0 до 8.");
            }

            run();
        }
    }
}
