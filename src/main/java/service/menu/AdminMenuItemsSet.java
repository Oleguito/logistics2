package service.menu;

import application.COP;
import enums.MenuTitle;

public class AdminMenuItemsSet extends MenuItemsSet {

    public MenuItem editAccountMenu          ;
    public MenuItem deleteAccountMenu        ;
    public MenuItem historyMenu              ;
    public MenuItem cargosMenu               ;
    public MenuItem trackingMenu             ;
    public MenuItem createCargoProfileMenu   ;
    public MenuItem addEntryMenu             ;
    public MenuItem editEntryMenu            ;
    public MenuItem deleteEntryMenu          ;
    public MenuItem editCargoProfileMenu     ;
    public MenuItem deleteCargoProfileMenu   ;
    public MenuItem deleteUserMenu           ;
    
    public AdminMenuItemsSet() {
        super();
        
        editAccountMenu = new MenuItem(MenuTitle.EDIT_ACCOUNT, () -> {COP.us.editUser();});
        deleteAccountMenu = new MenuItem(MenuTitle.DELETE_ACCOUNT, () -> {COP.us.deleteUser();});
        historyMenu = new MenuItem(MenuTitle.HISTORY, () -> {});
        cargosMenu = new MenuItem(MenuTitle.CARGOS, () -> {});
        trackingMenu = new MenuItem(MenuTitle.TRACKING, () -> {});
        createCargoProfileMenu = new MenuItem(MenuTitle.CREATE_CARGO_PROFILE, () -> {});
        addEntryMenu = new MenuItem(MenuTitle.ADD_ENTRY, () -> {});
        editEntryMenu = new MenuItem(MenuTitle.EDIT_ENTRY, () -> {});
        deleteEntryMenu = new MenuItem(MenuTitle.DELETE_ENTRY, () -> {});
        editCargoProfileMenu = new MenuItem(MenuTitle.EDIT_CARGO_PROFILE, () -> {});
        deleteCargoProfileMenu = new MenuItem(MenuTitle.DELETE_CARGO_PROFILE, () -> {});
        
        deleteUserMenu = new MenuItem(MenuTitle.DELETE_USER, () -> {
            System.out.println("Админ удаляет пользователя...");
        });
        
        /* Профиль — войденный */
        profileMenu = new MenuItem(MenuTitle.PROFILE, () -> {});
        profileMenu.addSubMenu(editAccountMenu);
        profileMenu.addSubMenu(deleteAccountMenu);
        profileMenu.addSubMenu(deleteUserMenu);
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
