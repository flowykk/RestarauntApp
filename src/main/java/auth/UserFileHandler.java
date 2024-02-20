package auth;

import auth.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;


public class UserFileHandler {
    private static String dataFolderPath = "Data";
    private static String usersFilePath = "users.json";

    public static void saveUsers(List<User> users) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, usersFilePath);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonContent = objectMapper.writeValueAsString(users);
            objectMapper.writeValue(new File(filePath), users);

            System.out.println("Данные успешно записаны в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }
}
