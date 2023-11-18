package service;

import zapplication.COP;
import entities.Cargo;
import entities.CargoProfile;
import enums.DeliveryStatus;
import utils.FileUtils;
import utils.IntUtils;

import java.util.ArrayList;
import java.util.UUID;

public class CargoService implements service.interfaces.CargoService {
    
    CargoProfile selectedCargoProfile;
    
    void setSelectedCargoProfile(CargoProfile cargoProfileToSelect) {
        selectedCargoProfile = cargoProfileToSelect;
    }
    
    CargoProfile getSelectedCargoProfile() {
        return selectedCargoProfile;
    }
    
    public void selectCargoProfile() {
        var myCargoProfiles = COP.db.getCargoProfiles()
                .parallelStream()
                .filter(cp -> {
                    return cp.getAuthorUUID().equals(COP.us.getAuthorizedUser().getFields().getUuid());
                })
                .toList();
        
        if(myCargoProfiles.isEmpty()) {
            System.out.println("""
                    У вас еще нет профилей грузов. Пожалуйста, создайте профиль груза
                    в меню "Профиль"
                    """);
            return;
        }
        
        System.out.println("""
                Ваши профили грузов, зарегистрированные в системе:
                """);
        int len = myCargoProfiles.size();
        for (int i = 0; i < len; i++) {
            System.out.printf("%d: %s\n", i + 1, myCargoProfiles.get(i).toShortDisplayString());
        }
        
        System.out.println("""
                Введите индекс профиля грузов, который требуется выбрать
                """);
        
        int res = IntUtils.getChoice(1, len);
        System.out.println("Вы ввели " + res);
        
        var thisCargoProfile = myCargoProfiles.get(res - 1);
        
        setSelectedCargoProfile(thisCargoProfile);
        
        System.out.println("""
                Профиль груза успешно выделен
                """);
    }
    
    // @Override
    public Cargo createCargo() {
        System.out.print("Введите название вашего груза: ");
        String regex = "[\\p{L}\\p{M}\\p{N}\\p{P}\\p{S}\\s.,:;!@#$%^&*()_+|]+";
        String cargoTitle = getInputWhileNotRegex(regex + "{2,}",
                "Пожалуйста, придумайте что-нибудь более масштабное");
        System.out.print("Введите описание вашего груза: ");
        String cargoDescription = getInputWhileNotRegex(regex + "{10,}",
                "Пожалуйста, придумайте что-нибудь более масштабное");
        UUID uuid = UUID.randomUUID();
        System.out.println("""
                Введите UUID посылки (профиля груза) к которому требуется добавить
                данный груз
                """);
        return Cargo.builder()
                .uuid(uuid)
                .cargoTitle(cargoTitle)
                .cargoDescription(cargoDescription)
                .build();
    }
    
    @Override
    public void addCargo() {
        
        makeSureCargoProfileSelected();
        
        System.out.println("""
                Меню добавления груза к выбранному профилю грузов
                """);
        
        System.out.println("Продолжить?");
        if(!COP.us.userYes()) return;
        
        selectedCargoProfile.addCargo(createCargo());
        System.out.println("Груз успешно добавлен в выбранный профиль грузов");
        saveData();
    }
    
    private void saveData() {
        var filename = "object.dat";
        Encrypt.decryptFile(filename);
        FileUtils.serialize(filename, COP.db.getCargoProfiles());
        Encrypt.encryptFile(filename);
        System.out.println("Изменения сохранены");
    }
    
    CargoProfile createCargoProfile() {
        
        /*  UUID uuid;
            UUID authorUUID;
            String sender;
            String receiver; */
        System.out.println("""
                Введите название вашей посылки. Посылка содержит грузы,
                которые вы можете добавлять, изменять и удалять.
                """);
        String regex = "[\\p{L}\\p{M}\\p{N}\\p{P}\\p{S}\\s.,:;!@#$%^&*()_+|]+";
        String cargoTitle = getInputWhileNotRegex(regex + "{2,}",
                "Пожалуйста, придумайте что-нибудь более масштабное");
        System.out.print("\nВведите отправителя вашей посылки: ");
        String cargoSender = getInputWhileNotRegex(regex + "{2,}",
                "Пожалуйста, придумайте что-нибудь более масштабное");
        System.out.print("\nВведите получателя вашей посылки: ");
        String cargoReceiver = getInputWhileNotRegex(regex + "{2,}",
                "Пожалуйста, придумайте что-нибудь более масштабное");
        return CargoProfile.builder()
                .title(cargoTitle)
                .sender(cargoSender)
                .receiver(cargoReceiver)
                .authorUUID(COP.us.getAuthorizedUser().getFields().getUuid())
                .uuid(UUID.randomUUID())
                .cargoList(new ArrayList <>())
                .deliveryStatus(DeliveryStatus.IN_ASSEMBLY)
                .build();
    }
    
    @Override
    public void addCargoProfile() {
        
        System.out.println("""
                Меню создания нового профиля груза. Вы желаете продолжить?
                """);
        if(!COP.us.userYes()) return;
        
        var created = createCargoProfile();
        COP.db.getCargoProfiles().add(created);
        System.out.println("Профиль грузов добавлен");
        saveData();
        
        System.out.println("Желаете выделить профиль груза для дальнейшей работы?");
        if(!COP.us.userYes()) return;
        
        selectedCargoProfile = created;
        System.out.println("Профиль успешно выделен");
    }
    
    @Override
    public void editCargo() {
        
        System.out.println("""
                Меню редактирования груза в выбранном профиле грузов
                Продолжить?
        """);
        if(!COP.us.userYes()) return;
        
        makeSureCargoProfileSelected();
        
        var len = selectedCargoProfile.getCargoList().size();
        if(len == 0) {
            System.out.println("Пока редактировать нечего");
            return;
        }
        
        System.out.println("Грузы в выбранном профиле грузов");
        for (int i = 0; i < len; i++) {
            System.out.printf("%d - %s\n", i + 1, selectedCargoProfile.getCargoList().get(i).toDisplayString());
        }
        
        System.out.println("Выберите груз из списка");
        int res = IntUtils.getChoice(1, len);
        
        System.out.println("Выбранный груз:");
        System.out.println(selectedCargoProfile.getCargoList().get(res - 1).toDisplayString());
        
        selectedCargoProfile.getCargoList().set(res - 1, createCargo());
        System.out.println("Груз успешно изменен");
        saveData();
        
    }
    
    @Override
    public void editCargoProfile() {
        
        makeSureCargoProfileSelected();
        
        System.out.println("""
                Теперь вы можете воспользоваться пунктами меню создания, добавления,
                изменения и удаления грузов. Все они будут применяться к выбранному профилю груза.
                """);
    }
    
    void makeSureCargoProfileSelected() {
        if(selectedCargoProfile == null) {
            System.out.println("""
                    Профиль груза не выделен. Для работы с профилем груза его
                    необходимо сначала выделить (выбрать)
                    Вы хотите выбрать профиль груза?
                    """);
            if(!COP.us.userYes()) return;
            selectCargoProfile();
        }
        System.out.println("Выделенный профиль груза:");
        System.out.println(selectedCargoProfile.toDisplayString());
    }
    
    @Override
    public void deleteCargo() {
        
        makeSureCargoProfileSelected();
        
        if (selectedCargoProfile.getCargoList().isEmpty()) {
            System.out.println("Пока нечего удалять");
            return;
        }
        
        System.out.println("Продолжить?");
        if(!COP.us.userYes()) return;
        
        System.out.println("Список грузов выбранного профиля:");
        
        int len = selectedCargoProfile.getCargoList().size();
        for (int i = 0; i < len; i++) {
            System.out.printf("%d: %s\n", i + 1, selectedCargoProfile.getCargoList().get(i).toDisplayString());
        }
        
        int res = IntUtils.getChoice(1, len);
        
        selectedCargoProfile.getCargoList().remove(res - 1);
        System.out.println("Груз удален из выбранного профиля");
        saveData();
    }
    
    @Override
    public void deleteCargoProfile() {
        
        makeSureCargoProfileSelected();
        
        System.out.println("""
                Меню удаления профиля грузов. Эта операция необратима.
                Вы уверены, что хотите продолжить?
                """);
        if(!COP.us.userYes()) return;
        
        COP.db.getCargoProfiles().remove(selectedCargoProfile);
        System.out.println("Профиль грузов удален");
        saveData();
        
    }
    
    @Override
    public void findCargo() {
    
    }
    
    @Override
    public void findCargoProfile() {
    
    }
    
    String getInputWhileNotRegex(String regex, String errorMessage) {
        String userInput = COP.scanner.nextLine().trim();
        // System.out.println("Вы ввели: " + "\"" + userInput + "\"");
        // var l = userInput.getBytes();
        // for (int i = 0; i < l.length; i++) {
        //     System.out.println(
        //             Integer.toBinaryString(l[i])
        //     );
        // }
        // System.out.println(userInput.matches(".+"));
        // System.out.println(regex);
        // System.out.println(userInput.matches(regex));
        while(!userInput.matches(regex)) {
            if (!errorMessage.isEmpty()) { System.out.println(errorMessage); }
            userInput = COP.scanner.nextLine().trim();
            System.out.println(userInput);
        }
        return userInput;
    }
    
    
    public void listMyCargoProfiles() {
        System.out.println("Отслеживание профилей грузов...");
        var thisUser = COP.us.getAuthorizedUser();
        if(thisUser == null) {
            System.out.println("Пользователь пока не вошел");
            return;
        }
        
        var allCargoProfiles = COP.db.getCargoProfiles();
        if(allCargoProfiles == null || allCargoProfiles.isEmpty()) {
            System.out.println("Профилей пока нет в системе.");
            System.out.println("Выход из подпрограммы");
            return;
        }
        
        var myCargoProfiles = allCargoProfiles.parallelStream().filter(p -> {
            return p.getAuthorUUID().equals(thisUser.getFields().getUuid());
        }).toList();
        
        for(var i : myCargoProfiles) {
            System.out.println(i.toDisplayString());
        }
    }
    
    public void setDeliveryStatusMenu () {
        System.out.println("Меню установления статуса профиля грузов");
        if(!COP.us.userYes()) return;
        makeSureCargoProfileSelected();
        var vals = DeliveryStatus.values();
        for (int i = 0; i < vals.length; i++) {
            System.out.printf("%d - %s", i + 1, vals[i].getValue());
        }
        System.out.println("""
                Введите номер статуса, который требуется задать
        """);
        int res = IntUtils.getChoice(1, vals.length);
        setDeliveryStatus(res - 1);
        System.out.println("Все пучком, все сделано");
        saveData();
    }
    
    private void setDeliveryStatus(int index) {
        selectedCargoProfile.setDeliveryStatus(DeliveryStatus.values()[index]);
    }
    
    public void displayHistory() {
        System.out.println("Меню отображения перечня профилей грузов со статусом " + "\"" +
                DeliveryStatus.DELIVERED.getValue() + "\"" + "\n");
        // if(!COP.us.userYes()) return;
        System.out.println("""
                Ниже перечислены завершенные передачи
        """);
        COP.db.getCargoProfiles().parallelStream()
                .filter(cp -> {
                    return cp.getAuthorUUID().equals(COP.us.getAuthorizedUser().getFields().getUuid())
                            && cp.getDeliveryStatus().equals(DeliveryStatus.DELIVERED);
                })
                .forEach(i -> {
                    System.out.printf("%s\n", i.toDisplayString());
                });
    }
}
