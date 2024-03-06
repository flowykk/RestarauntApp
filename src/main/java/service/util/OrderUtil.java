package service.util;

import service.food.Dish;
import service.food.FoodMenu;
import service.order.Order;
import service.order.OrderDatabase;

import java.util.Objects;
import java.util.Scanner;

public class OrderUtil {
    public static Order inputOrder() {
        Order resultOrder = new Order();

        String dishName = "-1";
        while (!Objects.equals(dishName, null)) {
            dishName = DishUtil.InputDishName();
            Dish dish = FoodMenu.getDishByName(dishName);
            if (dish == null) {
                System.out.println("Не удалось найти текущее блюдо.");
                continue;
            }

            if (dishName != null) {
                if (dish.getCount() <= 0) {
                    System.out.println("Сейчас в ресторане недостаточное количество этого блюда.");
                } else {
                    resultOrder.add(FoodMenu.getDishByName(dishName));
                    dish.setCount(dish.getCount() - 1);
                    System.out.println("Блюдо успешно добавлено в текущий заказ.");
                }
            }
        }

        return resultOrder;
    }

    public static int inputOrderById() {
        Scanner scanner = new Scanner(System.in);
        int id;

        while (true) {
            try {
                System.out.print("Введите id заказа: ");
                id = scanner.nextInt();

                if (id == -1) {
                    return 0;
                } else if (id <= 0) {
                    System.out.println("Id заказа должно быть больше нуля.");
                } else if (id > OrderDatabase.getAll().size()) {
                    System.out.println("Заказа с таким Id не существует!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Id введено некорректно." + "\nПовторите ввод ещё раз или введите -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        return id;
    }
}
