package service.menu;

import zapplication.COP;
import enums.MenuTitle;

public class AdminMenuItemsSet extends UserMenuItemsSet {

    
    public MenuItem deleteUserMenu;
    
    public AdminMenuItemsSet() {
        super();
        
        deleteUserMenu = new MenuItem(MenuTitle.DELETE_USER, () -> {
            System.out.println("Админ удаляет пользователя...");
            COP.us.deleteOtherUser();
        });
        
        /* Профиль — войденный как Admin */
        profileMenu = new MenuItem(MenuTitle.PROFILE, () -> {});
        profileMenu.addSubMenu(viewFileStructureMenu);
        profileMenu.addSubMenu(editAccountMenu);
        profileMenu.addSubMenu(deleteAccountMenu);
        profileMenu.addSubMenu(cargosMenu);
        profileMenu.addSubMenu(historyMenu);
        profileMenu.addSubMenu(backMenu);
        profileMenu.addSubMenu(homeMenu);
        
        /* Приходится переопределять главное меню потому что меню работает от корня */
        mainMenu = new MenuItem(MenuTitle.MAIN, () -> {});
        mainMenu.addSubMenu(profileMenu);
        mainMenu.addSubMenu(super.addUserMenu);
        mainMenu.addSubMenu(deleteUserMenu);
        mainMenu.addSubMenu(super.exitMenu);
    }
}
