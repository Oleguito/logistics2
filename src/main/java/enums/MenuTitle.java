package enums;

public enum MenuTitle {
    
    MAIN("Главное меню"),
        PROFILE("Профиль"),
            SIGN_IN("Войти"),
                EDIT_ACCOUNT("Изменить учетную запись"),
                DELETE_ACCOUNT("Удалить учетную запись"),
                HISTORY("История доставок"),
                CARGOS("Грузы"),
                    TRACKING("Отслеживание грузов"),
                    CREATE_CARGO_PROFILE("Создать профиль груза"),
                        ADD_ENTRY("Добавить запись"),
                        EDIT_ENTRY("Изменить запись"),
                        DELETE_ENTRY("Удалить запись"),
                    EDIT_CARGO_PROFILE("Изменить профиль груза"),
                    DELETE_CARGO_PROFILE("Удалить профиль груза"),
            ADD_USER("Добавить пользователя"),
    
    
    BACK("Назад"),
    HOME("Домашняя страница"),
    EXIT("Выход");
    
    public String getValue() {
        return value;
    }
    
    String value;
    
    MenuTitle(String value) {
        this.value = value;
    }
}
