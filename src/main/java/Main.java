import auth.UserDatabase;
import auth.user.Admin;
import auth.user.User;
import auth.user.Visitor;
import service.food.Dish;
import service.food.FoodMenu;
import service.handlers.AdminUIMenu;
import service.handlers.VisitorUIMenu;
import service.order.Order;
import service.order.OrderDatabase;
import service.order.OrderService;
import service.util.UserUtil;

public class Main {
    public static void main(String[] args) {
        User admin = new Admin("flowykk", UserUtil.sha256("flowykk"));
        User visitor = new Visitor("flowykk2", UserUtil.sha256("flowykk"));

        UserDatabase.addUser(admin);
        UserDatabase.addUser(visitor);

        Dish borsh = new Dish("a", 100, 10);
        Dish pizza = new Dish("b", 150, 10);
        Dish filet_mignon = new Dish("c", 300, 10);

        FoodMenu.add(borsh);
        FoodMenu.add(pizza);
        FoodMenu.add(filet_mignon);

        Order order = new Order();
        order.add(borsh);
        order.add(pizza);

        OrderDatabase.add(order);
//
//        AuthService authService = new AuthService();
//        authService.registerUser(UserModes.ADMIN);

        //AuthService authService = new AuthService();
        //AuthHandler authHandler = new AuthHandler(authService);
        //authHandler.run();

//        AdminUIMenu menu = new AdminUIMenu();
//        menu.run();

        VisitorUIMenu menu = new VisitorUIMenu();
        menu.run();

//        OrderService orderService = new OrderService();
//        orderService.create();


//
//        Double price = DishUtil.inputPrice();
//        if (price == 0.0) {
//            System.out.println(price);
//        }
//        System.out.println(price);
//
//        DishService dishService = new DishService();
//        dishService.create();
    }
}