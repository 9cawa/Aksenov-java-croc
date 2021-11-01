package ru.croc.task7;

import ru.croc.task7.exceptions.IllegalMoveException;

public class Knight {
    private ChessPosition[] sequenceOfPositions; //Последовательность ходов

    public Knight(ChessPosition[] sequenceOfPositions) {
        this.sequenceOfPositions = sequenceOfPositions;
    }

    /** Метод, проверяющий может ли конь переместиться из одной клетки в другую **/
    private static boolean canMove(ChessPosition p1, ChessPosition p2) throws IllegalMoveException {

        //Конь может переместиться только когда модуль(буква - буква = 2) и (цифра - цифра = 1) или наоборот
        boolean check = (((Math.abs(p1.getX() - p2.getX()) == 2) && (Math.abs(p1.getY() - p2.getY()) == 1))
                    || ((Math.abs(p1.getX() - p2.getX()) == 1) && (Math.abs(p1.getY() - p2.getY()) == 2)));

        //Проверка на IllegalMoveException
        if (!check) {
            throw new IllegalMoveException(p1, p2);
        } else {
            return true;
        }
    }

    /** Метод, проверяющий, что конь может пройти всю последовательность позиций **/
    public boolean canMoveAll(ChessPosition[] sequenceOfPositions) throws IllegalMoveException {
        for (int i = 0; i < sequenceOfPositions.length - 1; i++) {
            canMove(sequenceOfPositions[i], sequenceOfPositions[i + 1]);
        }
        return true;
    }

}
