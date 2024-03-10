package service;

import auth.user.User;
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

    public static List<Visitor> getBlackList() {
        if (blackList == null)  {
            blackList = new ArrayList<>();
        }
        return blackList;
    }

    public static void addToBlackList(Visitor user) {
        if (blackList == null)  {
            blackList = new ArrayList<>();
        }

        FileHandler.saveStats("stats.json");
        blackList.add(user);
    }

    public static void displayBlackList() {
        if (blackList == null)  {
            blackList = new ArrayList<>();
            return;
        }
        if (blackList.isEmpty()) {
            System.out.println("❌ Черный список пока пуст!");
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
