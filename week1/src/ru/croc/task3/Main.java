package ru.croc.task3;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите массив: ");
        //Вводим массив в виде строки
        String arr = sc.nextLine();
        //Делаем из строки массив интов
        int[] arrInt = setArray(arr);

        //Сортируем массив по возрастанию
        Arrays.sort(arrInt);
        System.out.println("Результат: " + Arrays.toString(arrInt));
    }

    static int[] setArray(String arr) {
        String[] arrStr = arr.split(" ");
        int[] arrInt = new int[arrStr.length];
        for (int i = 0; i < arrInt.length; i++) {
            arrInt[i] = Integer.parseInt(arrStr[i]);
        }
        return arrInt;
    }
}
