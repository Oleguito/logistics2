package service.menu;

/* Кажется я его не использую, если не понадобится
   и не забуду — удалю */

public class GuestMenuItemsSet extends MenuItemsSet {
    public GuestMenuItemsSet () {
        
        /* Главное меню */
        mainMenu.addSubMenu(profileMenu);
        mainMenu.addSubMenu(exitMenu);
        
        /* Профиль */
        profileMenu.addSubMenu(signInMenu);
        profileMenu.addSubMenu(addUserMenu);
        profileMenu.addSubMenu(backMenu);
        profileMenu.addSubMenu(homeMenu);
    }
}
