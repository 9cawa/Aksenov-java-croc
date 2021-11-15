package ru.croc.taskB2;

import java.util.concurrent.Callable;

public class CountPi implements Callable<Float> {
    private long start; //Начало расчета
    private long end;   //Конец

    /** Конструктор **/
    public CountPi(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Float call() {
        float result = 0;
        for (long i = start; i < end; i++) {
            result += (float) ((Math.pow(-1, i)*4) / ((2 * i) + 1));  //Формула Лейбница
            //System.out.println(Thread.currentThread().getName() + " : " + result);
        }
        return result;
    }
}