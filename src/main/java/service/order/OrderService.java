package service.order;

import service.food.FoodMenu;
import service.modes.orderModes.paymentStatusMode;
import service.util.DishUtil;
import service.util.OrderUtil;

import java.util.Objects;

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

    public void addDishToOrder() {
        System.out.println("Введите информацию о заказе для добавления блюда: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("❌ Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("❌ Ваш Заказ Id " + id + " уже готов, нельзя добавить в него блюда!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "INPROCESS")) {
            System.out.println("❌ Ваш Заказ Id " + id + " уже готовится, нельзя добавить в него блюда!");
            return;
        }

        FoodMenu.display();
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("❌ Блюда с таким названием не существует!");
            return;
        }

        OrderDatabase.addDish(order, FoodMenu.getDishByName(name));
        System.out.println("✅ Ваш заказ успешно обновлён:");
        order.getReadyState().display();
    }

    public void deleteDishFromOrder() {
        System.out.println("Введите информацию о заказе для удаления блюда: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("❌ Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("❌ Ваш Заказ Id " + id + " уже готов, нельзя удалить из него блюда!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "INPROCESS")) {
            System.out.println("❌ Ваш Заказ Id " + id + " уже готовится, нельзя удалить из него блюда!");
            return;
        }

        if (order.getDishes().size() <= 1) {
            System.out.println("В заказе только одно блюдо!\nВместо удаления блюда, можно удалить заказ!");
            return;
        }

        order.getReadyState().display();
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("❌ Блюда с таким названием не существует!");
            return;
        } else if (!order.getDishes().contains(FoodMenu.getDishByName(name))) {
            System.out.println("❌ Блюда с таким названием нету в Заказе Id " + id + "!");
            return;
        }

        OrderDatabase.deleteDish(order, FoodMenu.getDishByName(name));
        System.out.println("✅ Ваш заказ успешно обновлён:");
        order.getReadyState().display();
    }

    public void cancel() {
        System.out.println("Введите информацию о заказе для отмены: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("❌ Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("❌ Ваш Заказ Id " + id + " уже готов, его нельзя отменить!");
            return;
        }

        OrderDatabase.delete(order);
        System.out.println("✅ Отмена заказа произошла успешно!");
    }

    public void pay() {
        System.out.println("Введите информацию о заказе для оплаты: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("❌ Произошла ошибка!");
            return;
        } else if (order.getPaymentStatus() == paymentStatusMode.PAID) {
            System.out.println("❌ Ваш Заказ Id " + id + " уже оплачен!");
            return;
        }

        order.pay();

        System.out.println("✅ Оплата произошла успешно!");
    }
}
