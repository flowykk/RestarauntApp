package service.util;

import auth.UserDatabase;
import auth.user.User;
import auth.user.Visitor;
import service.RestaurantStats;
import service.modes.InfoMode;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Scanner;


public class UserUtil {
    public static void handleAddingToBlackList() {
        User visitor = handleVisitorInput();
        if (visitor == null) {
            System.out.println("Не удалось найти такого посетителя!");
            return;
        } else if (RestaurantStats.getBlackList().contains(visitor)) {
            System.out.println("❌ Этот посетитель уже есть в черном списке!");
            return;
        }

        RestaurantStats.addToBlackList((Visitor) visitor);
        System.out.println("✅ Данный посетитель успешно добавлен в чёрный список ресторана!");
    }

    public static User handleVisitorInput() {
        System.out.println("\nСписок зарегистрированных посетителей:");
        UserDatabase.displayVisitors();
        User user;

        System.out.println("Введите данные о пользователе для добавления в черный список.");
        String username = UserUtil.handleInfoInput(
                "Введите идентификатор посетителя: ",
                "Имя пользователя введено некорректно!\n" +
                        "Имя пользователя должно состоять из не менее чем 6-ти латинских букв или цифр!",
                InfoMode.USERNAME
        );

        user = UserDatabase.getUserByUsername(username);

        if (user.getUserType() != null & !Objects.equals(user.getUserType(), "VISITOR")) {
            return null;
        }

        return user;
    }

    public static String handleInfoInput(String message, String errorMessage, InfoMode mode) {
        Scanner scanner = new Scanner(System.in);
        String data;

        while (true) {
            System.out.print(message);
            data = scanner.nextLine().trim().toLowerCase();

            if (data.equals("00")) {
                return "";
            }

            if ((mode == InfoMode.USERNAME && !isValidUsername(data)) ||
                    (mode == InfoMode.PASSWORD && !isValidPassword(data))) {
                System.out.println(errorMessage + "\nПовторите ввод ещё раз или введите 00 для выхода в меню.");
            } else {
                break;
            }
        }

        return data;
    }

    private static boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z0-9]{6,}");
    }

    private static boolean isValidPassword(String password) {
        return password.matches("[a-zA-Z0-9]{4,}");
    }

    public static String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

