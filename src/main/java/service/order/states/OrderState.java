package service.order.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import service.food.Dish;
import service.order.Order;

import static service.modes.orderModes.paymentStatusMode.NOTPAID;

abstract public class OrderState {
    protected Order order;

    public OrderState(Order order) {
        this.order = order;
    }

    public void display() {
        System.out.print("Статус оплаты заказа: ");
        if (order.getPaymentStatus() == NOTPAID) {
            System.out.println("\uD83D\uDD34");
        } else {
            System.out.println("\uD83D\uDFE2");
        }

        System.out.println("Стоимость заказа: " + order.getTotalPrice() + " $");
        System.out.println();

        System.out.println("Состав заказа:");
        for (Dish dish : order.getDishes()) {
            System.out.println("Блюдо: " + dish.getName());
            System.out.println("Цена: " + dish.getPrice() + " $");
            System.out.println();
        }
    }

    abstract public String getReadyState();
    abstract public void getProcessed();
    abstract public void getReady();

}
