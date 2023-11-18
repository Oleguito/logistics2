package database;

import entities.CargoProfile;
import entities.User;
import entities.UserFields;
import enums.Role;
import service.Encrypt;
import settings.Settings;
import utils.FileUtils;
import zapplication.COP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DataBase implements database.interfaces.DataBase {
    
    List<User> users;
    List<CargoProfile> cargoProfiles;
    
    public DataBase() {
        this.users = getUsersFromDB();
        this.cargoProfiles = getCargoProfilesFromDB();
    }
    
    public List <CargoProfile> getCargoProfiles() {
        if(cargoProfiles != null) return cargoProfiles;
        return getCargoProfilesFromDB();
    }
    
    private List <CargoProfile> getCargoProfilesFromDB() {
        String filename = "object.dat";
        if(!new File(Settings.filesDir + filename).exists()) {
            var res = new ArrayList<CargoProfile>();
            FileUtils.serialize(filename, res);
            Encrypt.encryptFile(filename);
            return res;
        }
        Encrypt.decryptFile(filename);
        var res = FileUtils.deserialize(filename);
        Encrypt.encryptFile(filename);
        // System.err.println("Профили грузов прочитаны");
        if(res == null) return new ArrayList<CargoProfile>();
        return res;
    }
    
    public Optional <User> getUserBy(UUID uuid) {
        var resultUser = users.parallelStream()
                                             .filter(u -> {return u.getFields().getUuid().equals(uuid);})
                                             .findFirst();
        return resultUser;
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    private List <User> getUsersFromDB () {
        String filename = "users.db";
        Encrypt.decryptFile(filename);
        String fileContents = FileUtils.readTextFile(filename);
        Encrypt.encryptFile(filename);
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
        File file = new File(Settings.filesDir + title + ".db");
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
    
    public Optional<CargoProfile> getCargoProfileByUUID(UUID uuid) {
        if(cargoProfiles == null) return Optional.ofNullable(null);
        return cargoProfiles.parallelStream().filter(p -> {
            return p.getUuid().equals(uuid);
        }).findFirst();
    }
}
