package service;

import application.COP;
import service.menu.Menu;
import settings.Settings;

public class Auth {
    public static void authorize() {
        // String login = COP.us.getLogin();
        // String password = COP.us.getPassword();
        // String passwordHash = Encrypt.getHMACof(Settings.userSecret, password);
        //
        // var foundUser = COP.db.getUsers().parallelStream()
        //         .filter(u -> {
        //             return u.getFields().getLogin().equals(login) &&
        //                     u.getFields().getPasswordHash().equals(passwordHash);
        //         })
        //         .findFirst();
        // if(foundUser.isEmpty()) {
        //     System.out.println("Увы, не получилось! Бывает :(");
        //     return;
        // }
        // /* Гостя тут не будет потому что файл users.db будет зашифрован
        //    поэтому просто устанавливаем текущего пользователя */
        // COP.us.setAuthorizedUser(foundUser.get());
        
        
        COP.us.setAuthorizedUser(COP.db.getUsers().get(0));
        System.out.println("Поздравляем! Вы успешно авторизовались!");
        System.out.println("Ваше имя-отчество: " + COP.us.getAuthorizedUser().getFields().getFullName());
        System.out.println("Ваш логин: " + COP.us.getAuthorizedUser().getFields().getLogin());
    }
}
