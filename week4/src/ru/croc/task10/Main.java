package ru.croc.task10;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.println("Не удалось открыть указанный файл.");
        }

        ArrayList<Call> calls = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(",");
            if (input.length == 2) {
                int from = Integer.parseInt(input[0]);
                int to = Integer.parseInt(input[1]);
                calls.add(new Call(from, to));
            }
        }

        int peak = getPeakCallCount(calls);
        System.out.println("Пиковое количество одновременных разговоров: " + peak);
    }

    static int getPeakCallCount(ArrayList<Call> calls) {
        int peak = 0;
        int counter = 0;
        for(Call currentCall : calls){
            for(Call otherCall : calls){
                if ((otherCall.getEnd() <= currentCall.getEnd() && otherCall.getEnd() >= currentCall.getBegin()) ||
                        otherCall.getBegin() <= currentCall.getEnd() && otherCall.getBegin() >= currentCall.getBegin()){
                    counter++;
                }
            }
            if(counter > peak){
                peak = counter;
            }
            counter = 0;
        }

        return peak - 1;
    }
}
