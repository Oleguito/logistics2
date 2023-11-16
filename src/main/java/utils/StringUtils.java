package utils;

public class StringUtils {
    public static String fitToWidth(String message, int width) {
        int len = message.length();
        if(len < width) {
            return message + " ";
        } else {
            return message.substring(0, width - 4) + " ...";
        }
    }
}
