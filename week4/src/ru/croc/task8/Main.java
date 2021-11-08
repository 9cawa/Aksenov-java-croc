package ru.croc.task8;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.println("Не удалось открыть указанный файл.");
            return;
        }

        int count = 0;
        boolean condition;
        String word;
        while (scanner.hasNext()) {
            condition = false;
            word = scanner.next();
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') ||
                        (ch >= 'а' && ch <= 'я') || (ch >= 'А' && ch <= 'Я') ) {
                    condition = true;
                    break;
                }
            }
            if (condition) {
                count++;
            }
        }
        System.out.println("Количество слов в файле: " + count);
    }
}
