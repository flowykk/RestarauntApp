package service.util;

import java.util.Scanner;

import static service.dish.FoodMenu.*;

public class DishUtil {
    public static String inputName() {
        Scanner scanner = new Scanner(System.in);
        String name;

        while (true) {
            System.out.print("Введите название блюда: ");
            name = scanner.nextLine().trim().toLowerCase();

            if (name.equals("00")) {
                return "";
            }

            if (!isValidDishName(name))  {
                System.out.println("Название блюда введено некорректно!" + "\nПовторите ввод ещё раз или введите 00 для выхода в меню.");
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

    public static int inputCount() {
        Scanner scanner = new Scanner(System.in);
        int count;

        while (true) {
            try {
                System.out.print("Введите текущее количество блюда в наличии: ");
                count = scanner.nextInt();

                if (count == -1) {
                    return 0;
                } else if (count < 0) {
                    System.out.println("количество должно быть больше или равно нуля.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Число введено некорректно." + "\nПовторите ввод ещё раз или введите -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        return count;
    }

    private static boolean isValidDishName(String name) { return name.matches("^[a-zA-Z0-9\\\\s]+$"); }
}
