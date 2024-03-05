package service;

import auth.user.Visitor;

import java.util.List;

public class RestaurantService {
    private static int totalRevenue;
    private static List<Visitor> blackList;

    public static int updateTotalRevenue(int value) {
        totalRevenue += value;
        return totalRevenue;
    }

    public static int getTotalRevenue() {
        return totalRevenue;
    }

    public static void getBlackList() {
        System.out.println();
        for (var visitor : blackList) {
            visitor.displayInfo();
        }
        System.out.println();
    }
}
