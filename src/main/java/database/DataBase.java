package database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class DataBase {
    public static void createTable(String title, String[] columns) {
        StringBuffer contents = new StringBuffer();
        for (int i = 0; i < columns.length; i++) {
            contents.append(columns[i] + ";");
        }
        File file = new File("src/main/resources/table.db");
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
