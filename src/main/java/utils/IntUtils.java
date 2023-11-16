package utils;

import application.COP;

public class IntUtils {
    public static int getChoice(int low, int high) {
        int res = 0;
        System.out.printf("Введите число от %d до %d: ", low, high);
        String entry = COP.scanner.nextLine();
        try {
            res = Integer.parseInt(entry);
        } catch (Exception e) {
            System.out.println("Введите правильное число");
            return getChoice(low, high);
        }
        if (res < low || res > high) {
            System.out.println("Введите правильное число");
            return getChoice(low, high);
        } else {
            return res;
        }
    }
}
