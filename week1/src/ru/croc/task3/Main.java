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

        //Поиск индексов максимального и минимального элементов
        int minIndex = findMinAndMax(arrInt)[0];
        int maxIndex = findMinAndMax(arrInt)[1];

        //Меняем местами максимальный и минимальный элементы с элементами, стоящими в начале и в конце
        arrInt = swap(arrInt, minIndex, maxIndex);

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

    static int[] findMinAndMax(int[] arrInt) {
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < arrInt.length; i++) {
            if (arrInt[i] > arrInt[maxIndex]) {
                maxIndex = i;
            }
            if (arrInt[i] < arrInt[minIndex]) {
                minIndex = i;
            }
        }
        return new int[] {minIndex, maxIndex};
    }

    static int[] swap(int[] arrInt, int minIndex, int maxIndex) {
        int max = arrInt[maxIndex];
        int min = arrInt[minIndex];
        if (maxIndex != 0 && minIndex != 1) {
            arrInt[minIndex] = arrInt[0];
            arrInt[maxIndex] = arrInt[arrInt.length - 1];
            arrInt[0] = min;
            arrInt[arrInt.length - 1] = max;
            return arrInt;
        //Проверка на тонкий момент граничного случая и его обработка
        } else {
            arrInt[minIndex] = arrInt[0];
            arrInt[0] = min;

            int tmp = arrInt[arrInt.length - 1];
            arrInt[arrInt.length - 1] = max;
            arrInt[minIndex] = tmp;

            return arrInt;
        }
    }
}