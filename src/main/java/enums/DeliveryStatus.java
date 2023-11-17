package enums;

public enum DeliveryStatus {
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
