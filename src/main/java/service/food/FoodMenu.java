package service.food;

import service.FileHandler;
import service.modes.UpdateMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
    private static List<Dish> dishes;
    private static final String filePath = "dishes.json";

    public static List<Dish> getAll() {
        if (dishes == null || dishes.isEmpty()) {
            dishes = new ArrayList<Dish>();
        }

        return dishes;
    }

    public static void display() {
        System.out.println();
        if (getAll().isEmpty()) {
            System.out.println("❌Пока что в меню нет блюд!");
            System.out.println();
            return;
        }

        for (Dish dish : getAll()) {
            dish.display();
        }
    }

    public static Dish getDishByName(String name) {
        for (Dish dish : getAll()) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }

        return null;
    }

    public static double getPriceByName(String name) {
        for (Dish dish : getAll()) {
            if (dish.getName().equals(name)) {
                return dish.getPrice();
            }
        }

        return -1;
    }

    public static void add(Dish dish) {
        getAll().add(dish);
        FileHandler.save(dishes, filePath);
    }

    public static void delete(String name) {
        for (int i = 0; i <= getAll().size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                dishes.remove(i);
                break;
            }
        }

        FileHandler.save(dishes, filePath);
    }

    public static void update(String name, double price) {
        for (int i = 0; i <= getAll().size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                dishes.get(i).setPrice(price);
                break;
            }
        }

        FileHandler.save(dishes, filePath);
    }

    public static void update(String name, int data, UpdateMode mode) {
        for (int i = 0; i <= getAll().size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                if (mode == UpdateMode.COUNT) {
                    dishes.get(i).setCount(data);
                } else {
                    dishes.get(i).setPrepareTime(data);
                }
                break;
            }
        }

        FileHandler.save(dishes, filePath);
    }
}
