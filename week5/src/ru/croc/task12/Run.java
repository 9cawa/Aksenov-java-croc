package ru.croc.task12;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Run implements Runnable{

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private final int numberOfThreads; //Количество потоков
    private final int threadNumber;    //Номер потока
    private final int passLength;      //Длина пароля
    private long begin;                //Границы начала и конца конкретного потока для
    private long end;                  // формирования пароля брутфорсом
    private final long numberOfOptions;//Количество возможных вариантов паролей
    private static volatile boolean passFounded = false; // Проверка на нахождение нужного пароля

    /** Функция генерации хеша MD-5 **/
    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    /** Функция для генерации хеша MD-5 **/
    private static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

    /** Конструктор, в котором так же считаются:
     * кол-во возможных вариантов пароля, границы для вычисления конкретного потока **/
    public Run(int threadNumber, int numberOfThreads, int passLength) {
        this.threadNumber = threadNumber;
        this.numberOfThreads = numberOfThreads;
        this.passLength = passLength;

        numberOfOptions = (long) Math.pow(26, passLength);
        begin = (numberOfOptions * (threadNumber - 1)) / numberOfThreads;
        end = (numberOfOptions * threadNumber) / numberOfThreads - 1;
    }

    /** Метод для формирования комбинации пароля в StringBuilder, в соответствии с номером потока **/
    private StringBuilder generatePass(long n) {
        int[] passASCII = new int[passLength];

        //Циклически записываем ASCII код символа в массив
        for (int i = 0; i < passLength; i++) {
            passASCII[i] = (int) (n % 26) + 'a';
            n = n / passLength;
        }

        StringBuilder password = new StringBuilder();

        //Циклически записываем в StringBuilder char'ы массива passASCII
        for (int i = 0; i < passLength; i++) {
            password.append((char) (passASCII[i]));
        }
        return password;
    }

    /** Метод Run, циклически перебирающий комбинации паролей, в соответствии номером потока **/
    @Override
    public void run() {
        while (!passFounded) {
            for (long i = begin; i < end; i++) {
                String password = generatePass(i).toString();
                //System.out.println(Thread.currentThread().getName() + " : " + password);
                if (hashPassword(password).equals(Main.hashPass)) {
                    Main.password = password;
                    passFounded = true;
                    break;
                }
            }
        }
    }
}
