import entities.User;
import interfaces.ServiceInterface;
import service.Service;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.Scanner;

class O {
    int field;
    
    O(int a) {this.field = -a;}
    O(String a) {this.field = a.length();}
}

class ServiceInterfaceClass implements ServiceInterface {
    @Override
    public User addUser() {
        return Service.addUser();
    }
}

public class Main {
    
    
    
    public static void main(String[] args) {
     
        ServiceInterface si = () -> { return Service.addUser(); };
        
        User user = si.addUser();
        
        ArrayList <User> users = new ArrayList<>();
        
        
        
        
        // "skdjhfks435b34j5g3b5j3g53fg6h4j5" 32 / 64 / ....
        String sdf = "skjdfhskdhjf";
        
        Integer b = new Integer(3);
        
        int[] ints;
        ints = new int[54];
        
        byte x = 1;
        char c = 'A'; // 65
        long l = Long.MAX_VALUE;
        // System.out.println(l);
        
        
        // String s = Service.getPassword();
        
        
        
        
        
        // O object = new O();
        // object.non_static_func();
        //
        // O.static_func();
        
        // Menu.run();
        // System.out.println(Encrypt.encryptString("Boo"));
        // Encrypt.writeFile("sfddf.txt","sldkfjsdl;kf");
        // Encrypt.decryptFile("filename.txt");
        // Class c = entities.UserFields.class;
        // for (Field f : c.getDeclaredFields()) {
        //     System.out.println(f.getType());
        // }
    }
    
    
}
