package service.menu;

import application.COP;
import enums.MenuTitle;

public class AdminMenuItemsSet extends MenuItemsSet {
    public static MenuItem editAccountMenu = new MenuItem(MenuTitle.EDIT_ACCOUNT, () -> {
        COP.us.editUser();});
    public static MenuItem deleteAccountMenu = new MenuItem(MenuTitle.DELETE_ACCOUNT, () -> {
        COP.us.deleteUser();});
    public static MenuItem historyMenu = new MenuItem(MenuTitle.HISTORY, () -> {});
    public static MenuItem cargosMenu = new MenuItem(MenuTitle.CARGOS, () -> {});
    public static MenuItem trackingMenu = new MenuItem(MenuTitle.TRACKING, () -> {});
    public static MenuItem createCargoProfileMenu = new MenuItem(MenuTitle.CREATE_CARGO_PROFILE, () -> {});
    public static MenuItem addEntryMenu = new MenuItem(MenuTitle.ADD_ENTRY, () -> {});
    public static MenuItem editEntryMenu = new MenuItem(MenuTitle.EDIT_ENTRY, () -> {});
    public static MenuItem deleteEntryMenu = new MenuItem(MenuTitle.DELETE_ENTRY, () -> {});
    public static MenuItem editCargoProfileMenu = new MenuItem(MenuTitle.EDIT_CARGO_PROFILE, () -> {});
    public static MenuItem deleteCargoProfileMenu = new MenuItem(MenuTitle.DELETE_CARGO_PROFILE, () -> {});
    
    public AdminMenuItemsSet() {
        super();
        
        /* Профиль — войденный */
        profileMenu = new MenuItem(MenuTitle.PROFILE, () -> {});
        profileMenu.addSubMenu(editAccountMenu);
        profileMenu.addSubMenu(deleteAccountMenu);
        profileMenu.addSubMenu(cargosMenu);
        profileMenu.addSubMenu(historyMenu);
        profileMenu.addSubMenu(backMenu);
        profileMenu.addSubMenu(homeMenu);
        
        
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
        
        
        /* Главное меню */
        mainMenu.addSubMenu(profileMenu);
        mainMenu.addSubMenu(exitMenu);
    }
}
