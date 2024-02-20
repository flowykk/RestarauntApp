package service.dish;

import service.modes.UpdateModes;
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
        if (price == -1) {
            return;
        }

        int prepareTime = DishUtil.inputInteger("Введите время приготовления блюда: ");
        if (prepareTime == -1) {
            return;
        }

        Dish dish = new Dish(name, price, prepareTime);
        FoodMenu.add(dish);

        System.out.println("✅ Блюдо успешно добавлено в меню!");
        System.out.println();
    }

    public void delete() {
        if (FoodMenu.getAll().isEmpty()) {
            return;
        }

        System.out.println("Введите информацию о блюде для удаления:");
        String name = DishUtil.InputNameForUpdating();
        if (name == null) {
            return;
        }

        FoodMenu.delete(name);

        System.out.println("✅ Блюдо успешно удалено из меню!");
        System.out.println();
    }

    public void updatePrice() {
        System.out.println("Введите информацию о блюде для изменения:");
        String name = DishUtil.InputNameForUpdating();
        if (name == null) {
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

    public void update(UpdateModes mode) {
        System.out.println("Введите информацию о блюде для изменения:");
        String name = DishUtil.InputNameForUpdating();
        if (name == null) {
            return;
        }

        int data = DishUtil.inputInteger((mode == UpdateModes.COUNT) ? "Введите количество блюда в наличии: " : "Введите время приготовления блюда: ");
        if (data == -1) {
            return;
        }

        if (mode == UpdateModes.COUNT) {
            FoodMenu.update(name, data, UpdateModes.COUNT);
        } else {
            FoodMenu.update(name, data, UpdateModes.TIME);
        }

        System.out.println("✅ Информация о блюде успешно обновлена!");
        System.out.println();
    }

}
