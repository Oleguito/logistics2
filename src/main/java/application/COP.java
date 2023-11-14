package application;

import database.DataBase;
import service.CargoService;
import service.menu.MenuService;
import service.UserService;

import java.util.Scanner;


/*  Расшифровка - CommonObjectsPack
    Этот статический класс служит для инкапсуляции всех необходимых программе объектов
    (статический = содержащий только статические члены)
    Это своеобразный ООП-аналог глобальной переменной
    По сути, через него осуществляется взаимодействие между слоями
    Например,
    Слой X обращается к базе данных не непосредственно, а через ссылку COP.db
    
    Пока поддерживаются только основные 3 класса, и у них прямая зависимость
    от User и прочих классов
    
    ВНИМАНИЕ! Я не использую геттеры исключительно из эстетических соображений,
    чтобы не писать скобки постоянно */

// @AllArgsConstructor
public class COP {
    public static database.interfaces.DataBase db = new DataBase();
    public static service.interfaces.UserService us = new UserService();
    public static service.interfaces.CargoService cs = new CargoService();
    public static MenuService ms = new MenuService();
    public static Scanner scanner = new Scanner(System.in);
}
