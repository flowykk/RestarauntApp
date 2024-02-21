package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.order.Order;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

public class FileHandler {

    private static String dataFolderPath = "Data";

    public static <T> void save(List<T> data, String path) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, path);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), data);

            System.out.println("Данные успешно записаны в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }
}
