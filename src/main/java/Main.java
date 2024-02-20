import auth.AuthHandler;
import auth.AuthService;
import auth.UserDatabase;
import auth.user.Admin;
import auth.user.User;
import auth.user.Visitor;
import service.dish.Dish;
import service.dish.DishService;
import service.dish.FoodMenu;
import service.handlers.AdminUIMenu;
import service.util.DishUtil;
import service.util.UserUtil;

public class Main {
    public static void main(String[] args) {
        User admin = new Admin("flowykk", UserUtil.sha256("flowykk"));
        User visitor = new Visitor("flowykk2", UserUtil.sha256("flowykk"));

        UserDatabase.addUser(admin);
        UserDatabase.addUser(visitor);

        Dish borsh = new Dish("a", 100);
        Dish pizza = new Dish("b", 150);
        Dish filet_mignon = new Dish("c", 300);

        FoodMenu.add(borsh);
        FoodMenu.add(pizza);
        FoodMenu.add(filet_mignon);
//
//        AuthService authService = new AuthService();
//        authService.registerUser(UserModes.ADMIN);

        //AuthService authService = new AuthService();
        //AuthHandler authHandler = new AuthHandler(authService);
        //authHandler.run();

        AdminUIMenu menu = new AdminUIMenu();
        menu.run();
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