package entities;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class CargoProfile {
    UUID uuid;
    UUID authorUUID;
    String sender;
    String receiver;
    
    List <Cargo> cargoList;
}
