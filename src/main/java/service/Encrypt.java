package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Encrypt {
    public static void encryptFile(String filename) {
        writeFile(filename, encryptString(readFile(filename)));
    }
    
    public static void decryptFile(String filename) {
        writeFile(filename, decryptString(readFile(filename)));
    }
    
    public static String readFile(String filename) {
        String contents = "";
        String line;
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                contents += line + "\n";
            }
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException("Файл не существует либо ошибка чтения", e);
        }
        return contents;
    }
    
   
    public static void writeFile (String filename, String text) {
        FileWriter writer;
        try {
            writer = new FileWriter("filename.txt");
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static String decryptString(String string) {
        char[] contentsArray = string.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contentsArray.length; i++) {
            result.append(decryptSymbol(contentsArray[i]));
        }
        return result.toString();
    }
    
    private static String encryptString(String string) {
        char[] contentsArray = string.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contentsArray.length; i++) {
            result.append(encryptSymbol(contentsArray[i]));
        }
        return result.toString();
    }
    
    private static char encryptSymbol(char symbol) {
        return (char) (symbol + 255);
    }
    
    private static char decryptSymbol(char symbol) {
        return (char) (symbol - 255);
    }
    
    
}
