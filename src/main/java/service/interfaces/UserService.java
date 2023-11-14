package service.interfaces;

import entities.User;

public interface UserService {
    public void addUser();
    
    public void deleteUser();
    
    public void editUser();
    
    public void setAuthorizedUser(User user);
    
    public User getAuthorizedUser();
    
    public String getLogin();
    
    public String getPassword();
}
