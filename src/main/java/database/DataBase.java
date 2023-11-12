package database;

import entities.User;
import entities.UserFields;
import utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBase {
    
    static List<User> users = getUsersFromDB();
    
    public static List<User> getUsers() {
        return users;
    }
    
    private static List <User> getUsersFromDB () {
        String fileContents = FileUtils.readFile("users.db");
        List<User> res = new ArrayList <>();
        String[] splitByLine = fileContents.split("\n");
        int needItemsInLine = UserFields.class.getDeclaredFields().length;
        for(var line : splitByLine) {
            String[] split = line.split(";");
            if(split.length != needItemsInLine) {
                throw new RuntimeException("Не то количество элементов в строке");
            }
            res.add(new User(UserFields.builder()
                    .uuid(UUID.fromString(split[0]))
                    .fullName(split[1])
                    .login(split[2])
                    .passwordHash(split[3])
                    .age(Integer.parseInt(split[4]))
                    .build()));
        }
        
        return res;
    }
    
    public static void createTable(String title, String[] columns) {
        StringBuffer contents = new StringBuffer();
        for (int i = 0; i < columns.length; i++) {
            contents.append(columns[i] + ";");
        }
        File file = new File("src/main/resources/" + title + ".db");
        try {
            if(!file.exists()) {
                FileWriter writer = new FileWriter(file);
                file.createNewFile();
                writer.write(contents.toString());
                writer.close();
            } else {
                throw new RuntimeException("Файл с таким именем уже есть!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
