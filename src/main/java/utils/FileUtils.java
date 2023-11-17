package utils;

import entities.CargoProfile;
import service.Encrypt;
import settings.Settings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    
    public static String readEncryptedFile(String filename) {
        String result = "";
        var file = new java.io.File(Settings.filesDir + filename);
        try {
            result = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            e.getMessage();
        }
        return Encrypt.decryptString(result);
    }
    
    public static String readFile(String filename) {
        StringBuffer contents = new StringBuffer();
        String line;
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader(Settings.filesDir + filename);
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
    
    public static void serialize(String filename, List<? extends CargoProfile> o) {
        // validateFileName(filename);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Settings.filesDir + filename))) {
            outputStream.writeObject(o);
            // System.out.println("Список сериализован в файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void serializeA(Object o, String filename) {
        // validateFileName(filename);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Settings.filesDir + filename))) {
            outputStream.writeObject(o);
            // System.out.println("Список сериализован в файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Object deserializeA(String filename) {
        validateFileName(filename);
        try (ObjectInputStream inputStream
                     = new ObjectInputStream(new FileInputStream((Settings.filesDir + filename)))) {
            
            var deserializedList =  inputStream.readObject();
            // System.out.println("Список десериализован из файла");
            return deserializedList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<CargoProfile> deserialize(String filename) {
        validateFileName(filename);
        try (ObjectInputStream inputStream
                     = new ObjectInputStream(new FileInputStream(Settings.filesDir + filename))) {
            
            List<CargoProfile> deserializedList = (List<CargoProfile>) inputStream.readObject();
            // System.out.println("Список десериализован из файла");
            return deserializedList;
        } catch (InvalidClassException e) {
            File file = new File(Settings.filesDir + filename );
            file.delete();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static void validateFileName(String filename) {
        if(!new File(Settings.filesDir + filename).exists()) {
            throw new RuntimeException("Этот файл еще не существует!!!");
        }
    }
    
    public static void listDirs() {
        var dir = new File("C:/Users/1/Desktop/PROJECT_3/test");
        listDir(dir, 0);
    }
    
    private static void listDir(File dir, int depth) {
        
        String indent = getIndent(depth);
        String childIndent = getIndent(depth + 1);
        
        if(dir.isFile()) {
            System.out.println(childIndent + dir.getName());
        } else if (dir.isDirectory()) {
            var children = dir.listFiles();
            var childFiles = Arrays.stream(children).filter(c -> c.isFile()).toList();
            var childDirs = Arrays.stream(children).filter(c -> c.isDirectory()).toList();
            System.out.println(indent + "<" + dir.getName() + ">");
        
        
            if(childFiles.isEmpty()) {
                // System.out.println(childIndent + "[No files]");
            } else {
                for (var i : childFiles) {
                    System.out.println(childIndent + "[" + i.getName() + "]");
                }
            }
            // System.out.println("");
            if(childDirs.isEmpty()) {
                // System.out.println(childIndent + "[No dirs]");
            } {
                for(var i : childDirs) {
                    listDir(i, depth + 1);
                }
            }
        }
    }
    
    static String getIndent(int depth) {
        StringBuilder indent = new StringBuilder(8);
        indent.append("├");
        for (int i = 1; i < depth * 8; i++) {
            indent.append("─");
        }
        return indent.toString();
    }
}
