package service;

import database.DataBase;
import entities.User;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import settings.Settings;

public class Auth {
    public static void authorize(User user) {
        String login = UserService.getLogin();
        String password = UserService.getPassword();
        String passwordHash = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, Settings.userSecret)
                .hmacHex(password);
        var foundUser = DataBase.getUsers().parallelStream()
                .filter(u -> {
                    return u.getFields().getLogin().equals(login) &&
                            u.getFields().getPasswordHash().equals(passwordHash);
                })
                .findFirst();
        if(foundUser.isEmpty()) {
            System.out.println("Увы, не получилось");
        }
        
    }
}
