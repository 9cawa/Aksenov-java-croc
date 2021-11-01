package ru.croc.task7.exceptions;

import java.io.IOException;

public class IllegalPositionException extends IOException {
    private int x;
    private int y;

    public IllegalPositionException() {
    }

    public IllegalPositionException(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getMessage() {
        return "На шахматной доске нет такой клетки: " + (char) (this.x + 97) + (this.y + 1);
    }
}
