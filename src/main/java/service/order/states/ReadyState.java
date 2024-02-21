package service.order.states;

import service.order.Order;

public class ReadyState extends OrderState {
    public ReadyState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n- ЗАКАЗ " + order.getId());
        System.out.println("Статус готовности заказа: \uD83D\uDFE2 (Готов)");

        super.display();
    }

    @Override
    public void getProcessed() { }

    @Override
    public void getReady() { }
}
