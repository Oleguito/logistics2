package service;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import settings.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static utils.FileUtils.readTextFile;
import static utils.FileUtils.writeTextFile;

public class Encrypt {
    public static void encryptFile(String filename) {
        try {
            var path = Path.of(Settings.filesDir + filename);
            byte[] bytes = Files.readAllBytes(path);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = rightRotateOnce(bytes[i]);
            }
            Files.write(path,bytes, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void decryptFile(String filename) {
        try {
            var path = Path.of(Settings.filesDir + filename);
            byte[] bytes = Files.readAllBytes(path);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = leftRotateOnce(bytes[i]);
            }
            Files.write(path,bytes, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static byte rightRotateOnce(byte what) {
        int thing = (int) what;
        int one = (thing << 31) >>> 24;
        int two = (thing << 24) >>> 25;
        int res = one | two;
        return (byte) res;
    }
    
    public static byte leftRotateOnce(byte what) {
        int thing = (int) what;
        int one = (thing << 24) >>> 31;
        int two = thing << 1;
        byte res = (byte) (one | two);
        return res;
    }
    
    public static String decryptString(String string) {
        char[] contentsArray = string.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contentsArray.length; i++) {
            result.append(decryptSymbol(contentsArray[i]));
        }
        return result.toString();
    }
    
    public static String encryptString(String string) {
        char[] contentsArray = string.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contentsArray.length; i++) {
            result.append(encryptSymbol(contentsArray[i]));
        }
        return result.toString();
    }
    
    private static char encryptSymbol(char symbol) {
        return (char) (symbol << 4);
    }
    
    private static char decryptSymbol(char symbol) {
        return (char) (symbol >> 4);
    }
    
    
    public static String getHMACof(String key, String data) {
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key).hmacHex(data).toString();
    }
}
