package service.order.states;

import service.food.Dish;
import service.order.Order;

import static service.modes.orderModes.paymentStatusMode.NOTPAID;
import static service.modes.orderModes.paymentStatusMode.PAID;

public class AcceptedState extends OrderState {
    public AcceptedState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n- ЗАКАЗ " + order.getId());
        System.out.println("Статус готовности заказа: \uD83D\uDD34 (Принят)");

        super.display();
    }

    @Override
    public void getProcessed() {
        order.changeState(new InProcessState(order));
    }

    @Override
    public void getReady() { }


}
