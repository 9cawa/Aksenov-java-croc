package ru.croc.task2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число байт: ");
        long number = sc.nextLong();
        System.out.println("Результат: " + printBytes(number));
    }

    static String printBytes(long n) {
        if (Math.abs(n) < 1024) {
            return String.format("%.1f", n * 1.0) + " B";
        } else if (Math.abs(n) < Math.pow(2, 20)) {
            return String.format("%.1f", n / 1024.0) + " KB";
        } else if (Math.abs(n) < Math.pow(2, 30)) {
            return String.format("%.1f", n / Math.pow(1024.0, 2)) + " MB";
        } else if (Math.abs(n) < Math.pow(2, 40)) {
            return String.format("%.1f", n / Math.pow(1024.0, 3)) + " GB";
        } else if (Math.abs(n) < Math.pow(2, 50)){
            return String.format("%.1f", n / Math.pow(1024.0, 4)) + " TB";
        } else {
            return String.format("%.1f", n / Math.pow(1024.0, 5)) + " PB";
        }
    }
}
