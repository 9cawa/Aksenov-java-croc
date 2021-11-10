package ru.croc.task12;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    private static synchronized String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    private static synchronized String hashPassword(String password) {
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

    public static synchronized StringBuilder generatePass(StringBuilder myPass) {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            myPass.append((char) ('a' + random.nextInt(26)));
        }
        return myPass;
    }

    static class Run implements Runnable{
        StringBuilder myPass = new StringBuilder(4);

        @Override
        public void run() {
            myPass = generatePass(myPass);
            System.out.println(Thread.currentThread().toString() + ": " + (myPass.toString()));
        }
        public StringBuilder getMyPass() {
            return this.myPass;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long t1 = System.currentTimeMillis();
        int numberOfThreads = Integer.parseInt(args[0]);
        String hashPass = args[1];
        boolean passFounded = false;
        String password = "";
        Run[] runs = new Run[numberOfThreads];
        Thread[] threads = new Thread[numberOfThreads];
        while (!passFounded) {
            for (int i = 0; i < numberOfThreads; i++) {
                runs[i] = new Run();
            }
            for (int i = 0; i < numberOfThreads; i++) {
                threads[i] = new Thread(runs[i]);
                threads[i].start();
            }
            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].join();
                StringBuilder myPass = runs[i].getMyPass();
                if (hashPassword(myPass.toString()).equals(hashPass)) {
                    passFounded = true;
                    password = myPass.toString();
                    break;
                }
                //System.out.println("!!!!");
            }
        }
        //System.out.println(hashPass.equals(hashPassword("passwrd")));
        System.out.println("My password is \"" + password + "\"");
        System.out.println("Затраченное время: " + (System.currentTimeMillis()-t1)/1_000 + " сек");
    }
}
