package service.order;

import auth.user.User;
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
}
