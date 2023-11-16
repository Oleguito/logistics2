package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import utils.StringUtils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.UUID;

@Builder
@Getter
public class Cargo implements Serializable {
    @NonNull
    UUID uuid;
    String cargoTitle;
    String cargoDescription;
    
    public String toDisplayString() {
        return String.format("ID: %s\n\tНазвание: \"%s\"\n\tОписание: \"%s\"\n",
                uuid.toString(),
                cargoTitle,
                cargoDescription);
    }
    
/*      String 	Название груза
        String 	Описание груза
        UUID	ID создавшего пользователя
        String	отправитель
        String 	получатель */
    
    
}
