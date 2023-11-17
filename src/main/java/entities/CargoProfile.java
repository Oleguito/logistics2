package entities;

import application.COP;
import enums.DeliveryStatus;
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
    DeliveryStatus deliveryStatus;
    
    private List <Cargo> cargoList;
    
    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public String toDisplayString() {
        
        StringBuilder sb = new StringBuilder();
        for(var i : cargoList) {
            sb.append("\t" + i.toDisplayString());
        }
        return "Профиль груза ID: " + uuid.toString() + "(" + deliveryStatus.getValue() + ")" + "\n"
                + "Название: " + "\"" + title + "\"" + "\n"
                // + "Автор ID: " + StringUtils.fitToWidth(authorUUID.toString(), 50) + "\n"
                + "Логин автора: " + COP.db.getUserBy(authorUUID).orElseThrow().getFields().getLogin() + "\n"
                + "Отправитель: " + sender  + "\n"
                + "Получатель: " + receiver  + "\n"
                + "СТАТУС: " + deliveryStatus.getValue() + "\n"
                + "\tГрузы: " + "\n"
                + (sb.isEmpty() ? "\t(Нет)" : sb.toString());
    }
    
    public String toShortDisplayString() {
        
        return "Профиль груза ID: "
                + uuid.toString() + " "
                + title
                + "(" + deliveryStatus.getValue() + ")" + "\n";
    }
    
    public void addCargo(Cargo cargo) {
        cargoList.add(cargo);
    }
    
    public void removeCargo(Cargo cargo) {
        cargoList.remove(cargo);
    }
}
