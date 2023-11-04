package entities;

import enums.MenuTitle;
import service.Service;

import java.util.Scanner;
import java.util.Stack;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public static int getChoice(int low, int high) {
        int res = 0;
        System.out.printf("Введите число от %d до %d: ", low, high);
        String entry = scanner.nextLine();
        try {
            res = Integer.parseInt(entry);
        } catch (Exception e) {
            System.out.println("Введите правильное число");
            return getChoice(low, high);
        }
        if (res < low || res > high) {
            System.out.println("Введите правильное число");
            return getChoice(low, high);
        } else {
            return res;
        }
    }
    
    public static void run() {
        
        MenuItem mainMenu = new MenuItem(MenuTitle.MAIN, () -> {});
        
        MenuItem profileMenu = new MenuItem(MenuTitle.PROFILE, () -> {});
        MenuItem signInMenu = new MenuItem(MenuTitle.SIGN_IN, () -> {});
        MenuItem editAccountMenu = new MenuItem(MenuTitle.EDIT_ACCOUNT, () -> {});
        MenuItem deleteAccountMenu = new MenuItem(MenuTitle.DELETE_ACCOUNT, () -> {});
        MenuItem historyMenu = new MenuItem(MenuTitle.HISTORY, () -> {});
        MenuItem cargosMenu = new MenuItem(MenuTitle.CARGOS, () -> {});
        MenuItem trackingMenu = new MenuItem(MenuTitle.TRACKING, () -> {});
        MenuItem createCargoProfileMenu = new MenuItem(MenuTitle.CREATE_CARGO_PROFILE, () -> {});
        MenuItem addEntryMenu = new MenuItem(MenuTitle.ADD_ENTRY, () -> {});
        MenuItem editEntryMenu = new MenuItem(MenuTitle.EDIT_ENTRY, () -> {});
        MenuItem deleteEntryMenu = new MenuItem(MenuTitle.DELETE_ENTRY, () -> {});
        MenuItem editCargoProfileMenu = new MenuItem(MenuTitle.EDIT_CARGO_PROFILE, () -> {});
        MenuItem deleteCargoProfileMenu = new MenuItem(MenuTitle.DELETE_CARGO_PROFILE, () -> {});
        MenuItem addUserMenu = new MenuItem(MenuTitle.ADD_USER, () -> {Service.addUser();});
        
        MenuItem exitMenu = new MenuItem(MenuTitle.EXIT, () -> {System.exit(0);});
        MenuItem backMenu = new MenuItem(MenuTitle.BACK, () -> {});
        MenuItem homeMenu = new MenuItem(MenuTitle.HOME, () -> {});
        
        /* Главное меню */
        mainMenu.addSubMenu(profileMenu);
        mainMenu.addSubMenu(exitMenu);
        
        /* Профиль */
        profileMenu.addSubMenu(signInMenu);
        profileMenu.addSubMenu(addUserMenu);
        profileMenu.addSubMenu(backMenu);
        profileMenu.addSubMenu(homeMenu);
        
        /* Войти */
        signInMenu.addSubMenu(editAccountMenu);
        signInMenu.addSubMenu(deleteAccountMenu);
        signInMenu.addSubMenu(historyMenu);
        signInMenu.addSubMenu(cargosMenu);
        signInMenu.addSubMenu(backMenu);
        signInMenu.addSubMenu(homeMenu);
        
        /* Грузы */
        cargosMenu.addSubMenu(trackingMenu);
        cargosMenu.addSubMenu(createCargoProfileMenu);
        cargosMenu.addSubMenu(editCargoProfileMenu);
        cargosMenu.addSubMenu(deleteCargoProfileMenu);
        cargosMenu.addSubMenu(homeMenu);
        cargosMenu.addSubMenu(backMenu);
        
        /* Создать профиль груза */
        createCargoProfileMenu.addSubMenu(addEntryMenu);
        createCargoProfileMenu.addSubMenu(editEntryMenu);
        createCargoProfileMenu.addSubMenu(deleteEntryMenu);
        createCargoProfileMenu.addSubMenu(homeMenu);
        createCargoProfileMenu.addSubMenu(backMenu);
        
        MenuItem currentMenu = mainMenu;
        Stack <MenuItem> enteredMenus = new Stack <>();
        
        while(true) {
            int num = currentMenu.getNumSubmenus();
            if (num > 0) {
                currentMenu.listSubmenus();
                int res = getChoice(1, num);
                MenuItem selectedMenu = currentMenu.getSubmenus().get(res - 1);
                if (selectedMenu.getTitle() == MenuTitle.BACK) {
                    currentMenu = enteredMenus.pop();
                    continue;
                } else if (selectedMenu.getTitle() == MenuTitle.HOME) {
                    currentMenu = mainMenu;
                    continue;
                } else {
                    enteredMenus.push(currentMenu);
                    currentMenu = selectedMenu;
                    currentMenu.select();
                }
            } else {
                currentMenu = enteredMenus.pop();
            }
        }
    }
}
