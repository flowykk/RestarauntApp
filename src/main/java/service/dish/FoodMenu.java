package service.dish;

import auth.UserFileHandler;
import auth.user.User;
import service.modes.UpdateModes;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
    private static List<Dish> dishes;

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
            System.out.println("Название: " + dish.getName());
            System.out.println("Цена: " + dish.getPrice() + " $");
            System.out.println("Время приготовления: " + dish.getPrepareTime() + " мин.");
            System.out.println("Количество: " + dish.getCount());
            System.out.println("Доступность: " + (dish.getAvailability() ? "\uD83D\uDFE2" : "\uD83D\uDD34"));
            System.out.println();
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
        FoodMenuFileHandler.saveDishes(dishes);
    }

    public static void delete(String name) {
        for (int i = 0; i <= getAll().size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                dishes.remove(i);
                break;
            }
        }

        FoodMenuFileHandler.saveDishes(dishes);
    }

    public static void update(String name, double price) {
        for (int i = 0; i <= getAll().size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                dishes.get(i).setPrice(price);
                break;
            }
        }

        FoodMenuFileHandler.saveDishes(dishes);
    }

    public static void update(String name, int data, UpdateModes mode) {
        for (int i = 0; i <= getAll().size(); i++) {
            if (name.equals(dishes.get(i).getName())) {
                if (mode == UpdateModes.COUNT) {
                    dishes.get(i).setCount(data);
                } else {
                    dishes.get(i).setPrepareTime(data);
                }
                break;
            }
        }

        FoodMenuFileHandler.saveDishes(dishes);
    }
}
