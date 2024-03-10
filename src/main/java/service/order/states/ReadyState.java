package service.order.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import service.order.Order;

public class ReadyState extends OrderState {
    public ReadyState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n- ЗАКАЗ: Id " + order.getId());
        System.out.println("Статус готовности заказа: \uD83D\uDFE2 (Готов)");

        super.display();
    }

    @Override @JsonProperty("readyState")
    public String getReadyState() { return "READY"; }

    @Override
    public void getProcessed() { }

    @Override
    public void getAccepted() { }
    @Override
    public void getReady() { }
}
