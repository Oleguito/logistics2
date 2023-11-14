package service.menu;

import application.COP;
import enums.MenuTitle;
import interfaces.MenuAction;

import java.util.ArrayList;
import java.util.List;
import enums.Role;
import settings.Settings;

public class MenuItem {
    private MenuTitle title;
    private MenuAction action;
    private List <MenuItem> submenus;
    private List <Role> acceptedRoles;
    
    public List<MenuItem> getSubmenus() {
        return submenus;
    }
    
    public int getNumSubmenus() {
        return submenus.size();
    }
    
    public MenuTitle getTitle() {
        return title;
    }
    
    MenuItem(MenuTitle title, MenuAction action) {
        this.title = title;
        this.submenus = new ArrayList <>();
        this.action = action;
        this.acceptedRoles = Settings.defaultRoles;
    }
    
    public void setAcceptedRoles(List<Role> rolesList) {
        acceptedRoles = rolesList;
    }
    
    public void listSubmenus () {
        System.out.println();
        for (int i = 0; i < submenus.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, submenus.get(i).getTitle().getValue());
        }
    }
    
    public void listSubmenusRole () {
        System.out.println();
        for (int i = 0; i < submenus.size(); i++) {
            if(!validateMenuItemForUserRole(i)) {
                submenus.remove(submenus.get(i));
            }
        }
        for (int i = 0; i < submenus.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, submenus.get(i).getTitle().getValue());
        }
    }
    
    private boolean validateMenuItemForUserRole(int menuItemIndex) {
        boolean res = false;
        var childMenuItem = submenus.get(menuItemIndex);
        for(var i : childMenuItem.acceptedRoles) {
            if(COP.us.getAuthorizedUser().getFields().getRole().equals(i)) return true;
        }
        return res;
    }
    
    public void addSubMenu(MenuTitle title, MenuAction action) {
        submenus.add(new MenuItem(title, action));
    }
    
    public void addSubMenu(MenuItem menuItem) {
        submenus.add(menuItem);
    }
    
    public void select() {
        action.perform();
    }
}
