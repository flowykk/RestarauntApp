package service;

import auth.user.Visitor;

import java.util.ArrayList;
import java.util.List;

public class RestaurantStats {
    private static double totalRevenue;
    private static List<Visitor> blackList;

    public static double updateTotalRevenue(double value) {
        totalRevenue += value;

        FileHandler.saveStats("stats.json");
        return totalRevenue;
    }

    public static double getTotalRevenue() { return totalRevenue; }

    public static void setTotalRevenue(double totalRevenue) { RestaurantStats.totalRevenue = totalRevenue; }

    public static void setBlackList(List<Visitor> blackList) { RestaurantStats.blackList = blackList; }

    public static List<Visitor> getBlackList() { return blackList; }

    public static void displayBlackList() {
        if (blackList == null)  {
            blackList = new ArrayList<>();
            return;
        }
        System.out.println();
        for (var visitor : blackList) {
            visitor.displayInfo();
        }
        System.out.println();
    }

    public static void display() {
        System.out.println();
        System.out.println("Текущая выручка ресторана: " + totalRevenue + " $");
        System.out.println();
    }
}
