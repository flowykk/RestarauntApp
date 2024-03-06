package service.food;

import service.modes.UpdateMode;
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
        if (name == null || name.equals("")) {
            return;
        }

        FoodMenu.delete(name);

        System.out.println("✅ Блюдо успешно удалено из меню!");
        System.out.println();
    }

    public void updatePrice() {
        System.out.println("Введите информацию о блюде для изменения:");
        String name = DishUtil.InputDishName();
        if (name == null || name.equals("")) {
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
        if (name == null || name.equals("")) {
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

}
