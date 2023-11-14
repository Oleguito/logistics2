package settings;

import enums.Role;

import java.util.List;

public class Settings {
    /* И так понятно */
    public static String filesDir = "src/main/resources/";
    /* Секрет для генерации HMAC пользовательских паролей */
    public static String userSecret = "Victoria's Secret";
    /* Эта ссылка будет у всех пунктов меню */
    public static List <Role> defaultRoles = List.of(Role.GUEST, Role.USER, Role.ADMIN);
}
