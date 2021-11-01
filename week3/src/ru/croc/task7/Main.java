package ru.croc.task7;

import ru.croc.task7.exceptions.IllegalMoveException;
import ru.croc.task7.exceptions.IllegalPositionException;

public class Main {
    public static void main(String[] args) {
        try {
            String[] sequence = {"a8", "b6", "a8"}; //Строковое представление последовательности позиций

            ChessPosition[] sequenceOfPositions = new ChessPosition[sequence.length]; //Массив класса ChessPosition

            parsePositions(sequence, sequenceOfPositions);

            Knight knight = new Knight(sequenceOfPositions);

            if (knight.canMoveAll(sequenceOfPositions)) {
                System.out.println("OK");
            }
        } catch (IllegalPositionException e) {
            System.out.println(e.getMessage());
        } catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    //Цикл, записывающий в массив класса ChessPosition строки последовательности через метод parse
    static void parsePositions(String[] sequence, ChessPosition[] sequenceOfPositions) throws IllegalPositionException {
        for (int i = 0; i < sequence.length; i++) {
            sequenceOfPositions[i] = ChessPosition.parse(sequence[i]);
        }
    }
}
