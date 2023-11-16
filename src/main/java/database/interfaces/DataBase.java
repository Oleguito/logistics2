package database.interfaces;

import entities.CargoProfile;
import entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DataBase {
    public List <User> getUsers();
    
    public List <CargoProfile> getCargoProfiles();
    
    public Optional <User> getUserBy(UUID uuid);
    
    public Optional<CargoProfile> getCargoProfileByUUID(UUID uuid);
}
