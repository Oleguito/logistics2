package database;

import entities.CargoProfile;
import entities.User;
import entities.UserFields;
import enums.Role;
import utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBase implements database.interfaces.DataBase {
    
    List<User> users;
    List<CargoProfile> cargoProfiles;
    
    public DataBase() {
        this.users = getUsersFromDB();;
        this.cargoProfiles = getCargoProfilesFromDB();
    }
    
    public List <CargoProfile> getCargoProfiles() {
        return cargoProfiles;
    }
    
    private List <CargoProfile> getCargoProfilesFromDB() {
        return null;
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    private List <User> getUsersFromDB () {
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
                    .role(Role.valueOf(split[4]))
                    .age(Integer.parseInt(split[5]))
                    .build()));
        }
        
        return res;
    }
    
    public void createTable(String title, String[] columns) {
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
