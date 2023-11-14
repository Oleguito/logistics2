package application;

// import interfaces.ServiceInterface;
import service.menu.Menu;
import enums.Role;

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
        
        // COP.us.setAuthorizedUser(
        //         COP.db.getUsers().stream()
        //                          .filter(u -> u.getFields().getRole().equals(Role.GUEST))
        //                          .findFirst().get()
        // );
        
        // Menu.fillMenuAddressesHashMap();
        // Menu.setMenus();
        // Menu.menuAddresses.put(Menu.profileMenu, Menu.menuAddresses.get(Menu.nonEnteredProfileMenu));
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
        
        
        // FileUtils.writeFile("src/main/resources/users.db", sb.toString());
        
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
