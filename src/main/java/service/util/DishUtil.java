package service.util;

import service.food.Dish;
import service.food.FoodMenu;

import java.util.Objects;
import java.util.Scanner;

public class DishUtil {
    public static String inputName() {
        Scanner scanner = new Scanner(System.in);
        String name;

        while (true) {
            System.out.print("Введите название блюда: ");
            name = scanner.nextLine().trim().toLowerCase();

            if (name.equals("0")) {
                return "";
            }

            if (!isValidDishName(name))  {
                System.out.println("Название блюда введено некорректно!" + "\nПовторите ввод ещё раз или введите 0 для выхода в меню.");
            } else {
                break;
            }
        }

        return name;
    }

    public static double inputPrice() {
        Scanner scanner = new Scanner(System.in);
        double price;

        while (true) {
            try {
                System.out.print("Введите цену блюда: ");
                price = scanner.nextDouble();

                if (price == -1) {
                    return 0.0;
                } else if (price < 0) {
                    System.out.println("Цена блюда должна быть больше нуля.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Цена введена некорректно." + "\nПовторите ввод ещё раз или введите -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        return price;
    }

    public static int inputInteger(String message) {
        Scanner scanner = new Scanner(System.in);
        int data;

        while (true) {
            try {
                System.out.print(message);
                data = scanner.nextInt();

                if (data == -1) {
                    return 0;
                } else if (data < 0) {
                    System.out.println("Вводимое число должно быть больше или равно нуля.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Число введено некорректно." + "\nПовторите ввод ещё раз или введите -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        return data;
    }

    public static String InputDishName() {
        String name = DishUtil.inputName();
        Dish dish = FoodMenu.getDishByName(name);
        if (Objects.equals(name, "")) {
            return null;
        } else if (dish == null) {
            System.out.println("❌ Блюда с таким названием не существует!");
            return "";
        }

        return name;
    }

    private static boolean isValidDishName(String name) { return name.matches("^[a-zA-Z0-9\\\\s]+$"); }
}
