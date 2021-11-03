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
        while (scanner.hasNextLine()) {
            count += scanner.nextLine().split("\\s+").length;
        }
        System.out.println("Количество слов в файле: " + count);
    }
}


