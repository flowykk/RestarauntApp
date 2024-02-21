package service.util;

import service.food.FoodMenu;
import service.order.Order;

import java.util.List;
import java.util.Objects;

public class OrderUtil {
    public static Order inputOrder() {
        Order resultOrder = new Order();

        String dishName = "-1";
        while (!Objects.equals(dishName, null)) {
            dishName = DishUtil.InputNameForUpdating();

            if (dishName != null) {
                resultOrder.add(FoodMenu.getDishByName(dishName));
            }
        }

        return resultOrder;
    }
}
