package service.dish;

import auth.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

public class FoodMenuFileHandler {
    private static String dataFolderPath = "Data";
    private static String dishesFilePath = "dishes.json";

    public static void saveDishes(List<Dish> dishes) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, dishesFilePath);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonContent = objectMapper.writeValueAsString(dishes);
            objectMapper.writeValue(new File(filePath), dishes);

            System.out.println("Данные успешно записаны в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }
}
