package utils;

import settings.Settings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static String readFile(String filename) {
        StringBuffer contents = new StringBuffer();
        String line;
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader("src/main/resources/" + filename);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                contents.append(line + "\n");
            }
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException("Файл не существует либо ошибка чтения", e);
        }
        return contents.toString();
    }
    
   
    public static void writeFile (String filename, String text) {
        FileWriter writer;
        try {
            writer = new FileWriter(Settings.filesDir + filename);
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
