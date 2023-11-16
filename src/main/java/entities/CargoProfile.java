package entities;

import application.COP;
import lombok.Builder;
import lombok.Getter;
import utils.StringUtils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class CargoProfile implements Serializable {
    String title;
    UUID uuid;
    UUID authorUUID;
    String sender;
    String receiver;
    
    List <Cargo> cargoList;
    
    public String toDisplayString() {
        
        StringBuilder sb = new StringBuilder();
        for(var i : cargoList) {
            sb.append("\t" + i.toDisplayString());
        }
        return "Профиль груза ID: " + StringUtils.fitToWidth(uuid.toString(), 50)
                + title + "\n"
                // + "Автор ID: " + StringUtils.fitToWidth(authorUUID.toString(), 50) + "\n"
                + "Автор login: " + COP.db.getUserBy(authorUUID).orElseThrow().getFields().getLogin() + "\n"
                + "Отправитель: " + StringUtils.fitToWidth(sender, 30) + "\n"
                + "Получатель: " + StringUtils.fitToWidth(receiver, 30) + "\n"
                + "\tГрузы: " + "\n"
                + (sb.isEmpty() ? "\t(Нет)" : sb.toString());
    }
    
    public String toShortDisplayString() {
        
        return "Профиль груза ID: " + uuid.toString() + " " + title + "\n";
    }
    
    public void addCargo(Cargo cargo) {
        cargoList.add(cargo);
    }
    
    public void removeCargo(Cargo cargo) {
        cargoList.remove(cargo);
    }
}
