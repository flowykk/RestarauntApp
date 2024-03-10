package service.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import service.RestaurantStats;
import service.food.Dish;
import service.modes.orderModes.paymentStatusMode;
import service.order.states.AcceptedState;
import service.order.states.OrderState;

import java.util.ArrayList;
import java.util.List;

import static service.modes.orderModes.paymentStatusMode.NOTPAID;
import static service.modes.orderModes.paymentStatusMode.PAID;


public class Order implements Runnable {
    @JsonProperty("Id")
    private int id;
    @JsonProperty("dishes")
    private List<Dish> dishes;
    @JsonProperty("totalPrice")
    private double totalPrice;
    @JsonProperty("totalPrepareTime")
    private int totalPrepareTime;
    private paymentStatusMode paymentStatus;
    private OrderState readyState;

    public Order() {
        id = OrderDatabase.getAll().size() + 1;
        dishes = new ArrayList<Dish>();
        totalPrice = 0;
        totalPrepareTime = 0;
        paymentStatus = NOTPAID;
        readyState = new AcceptedState(this);
    }

    public Order(@JsonProperty("Id") int id, @JsonProperty("dishes") List<Dish> dishes, @JsonProperty("totalPrice") double totalPrice, @JsonProperty("totalPrepareTime") int totalPrepareTime, @JsonProperty("readyStatus") OrderState readyState) {
        this.id = id;
        this.dishes = dishes;
        this.totalPrice = totalPrice;
        this.totalPrepareTime = totalPrepareTime;
        this.readyState = readyState;
        this.paymentStatus = NOTPAID;
    }

    public void changeState(OrderState state) {
        this.readyState = state;
    }

    public List<Dish> getDishes() { return dishes; }

    public int getId() { return id; }

    public void computeTotalPrice() {
        totalPrice = 0;
        for (Dish item : dishes) {
            totalPrice += item.getPrice();
        }
    }

    public void computeTotalPrepareTime() {
        totalPrepareTime = 0;
        for (Dish item : dishes) {
            totalPrepareTime += item.getPrepareTime();
        }
    }

//    public void display() {
//        System.out.println("\n- ЗАКАЗ " + id);
//        System.out.print("Статус готовности заказа: ");
//        if (readyStatus == INPROCESS) {
//            System.out.println("\uD83D\uDD34");
//        } else if (readyStatus == READY) {
//            System.out.println("\uD83D\uDFE1");
//        } else {
//            System.out.println("\uD83D\uDFE2");
//        }
//
//        System.out.print("Статус оплаты заказа: ");
//        if (paymentStatus == PAID) {
//            System.out.println("\uD83D\uDD34");
//        } else if (paymentStatus == NOTPAID) {
//            System.out.println("\uD83D\uDFE2");
//        }
//
//        System.out.println("Стоимость заказа: " + totalPrice + " $");
//        System.out.println();
//
//        for (Dish dish : dishes) {
//            System.out.println("Блюдо: " + dish.getName());
//            System.out.println("Цена: " + dish.getPrice() + " $");
//            System.out.println();
//        }
//    }

    public paymentStatusMode getPaymentStatus() {
        return paymentStatus;
    }

    public OrderState getReadyState() {
        return readyState;
    }

    @JsonProperty("readyState")
    public String getReadyStateString() { return readyState.getReadyState(); }

    public double getTotalPrice() { return totalPrice; }

    public int getTotalPrepareTime() { return totalPrepareTime; }

    public void pay() {
        paymentStatus = PAID;
        RestaurantStats.updateTotalRevenue(totalPrice);
    }

    public void add(Dish dish) {
        dishes.add(dish);
        computeTotalPrice();
        computeTotalPrepareTime();
    }

    public void run() {
        System.out.println(totalPrepareTime * 1000L / 2);
        readyState.display();
        long millis = totalPrepareTime * 1000L / 2;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        readyState.getProcessed();

        readyState.display();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        readyState.getReady();
        readyState.display();
    }
}
