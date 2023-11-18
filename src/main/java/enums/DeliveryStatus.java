package enums;

public enum DeliveryStatus {
    IN_ASSEMBLY("В сборке"),
    DELIVERED("Доставлено"),
    DELIVERY_PENDING("Доставляется");
    
    DeliveryStatus(String value) {
        this.value = value;
    }
    
    String value;
    
    public String getValue() {
        return value;
    }
}
