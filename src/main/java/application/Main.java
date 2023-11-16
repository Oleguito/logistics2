package application;

// import interfaces.ServiceInterface;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;
import entities.CargoProfile;
import service.CargoService;
import service.menu.Menu;
import enums.Role;
import utils.FileUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("""
                Здравствуйте и спасибо что выбрали нашу продукцию! Это программа для
                отслеживания грузов. В ней вы можете создавать свои посылки, отправлять
                их и следить за статусом доставки. Добро пожаловать!
                """);
        
        
        
        
        
        // var createdUser = new User(UserFields.builder()
        //         .uuid(UUID.randomUUID())
        //         .login("g")
        //         .passwordHash(Encrypt.getHMACof(Settings.userSecret,""))
        //         .fullName("SampleLastName SampleName SamplePatronymic")
        //         .role(Role.GUEST)
        //         .age(0)
        //         .build());
        // COP.db.getUsers().add(createdUser);
        // FileUtils.writeFile("users.db", UserMapper.convertListUsersToString(COP.db.getUsers()));
        
        // System.out.println();

        // COP.init();
        
        
        // Menu.fillMenuAddressesHashMap();
        // Menu.setMenus();
        // Menu.menuAddresses.put(Menu.profileMenu, Menu.menuAddresses.get(Menu.nonEnteredProfileMenu));
        
        // String[] names = {"Базилио", "Петро", "Клаудио", "Миладе", "Олег"};
        // Random random = new Random();
        // var lcp = new ArrayList <CargoProfile>();
        // var users = COP.db.getUsers();
        // for (int i = 0; i < 5; i++) {
        //     var author = users.get(random.nextInt(4)).getFields();
        //     CargoProfile cp = CargoProfile.builder()
        //         .uuid(UUID.randomUUID())
        //         .authorUUID(author.getUuid())
        //         .sender(names[random.nextInt(5)])
        //         .receiver(names[random.nextInt(5)])
        //         .cargoList(new ArrayList <>())
        //         .build();
        //    lcp.add(cp);
        // }
        
        
        // for(var i : Charset.availableCharsets().values()) {
        //     System.out.println(i);
        // }
        

        
        // System.out.println("Введите строку на русском");
        // var newin = new InputStreamReader(System.in, Charset.forName("windows-1251"));
        // var scanner = new java.util.Scanner(newin);
        // var b = scanner.nextLine();
        // System.out.println("Вы ввели: " +  "\"" + b + "\"");
        // FileUtils.serializeA(b, "test.set");
        // var a = (String) FileUtils.deserializeA("test.set");
        // System.out.println("десериализовано: " + "\"" + a + "\"");
        
        
        
        // FileUtils.serialize("object.dat", lcp);
        // var res = FileUtils.deserialize("object.dat");
        //
        // for(var i : res) {
        //     System.out.println(i.toDisplayString());
        // }
        
        // return;
        
        COP.us.setAuthorizedUser(
                COP.db.getUsers().stream()
                                 .filter(u -> u.getFields().getRole().equals(Role.USER))
                                 .findFirst().get()
        );

        
        
        // var list = COP.cs.listMyCargoProfiles();
        
        
        // COP.cs.addCargo();
        
        // COP.cs.listMyCargoProfiles();
        
        
        // res = FileUtils.deserialize("object.dat");
        //
        // for(var i : res) {
        //     System.out.println(i.toDisplayString());
        // }
        
        Menu.run();
        
        
        
        // var userService = new UserService();
        // userService.setAuthorizedUser(DataBase.getUsers().get(3));
        // userService.deleteUser();
        
        
        // ServiceInterface si = () -> { return MainService.addUser(); };
        //
        // User user = si.addUser();
        
        // String hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, "key").hmacHex("data");
        // String hmac1 = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, "key").hmacHex("data");
        // System.out.println(hmac);System.out.println(hmac1);
    
        
        // var users = DataBase.getUsers();
        // for(var i : users) {
        //     System.out.println(i.getFields().getFullName() + " " +
        //                         i.getFields().getLogin() + " " +
        //                         i.getFields().getAge());
        // }
        
        // DataBase.createTable("kjshdfklsjdf", new String[] {"sdfsdf", "sdfsdf", "sdfsdf"});
        
        // ArrayList <User> users.db = new ArrayList<>();
        //
        //
        //
        // // "skdjhfks435b34j5g3b5j3g53fg6h4j5" 32 / 64 / ....
        // String sdf = "skjdfhskdhjf";
        //
        // Integer b = new Integer(3);
        //
        // int[] ints;
        // ints = new int[54];
        //
        // byte x = 1;
        // char c = 'A'; // 65
        // long l = Long.MAX_VALUE;
        // System.out.println(l);
        
        
        // String s = Service.getPassword();
        
        
        
        
        
        // O object = new O();
        // object.non_static_func();
        //
        // O.static_func();
        
        // Menu.run();
        
        
        
        
        // DataBase.getUsers();
        
        // System.out.println(Encrypt.encryptString("Boo"));
        // Encrypt.writeFile("sfddf.txt","sldkfjsdl;kf");
        // Encrypt.decryptFile("filename.txt");
        // Class c = entities.UserFields.class;
        // for (Field f : c.getDeclaredFields()) {
        //     System.out.println(f.getType());
        // }
    }
    
    
}


