package service.interfaces;

import entities.Cargo;

public interface CargoService {
    
    public Cargo createCargo();

    public void addCargo();
    
    public void addCargoProfile();
    
    public void editCargo();
    
    public void editCargoProfile();
    
    public void deleteCargo();
    
    public void deleteCargoProfile();
    
    public void findCargo();
    
    public void findCargoProfile();
    
    public void listMyCargoProfiles();
    
    public void setDeliveryStatusMenu();
    
    public void displayHistory();
    
    public void selectCargoProfile();
}
