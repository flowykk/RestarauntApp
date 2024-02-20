package service.dish;

import service.util.DishUtil;

import java.util.Objects;

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
        if (price == 0.0) {
            return;
        }

        Dish dish = new Dish(name, price);
        FoodMenu.add(dish);

        System.out.println("✅ Блюдо успешно добавлено в меню!");
        System.out.println();
    }

    public void delete() {
        if (FoodMenu.getAll().isEmpty()) {
            return;
        }

        System.out.println("Введите информацию о блюде для удаления:");
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("❌ Блюда с таким названием не существует!");
            return;
        }

        Dish dish = new Dish(name, FoodMenu.getPriceByName(name));
        FoodMenu.delete(dish);

        System.out.println("✅ Блюдо успешно удалено из меню!");
        System.out.println();
    }

    public void updatePrice() {
        System.out.println("Введите информацию о блюде для изменения:");
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("❌ Блюда с таким названием не существует!");
            return;
        }

        double price = DishUtil.inputPrice();
        if (price == -1) {
            return;
        }

        Dish dish = new Dish(name, FoodMenu.getPriceByName(name));
        FoodMenu.update(dish, price);

        System.out.println("✅ Информация о блюде успешно обновлена!");
        System.out.println();
    }

    public void updateCount() {
        System.out.println("Введите информацию о блюде для изменения:");
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("❌ Блюда с таким названием не существует!");
            return;
        }

        int count = DishUtil.inputCount();
        if (count == -1) {
            return;
        }

        Dish dish = new Dish(name, FoodMenu.getPriceByName(name));
        FoodMenu.update(dish, count);

        System.out.println("✅ Информация о блюде успешно обновлена!");
        System.out.println();
    }
}
