package ru.croc.task12;

public class Main {
    public static volatile String password;
    public static volatile String hashPass;

    public static void main(String[] args) {
        //long t = System.currentTimeMillis();
        int numberOfThreads = Integer.parseInt(args[0]); //Количество потоков
        hashPass = args[1];
        Thread[] threads = new Thread[numberOfThreads];  //Объявление потоков

        for (int i = 1; i <= numberOfThreads; i++) {
            threads[i - 1] = new Thread(new Run(i, numberOfThreads, 7));
            threads[i - 1].start();
        }

        try {
            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Password: " + password);
        //System.out.println("Затраченное время на поиск пароля: " + (System.currentTimeMillis()-t)/1000);
    }
}
