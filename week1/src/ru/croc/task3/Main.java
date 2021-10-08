package ru.croc.task3;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите массив: ");
        //Вводим массив в виде строки
        String arr = sc.nextLine();
        //Делаем из строки массив строк
        String[] arrStr = arr.split(" ");
        //Делаем из массива строк массив интов
        int[] arrInt = new int[arrStr.length];
        for (int i = 0; i < arrInt.length; i++) {
            arrInt[i] = Integer.parseInt(arrStr[i]);
        }

        //Поиск максимального и минимального элемента и их индексов
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < arrInt.length; i++) {
            if (arrInt[i] > max) {
                max = arrInt[i];
                maxIndex = i;
            }
            if (arrInt[i] < min) {
                min = arrInt[i];
                minIndex = i;
            }
        }

        //Меняем местами максимальный и минимальный элементы с элементами, стоящими в начале и в конце
        arrInt[minIndex] = arrInt[0];
        arrInt[maxIndex] = arrInt[arrInt.length - 1];
        arrInt[0] = min;
        arrInt[arrInt.length - 1] = max;
        System.out.println("Результат: " + Arrays.toString(arrInt));
    }
}
