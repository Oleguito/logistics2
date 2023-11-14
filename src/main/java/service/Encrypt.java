package service;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static utils.FileUtils.readFile;
import static utils.FileUtils.writeFile;

public class Encrypt {
    public static void encryptFile(String filename) {
        writeFile(filename, encryptString(readFile(filename)));
    }
    
    public static void decryptFile(String filename) {
        writeFile(filename, decryptString(readFile(filename)));
    }
    

    private static String decryptString(String string) {
        char[] contentsArray = string.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contentsArray.length; i++) {
            result.append(decryptSymbol(contentsArray[i]));
        }
        return result.toString();
    }
    
    private static String encryptString(String string) {
        char[] contentsArray = string.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contentsArray.length; i++) {
            result.append(encryptSymbol(contentsArray[i]));
        }
        return result.toString();
    }
    
    private static char encryptSymbol(char symbol) {
        return (char) (symbol + 255);
    }
    
    private static char decryptSymbol(char symbol) {
        return (char) (symbol - 255);
    }
    
    
    public static String getHMACof(String key, String data) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key).hmacHex(data).toString();
    }
}
