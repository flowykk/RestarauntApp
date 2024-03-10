package service.food;

import service.modes.UpdateMode;
import service.order.OrderDatabase;
import service.util.DishUtil;

import java.util.Objects;
import java.util.Scanner;

public class DishService {
    public void create() {
        System.out.println("Введите информацию о блюде для добавления:");
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) != null) {
            System.out.println("❌ Блюдо с таким названием уже существует!");
            return;
        }

        double price = DishUtil.inputPrice();
        if (price == 0) {
            return;
        }

        int prepareTime = DishUtil.inputInteger("Введите время приготовления блюда: ");
        if (prepareTime == 0) {
            return;
        }

        Dish dish = new Dish(name, price, prepareTime);

        System.out.println("✅ Блюдо успешно добавлено в меню!");
        System.out.println();
        dish.display();

        FoodMenu.add(dish);
    }

    public void delete() {
        if (FoodMenu.getAll().isEmpty()) {
            return;
        }

        System.out.println("Введите информацию о блюде для удаления:");
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        FoodMenu.delete(name);

        System.out.println("✅ Блюдо успешно удалено из меню!");
        System.out.println();
    }

    public void updatePrice() {
        System.out.println("Введите информацию о блюде для изменения:");
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        double price = DishUtil.inputPrice();
        if (price == -1) {
            return;
        }

        FoodMenu.update(name, price);

        System.out.println("✅ Информация о блюде успешно обновлена!");
        System.out.println();
    }

    public void update(UpdateMode mode) {
        System.out.println("Введите информацию о блюде для изменения:");
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        int data = DishUtil.inputInteger((mode == UpdateMode.COUNT) ? "Введите количество блюда в наличии: " : "Введите время приготовления блюда: ");
        if (data == -1) {
            return;
        }

        if (mode == UpdateMode.COUNT) {
            FoodMenu.update(name, data, UpdateMode.COUNT);
        } else {
            FoodMenu.update(name, data, UpdateMode.TIME);
        }

        System.out.println("✅ Информация о блюде успешно обновлена!");
        System.out.println();
    }

    public void addFeedBack() {
        System.out.println("Введите информацию о блюде для добавления отзыва:");
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        Dish dish = FoodMenu.getDishByName(name);
        if (dish == null) {
            System.out.println("Не удалось найти блюдо!");
            return;
        }

        System.out.println("Введите содержание отзыва:");
        String text;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            text = scanner.nextLine().trim().toLowerCase();

            if (text.equals("0")) return;
            if (text.length() < 10) {
                System.out.println("Отзыв должен быть длинной более 10 символов!\nПовторите попытку или введите 0, чтобы отменить вернуться в меню!");
                continue;
            }
            break;
        }

        int mark;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Введите оценку блюда от 1 до 5: ");

                mark = scanner.nextInt();

                if (mark == -1) {
                    return;
                } else if (mark < 1 || mark > 5) {
                    System.out.println("Цена блюда должна быть от 1 до 5.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Оценка введена некорректно." + "\nПовторите ввод ещё раз или введите -1 для выхода в меню.");
                scanner.nextLine();
            }
        }

        FeedBack feedBack = new FeedBack(mark, text);
        dish.addFeedBack(feedBack);
        System.out.println("✅ Отзыв успешно добавлен!");
    }
}
