package service.menu;

import zapplication.COP;
import enums.MenuTitle;
import utils.FileUtils;

public class UserMenuItemsSet extends MenuItemsSet {
    
    public MenuItem editAccountMenu;
    public MenuItem deleteAccountMenu;
    public MenuItem historyMenu;
    public MenuItem cargosMenu;
    public MenuItem trackingMenu;
    public MenuItem createCargoProfileMenu;
    public MenuItem addEntryMenu;
    public MenuItem editEntryMenu;
    public MenuItem deleteEntryMenu;
    public MenuItem editCargoProfileMenu;
    public MenuItem deleteCargoProfileMenu;
    public MenuItem viewFileStructureMenu;
    public MenuItem markDeliveryStatusMenu;
    public MenuItem selectCargoProfileMenu;
    
    public UserMenuItemsSet() {
        super();
        
        editAccountMenu = new MenuItem(MenuTitle.EDIT_ACCOUNT, () -> {COP.us.editUser();});
        deleteAccountMenu = new MenuItem(MenuTitle.DELETE_ACCOUNT, () -> {COP.us.deleteUser();});
        historyMenu = new MenuItem(MenuTitle.HISTORY, () -> {COP.cs.displayHistory();});
        cargosMenu = new MenuItem(MenuTitle.CARGOS, () -> {});
        trackingMenu = new MenuItem(MenuTitle.TRACKING, () -> {COP.cs.listMyCargoProfiles();});
        createCargoProfileMenu = new MenuItem(MenuTitle.CREATE_CARGO_PROFILE, () -> {COP.cs.addCargoProfile();});
        addEntryMenu = new MenuItem(MenuTitle.ADD_ENTRY, () -> {COP.cs.addCargo();});
        editEntryMenu = new MenuItem(MenuTitle.EDIT_ENTRY, () -> {COP.cs.editCargo();});
        deleteEntryMenu = new MenuItem(MenuTitle.DELETE_ENTRY, () -> {COP.cs.deleteCargo();});
        editCargoProfileMenu = new MenuItem(MenuTitle.EDIT_CARGO_PROFILE, () -> {COP.cs.editCargoProfile();});
        deleteCargoProfileMenu = new MenuItem(MenuTitle.DELETE_CARGO_PROFILE, () -> {COP.cs.deleteCargoProfile();});
        viewFileStructureMenu = new MenuItem(MenuTitle.VIEW_FILE_STRUCTURE, () -> {FileUtils.listDirs();});
        markDeliveryStatusMenu = new MenuItem(MenuTitle.MARK_DELIVERY_STATUS, () -> {COP.cs.setDeliveryStatusMenu();});
        selectCargoProfileMenu = new MenuItem(MenuTitle.SELECT_CARGO_PROFILE, () -> {COP.cs.selectCargoProfile();});
        
        /* Профиль — войденный как USER */
        profileMenu = new MenuItem(MenuTitle.PROFILE, () -> {});
        profileMenu.addSubMenu(viewFileStructureMenu);
        profileMenu.addSubMenu(editAccountMenu);
        profileMenu.addSubMenu(deleteAccountMenu);
        profileMenu.addSubMenu(cargosMenu);
        profileMenu.addSubMenu(historyMenu);
        profileMenu.addSubMenu(backMenu);
        profileMenu.addSubMenu(homeMenu);
        
        
        /* Грузы */
        cargosMenu.addSubMenu(trackingMenu);
        cargosMenu.addSubMenu(markDeliveryStatusMenu);
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
        
        /* Изменить профиль груза */
        editCargoProfileMenu.addSubMenu(addEntryMenu);
        editCargoProfileMenu.addSubMenu(editEntryMenu);
        editCargoProfileMenu.addSubMenu(deleteEntryMenu);
        editCargoProfileMenu.addSubMenu(homeMenu);
        editCargoProfileMenu.addSubMenu(backMenu);
        
        /* Главное меню */
        mainMenu.addSubMenu(profileMenu);
        mainMenu.addSubMenu(exitMenu);
    }
}
