package service.menu;

import application.COP;
import enums.MenuTitle;
import service.Auth;

public class MenuItemsSet {
    
    public MenuItem mainMenu;
    public MenuItem profileMenu;
    public MenuItem signInMenu;
    public MenuItem addUserMenu;
    public MenuItem exitMenu;
    public MenuItem backMenu;
    public MenuItem homeMenu;
    
    public MenuItemsSet() {
        this.mainMenu  =    new MenuItem(MenuTitle.MAIN, () -> {});
        this.profileMenu  = new MenuItem(MenuTitle.PROFILE, () -> {});
        this.signInMenu =  new MenuItem(MenuTitle.SIGN_IN, () -> {Auth.authorize();});
        this.addUserMenu = new MenuItem(MenuTitle.ADD_USER, () -> {COP.us.addUser();});
        this.exitMenu  =    new MenuItem(MenuTitle.EXIT, () -> {System.exit(0);});
        this.backMenu  =    new MenuItem(MenuTitle.BACK, () -> {});
        this.homeMenu  =    new MenuItem(MenuTitle.HOME, () -> {});
    }
}
