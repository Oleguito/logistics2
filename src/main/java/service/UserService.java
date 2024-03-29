package service;

import entities.User;
import entities.UserFields;
import enums.Role;
import mappers.UserMapper;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import settings.Settings;
import zapplication.COP;
import utils.FileUtils;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserService implements service.interfaces.UserService {
    
    User authorizedUser = null;
    
    public void setAuthorizedUser(User user) {
        this.authorizedUser = user;
    }
    
    public User getAuthorizedUser() {
        return this.authorizedUser;
    }
    
    public String getPassword() {
        System.out.println("Введите пароль");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        while(!password.matches(".+")) {
            System.out.println("Пароль неверный");
            password = scanner.nextLine();
        }
        return password;
    }
    
    // private List<User> getUsersFromDB () {
    //     return DataBase.getUsers();
    // }
    
    public void deleteUser() {
        System.out.println("""
                Меню удаления вашей учетной записи.
                Имейте в виду что эта операция необратима
                """);
        if(!userYes()) return;
        User currentUser = authorizedUser;
        var allUsers = COP.db.getUsers();
        allUsers.remove(currentUser);
        System.out.println("Ваша учетная запись удалена");
        saveUserData(allUsers);
        System.out.println("Изменения сохранены");
    }
    
    @Override
    public void editUser() {
        System.out.println("Меню редактирования учетной записи");
        if(!userYes()) return;
        var allUsers = COP.db.getUsers();
        allUsers.remove(authorizedUser);
        User createdUser = createUser();
        System.out.println("Поздравляем! Ваша учетная запись успешно изменена!");
        allUsers.add(createdUser);
        saveUserData(allUsers);
        System.out.println("Изменения сохранены");
    }
    
    public void addUser() {
        User createdUser = createUser();
        var allUsers = COP.db.getUsers();
        if(allUsers.stream().anyMatch(u -> u.getFields().getLogin()
                .equals(createdUser.getFields().getLogin()))) {
            System.out.println("Такой бандит уже существует в системе");
            return;
        }
        System.out.println("Поздравляем! Ваша учетная запись успешно создана!");
        allUsers.add(createdUser);
        saveUserData(allUsers);
        System.out.println("Изменения сохранены");
    }
    
    private static void saveUserData(List <User> allUsers) {
        String filename = "users.db";
        Encrypt.decryptFile(filename);
        FileUtils.writeTextFile(filename,
                UserMapper.convertListUsersToString(allUsers));
        Encrypt.encryptFile(filename);
    }
    
    public User createUser(){
        String FIO = getFIO();
        String login = getLogin();
        String password = getPassword();
        String passwordHash = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, Settings.userSecret)
                .hmacHex(password);
        
        var thisrole = COP.us.getAuthorizedUser().getFields().getRole();
        if(thisrole.equals(Role.GUEST)) thisrole = Role.USER;
        
        return new User(UserFields.builder()
                .uuid(UUID.randomUUID())
                .fullName(FIO)
                .login(login)
                .passwordHash(passwordHash)
                .role(COP.us.getAuthorizedUser().getFields().getRole())
                .build());
    }
    
    public String getLogin() {
        System.out.println("Введите ваш логин");
        return getInputWhileNotRegex("[A-Za-z]+", "Только латинские буквы, пожалуйста!");
    }
    
    private String getFIO() {
        System.out.println();
        String firstName = getFirstName();
        String lastName = getLastName();
        String patronymicName = getPatronymicName();
        return "".join(" ", lastName, firstName, patronymicName);
    }
    
    String getFirstName() {
        System.out.println("Введите ваше имя ");
        System.out.println("Не меньше 2 символов, не считая пробелы");
        return getInputWhileNotRegex("[A-Za-zА-Яа-я]{2,}", "");
    }
    
    String getLastName() {
        System.out.println("Введите вашу фамилию ");
        System.out.println("Не меньше 2 символов, не считая пробелы");
        return getInputWhileNotRegex("[A-Za-zА-Яа-я]{2,}", "");
    }
    
    String getPatronymicName() {
        System.out.println("Введите ваше отчество ");
        System.out.println("Не меньше 2 символов, не считая пробелы");
        return getInputWhileNotRegex("[A-Za-zА-Яа-я]{2,}",
                "Только латинские символы");
    }
    
    String getInputWhileNotRegex(String regex, String errorMessage) {
        String userInput = COP.scanner.nextLine().trim();
        while(!userInput.matches(regex)) {
            if (!errorMessage.isEmpty()) { System.out.println(errorMessage); }
            userInput = COP.scanner.nextLine().trim();
        }
        return userInput;
    }
    
    public void deleteUserByLogin(String loginOfAccToDelete) {
        
        var foundUser = COP.db.getUsers().parallelStream()
                .filter(u -> u.getFields().getLogin().equals(loginOfAccToDelete))
                .findFirst();
        if(foundUser.isEmpty()) {
            System.out.println("Такого бандита нет в системе! Выход из операции");
            return;
        }
        COP.db.getUsers().remove(foundUser.get());
        System.out.println("Учетная запись удалена");
        saveUserData(COP.db.getUsers());
        System.out.println("Изменения сохранены");
    }
    
    public void deleteOtherUser () {
        System.out.println("""
                Меню удаления учетной записи другого пользователя.
                Имейте в виду, что эта операция необратима
                и вам придется иметь дело с последствиями
                Введите в следующей строке логин пользователя,
                чью учетную запись вы желаете удалить
                или пустую строку для отмены
                """);
        String login = COP.scanner.nextLine();
        if(login.isBlank() || login.isEmpty()) {
            System.out.println("""
                    Операция отменена. Выход из подпрограммы
                    """);
            return;
        }
        deleteUserByLogin(login);
    }
    
    public boolean userYes() {
        boolean res = false;
        System.out.println("Введите \"y\" для продолжения, что угодно для отмены");
        var decision = COP.scanner.nextLine();
        if(!decision.isEmpty() && !decision.isBlank() && decision.trim().toLowerCase().charAt(0) == 'y') {
            return true;
        } else {
            System.out.println("Отмена операции");
        }
        return res;
    }
}
