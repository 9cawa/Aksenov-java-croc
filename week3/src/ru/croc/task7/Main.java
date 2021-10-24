package ru.croc.task7;

public class Main {
    public static void main(String[] args) throws IllegalPositionException, IllegalMoveException {
        String[] sequence = {"a8", "b6", "a8", "a8"}; //Строковое представление последовательности позиций

        ChessPosition[] sequenceOfPositions = new ChessPosition[sequence.length]; //Массив класса ChessPosition

        parsePositions(sequence, sequenceOfPositions);

        Knight knight = new Knight(sequenceOfPositions);

        if (knight.canMoveAll(sequenceOfPositions)) {
            System.out.println("OK");
        }
    }

    //Цикл, записывающий в массив класса ChessPosition строки последовательности через метод parse
    static void parsePositions(String[] sequence, ChessPosition[] sequenceOfPositions) throws IllegalPositionException {
        for (int i = 0; i < sequence.length; i++) {
            sequenceOfPositions[i] = ChessPosition.parse(sequence[i]);
        }
    }
}
