package service;


import entities.User;

import java.util.Scanner;

public class MainService {
    
    static Scanner scanner = new Scanner(System.in);
    
    
    
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
    
    public static void addUser(){
        String FIO = getFIO();
        String login = getLogin();
        String password = getPassword();
        // return new User(FIO, login, password, 0);
        // return;
    }
    
    private static String getLogin() {
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
