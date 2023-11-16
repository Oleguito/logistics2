package service.menu;

import application.COP;
import enums.MenuTitle;
import enums.Role;
import service.Auth;

import java.util.List;
import java.util.Stack;

public class Menu {
    
    static MenuItemsSet menuSet = new GuestMenuItemsSet();
    
    // /*  Этот хэшмап используется для хранения фактических адресов меню
    //     потому что если я устанавливаю какой-то пункт меню А в меню Б,
    //     а потом меняю меню Б на другое меню Ц, этот пункт остается меню Б! */
    // public static HashMap<MenuItem, MenuItem> menuAddresses = new HashMap<>();
    
    
    /* Тут создаются сами менюшки */

    // public static MenuItem mainMenu = new MenuItem(MenuTitle.MAIN, () -> {});
    //
    // public static MenuItem profileMenu = new MenuItem(MenuTitle.PROFILE, () -> {});
    // public static MenuItem nonEnteredProfileMenu = new MenuItem(MenuTitle.NON_ENTERED_PROFILE, () -> {});
    // public static MenuItem enteredProfileMenu = new MenuItem(MenuTitle.ENTERED_PROFILE, () -> {});
    //
    // public static MenuItem signInMenu = new MenuItem(MenuTitle.SIGN_IN, () -> {
    //     Auth.authorize();});
    // public static MenuItem editAccountMenu = new MenuItem(MenuTitle.EDIT_ACCOUNT, () -> {
    //     COP.us.editUser();});
    // public static MenuItem deleteAccountMenu = new MenuItem(MenuTitle.DELETE_ACCOUNT, () -> {
    //     COP.us.deleteUser();});
    // public static MenuItem historyMenu = new MenuItem(MenuTitle.HISTORY, () -> {});
    // public static MenuItem cargosMenu = new MenuItem(MenuTitle.CARGOS, () -> {});
    // public static MenuItem trackingMenu = new MenuItem(MenuTitle.TRACKING, () -> {});
    // public static MenuItem createCargoProfileMenu = new MenuItem(MenuTitle.CREATE_CARGO_PROFILE, () -> {});
    // public static MenuItem addEntryMenu = new MenuItem(MenuTitle.ADD_ENTRY, () -> {});
    // public static MenuItem editEntryMenu = new MenuItem(MenuTitle.EDIT_ENTRY, () -> {});
    // public static MenuItem deleteEntryMenu = new MenuItem(MenuTitle.DELETE_ENTRY, () -> {});
    // public static MenuItem editCargoProfileMenu = new MenuItem(MenuTitle.EDIT_CARGO_PROFILE, () -> {});
    // public static MenuItem deleteCargoProfileMenu = new MenuItem(MenuTitle.DELETE_CARGO_PROFILE, () -> {});
    // public static MenuItem addUserMenu = new MenuItem(MenuTitle.ADD_USER, () -> {
    //     COP.us.addUser();});
    // public static MenuItem exitMenu = new MenuItem(MenuTitle.EXIT, () -> {
    //     System.exit(0);});
    // public static MenuItem backMenu = new MenuItem(MenuTitle.BACK, () -> {});
    // public static MenuItem homeMenu = new MenuItem(MenuTitle.HOME, () -> {});
    /* Админское меню */
    // public static MenuItem deleteUserMenu = new MenuItem(MenuTitle.DELETE_USER, () -> {
    //     System.out.println("Админ удаляет пользователя...");
    // });
    
    
    /* С этим работает цикл */
    static MenuItem mainMenu = menuSet.mainMenu;
    static MenuItem currentMenu = menuSet.mainMenu;
    static Stack <MenuItem> enteredMenus = new Stack <>();
    
    
    // public static void replaceMenu (MenuItem oldItem, MenuItem newItem) {
    //     menuAddresses.put(oldItem, Menu.menuAddresses.get(newItem));
    // }
    
    // public static void fillMenuAddressesHashMap() {
    //     var menuItems = Menu.class.getDeclaredFields();
    //     for(var menuItem : menuItems) {
    //         if(menuItem.getType() == MenuItem.class) {
    //             try {
    //                 menuItem.setAccessible(true);
    //                 var object = (MenuItem) menuItem.get(null);
    //                 if(!menuAddresses.containsKey(object)) {
    //                     menuAddresses.put(object, object);
    //                 }
    //             } catch (IllegalAccessException e) {
    //                 throw new RuntimeException(e);
    //             }
    //         }
    //     }
    // }
    
    // public static void setProfileMenuEntered() {
        
        // Menu.profileMenu = Menu.menuAddresses.get(Menu.enteredProfileMenu);
        
        // profileMenu = enteredProfileMenu;
        // for (int i = 0; i < enteredMenus.size(); i++) {
        //     if(enteredMenus.get(i).getTitle() == MenuTitle.PROFILE) {
        //         enteredMenus.remove(i);
        //         if(i <= (enteredMenus.size() - 1)) {
        //             enteredMenus.set(i, profileMenu);
        //         } else {
        //             enteredMenus.push(profileMenu);
        //         }
        //     }
        // }
    // }
    
    public static void setMenus() {

        /* Р О Л И */

        /* Админское меню */
        // deleteUserMenu.setAcceptedRoles(List.of(Role.ADMIN));


        /* М Е Н Ю */

        // /* Главное меню */
        // mainMenu.addSubMenu(profileMenu);
        // mainMenu.addSubMenu(exitMenu);
        //
        // /* Профиль — невойденный */
        // nonEnteredProfileMenu.addSubMenu(signInMenu);
        // nonEnteredProfileMenu.addSubMenu(addUserMenu);
        // nonEnteredProfileMenu.addSubMenu(deleteUserMenu);
        // nonEnteredProfileMenu.addSubMenu(backMenu);
        // nonEnteredProfileMenu.addSubMenu(homeMenu);
        //
        // /* Профиль — войденный */
        // enteredProfileMenu.addSubMenu(cargosMenu);
        // enteredProfileMenu.addSubMenu(deleteUserMenu);
        // enteredProfileMenu.addSubMenu(backMenu);
        // enteredProfileMenu.addSubMenu(homeMenu);
        //
        // // /* Профиль — просто. Принимает либо войденный либо невойденный */
        // // profileMenu = menuAddresses.get(MenuTitle.NON_ENTERED_PROFILE);
        //
        //
        // /* Войти */
        // signInMenu.addSubMenu(editAccountMenu);
        // signInMenu.addSubMenu(deleteAccountMenu);
        // signInMenu.addSubMenu(historyMenu);
        // signInMenu.addSubMenu(cargosMenu);
        // signInMenu.addSubMenu(backMenu);
        // signInMenu.addSubMenu(homeMenu);
        //
        // /* Грузы */
        // cargosMenu.addSubMenu(trackingMenu);
        // cargosMenu.addSubMenu(createCargoProfileMenu);
        // cargosMenu.addSubMenu(editCargoProfileMenu);
        // cargosMenu.addSubMenu(deleteCargoProfileMenu);
        // cargosMenu.addSubMenu(homeMenu);
        // cargosMenu.addSubMenu(backMenu);
        //
        // /* Создать профиль груза */
        // createCargoProfileMenu.addSubMenu(addEntryMenu);
        // createCargoProfileMenu.addSubMenu(editEntryMenu);
        // createCargoProfileMenu.addSubMenu(deleteEntryMenu);
        // createCargoProfileMenu.addSubMenu(homeMenu);
        // createCargoProfileMenu.addSubMenu(backMenu);

    }
    
    public static int getChoice(int low, int high) {
        int res = 0;
        System.out.printf("Введите число от %d до %d: ", low, high);
        String entry = COP.scanner.nextLine();
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
        
        
        
        while(true) {
            int num = currentMenu.getNumSubmenus();
            if (num > 0) {
                for (int i = 0; i < 80; i++) { System.out.print("-"); }
                System.out.println("\n\n\nПункт меню \"" + currentMenu.getTitle().getValue() + "\"");
                currentMenu.listSubmenus();
                int res = getChoice(1, num);
                System.out.println("\n\n");
                for (int i = 0; i < 80; i++) { System.out.print("-"); }
                System.out.println();
                MenuItem selectedMenu = currentMenu.getSubmenus().get(res - 1);
                if (selectedMenu.getTitle() == MenuTitle.BACK) {
                    currentMenu = enteredMenus.pop();
                    continue;
                } else if (selectedMenu.getTitle() == MenuTitle.HOME) {
                    currentMenu = mainMenu;
                    continue;
                } else if (selectedMenu.getTitle() == MenuTitle.DELETE_ACCOUNT) {
                    selectedMenu.select();
                    currentMenu = mainMenu;
                    continue;
                } else if (selectedMenu.getTitle() == MenuTitle.SIGN_IN) {
                    selectedMenu.select();
                    
                    switch (COP.us.getAuthorizedUser().getFields().getRole()) {
                        case USER -> {
                            var a = new UserMenuItemsSet();
                            menuSet = a;
                        }
                        case ADMIN -> {
                            menuSet = new AdminMenuItemsSet();
                        }
                    }
                    
                    mainMenu = menuSet.mainMenu;
                    currentMenu = menuSet.mainMenu.getSubmenus().get(0);
                    while(!enteredMenus.empty()) enteredMenus.pop();
                    enteredMenus.push(menuSet.mainMenu);
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
