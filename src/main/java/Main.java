import entities.User;
// import interfaces.ServiceInterface;
import entities.UserFields;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import service.MainService;
import database.DataBase;
import service.Menu;
import settings.Settings;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.UUID;


public class Main {
    
    public static void main(String[] args) {
     
        // ServiceInterface si = () -> { return MainService.addUser(); };
        //
        // User user = si.addUser();
        
        String hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, "key").hmacHex("data");
        String hmac1 = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, "key").hmacHex("data");
        // System.out.println(hmac);System.out.println(hmac1);
    
        
        // DataBase.getUsersFromDB();
        
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
