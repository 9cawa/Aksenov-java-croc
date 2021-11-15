package ru.croc.taskB2;

import java.text.DecimalFormat;
import java.util.concurrent.*;

class Main {
    private final static ExecutorService pool = Executors.newCachedThreadPool();
    public static double accuracy;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long t1 = System.currentTimeMillis();

        accuracy = Integer.parseInt(args[0]); //Точность (кол-во знаков после запятой)
        double threshold = Math.pow(10,accuracy); //Порог до которого будет высчитываться формула Лейбница
        int numberOfThreads = Integer.parseInt(args[1]); //Кол-во потоков

        float result = (countPiParallel((long)threshold,numberOfThreads));

        System.out.println("Ответ: " + numberOfDecimalPlaces(result));
        pool.shutdown(); //Закрываем потоки (потоки прекращают принимать задачи)

        System.out.println("Программа нашла число пи за: " + (System.currentTimeMillis() - t1)/1000 + " секунд");
    }

    /** Метод, образующий строку с нужным количеством знаков после запятой **/
    static String numberOfDecimalPlaces(float result) {
        String formatOfResult = "#.";
        for (int i = 0; i < accuracy; i++) {
            formatOfResult += "#";
        }
        DecimalFormat decimalFormat = new DecimalFormat(formatOfResult);
        return decimalFormat.format(result);
    }

    /** Метод, вычисляющий число пи в потоках **/
    static float countPiParallel(double accuracy, int numberOfThreads) throws ExecutionException, InterruptedException {
        Future<Float>[] results = new Future[numberOfThreads]; //Создаем массив потоков

        results[0] = pool.submit(new CountPi(0, (long) (accuracy/numberOfThreads)));
        // Цикл, порождающий параллельные задачи, с разделением вычислений на равные части
        for (int i = 1; i < numberOfThreads; i++) {
            results[i] = pool.submit(new CountPi((long)accuracy*i/numberOfThreads, (long)accuracy*(i+1)/numberOfThreads));
        }

        float res = results[0].get();
        // Если точность - четная, то подсчет суммы будет начинаться с отрицательного значения,
        //а если нечетная, то с положительного.
        if (Main.accuracy % 2 == 0) {
            for (int i = 1; i < numberOfThreads; i++) {
                if (i % 2 == 0)
                    res -= (results[i].get());
                else
                    res += (results[i].get());
            }
        } else {
            for (int i = 1; i < numberOfThreads; i++) {
                if (i % 2 == 0)
                    res += (results[i].get());
                else
                    res -= (results[i].get());
            }
        }
        return res;
    }
}
