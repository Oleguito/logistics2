package service;

import database.DataBase;
import entities.User;
import entities.UserFields;
import mappers.UserMapper;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import service.interfaces.UserServiceInterface;
import settings.Settings;
import utils.FileUtils;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserService implements UserServiceInterface {
    
    static Scanner scanner = new Scanner(System.in);
    
    static User authorizedUser = null;
    
    
    public static String getPassword() {
        System.out.println("Введите пароль");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        while(!password.matches(".+")) {
            System.out.println("Пароль неверный");
            password = scanner.nextLine();
        }
        return password;
    }
    
    private List<User> getUsersFromDB () {
        return null;
    }
    
    public void addUser() {
        DataBase.getUsers().add(createUser());
        FileUtils.writeFile("users.db", UserMapper.convertListUsersToString(DataBase.getUsers()));
    }
    
    public User createUser(){
        String FIO = getFIO();
        String login = getLogin();
        String password = getPassword();
        String passwordHash = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, Settings.userSecret)
                .hmacHex(password);
        
        return new User(UserFields.builder()
                .uuid(UUID.randomUUID())
                .fullName(FIO)
                .login(login)
                .passwordHash(passwordHash)
                .build());
    }
    
    public static String getLogin() {
        System.out.println("Введите ваш логин");
        return getInputWhileNotRegex("[A-Za-z]+");
    }
    
    private static String getFIO() {
        System.out.println();
        String firstName = getFirstName();
        String lastName = getLastName();
        String patronymicName = getPatronymicName();
        return "".join(" ", lastName, firstName, patronymicName);
    }
    
    static String getFirstName() {
        System.out.println("Введите ваше имя ");
        System.out.println("Не меньше 2 символов, не считая пробелы");
        return getInputWhileNotRegex("[A-Za-zА-Яа-я]{2,}");
    }
    
    static String getLastName() {
        System.out.println("Введите вашу фамилию ");
        System.out.println("Не меньше 2 символов, не считая пробелы");
        return getInputWhileNotRegex("[A-Za-zА-Яа-я]{2,}");
    }
    
    static String getPatronymicName() {
        System.out.println("Введите ваше отчество ");
        System.out.println("Не меньше 2 символов, не считая пробелы");
        return getInputWhileNotRegex("[A-Za-zА-Яа-я]{2,}");
    }
    
    static String getInputWhileNotRegex(String regex) {
        String userInput = scanner.nextLine().trim();
        while(!userInput.matches(regex)) {
            userInput = scanner.nextLine().trim();
        }
        return userInput;
    }
}
