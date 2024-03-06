package service.order;

import auth.user.User;
import service.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {
    private static List<Order> orders;
    private final static String filePath = "orders.json";

    public static List<Order> getAll() {
        if (orders == null) {
            orders = new ArrayList<Order>();
        }

        return orders;
    }

    public static void add(Order order) {
        orders = getAll();
        orders.add(order);
        FileHandler.save(orders, filePath);
    }

    public static Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }

        return null;
    }

    public static void display() {
        if (orders.isEmpty()) {
            System.out.println("❌ Заказов пока что нет!");
        }

        for (var order : orders) {
            order.getReadyState().display();
        }
    }
}
