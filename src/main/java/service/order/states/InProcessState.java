package service.order.states;

import service.order.Order;

public class InProcessState extends OrderState {
    public InProcessState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n- ЗАКАЗ " + order.getId());
        System.out.println("Статус готовности заказа: \uD83D\uDFE1 (Готовится)");

        super.display();
    }

    @Override
    public String getReadyState() {
        return "INPROCESS";
    }

    @Override
    public void getReady() {
        order.changeState(new ReadyState(order));
    }

    @Override
    public void getProcessed() { }
}
