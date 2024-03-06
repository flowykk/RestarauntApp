package service.order;

import auth.user.User;
import service.RestaurantStats;
import service.modes.orderModes.paymentStatusMode;
import service.util.OrderUtil;

public class OrderService {
    public void create() {
        System.out.println("Введите информацию для создания нового заказа.");
        System.out.println("При вводе названия блюда, введите 0, чтобы сохранить текущий заказ:");

        Order order = OrderUtil.inputOrder();
        if (order.getDishes().isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("✅ Ваш заказ успешно создан:");
        order.getReadyState().display();

        OrderDatabase.add(order);
    }

    public void pay() {
        OrderDatabase.display();

        System.out.println("Введите инфорацию о заказе для оплаты: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка!");
            return;
        } else if (order.getPaymentStatus() == paymentStatusMode.PAID) {
            System.out.println("Ваше Заказ Id " + id + " уже оплачен!");
            return;
        }

        order.pay();

        System.out.println("Оплата произошла успешно!");
    }
}
