package ru.croc.task4;

public class Circle extends Figure {
    public int x1, y1;
    public int r;

    public Circle(int x1, int y1, int r) {
        if (r < 0) {
            throw new IllegalArgumentException("Введите положительное число для радиуса");
        }
        this.x1 = x1;
        this.y1 = y1;
        this.r = r;
    }

    @Override
    public String toString() {
        return "C " + "(" + x1 + ", " + y1 + "), " + r;
    }

    //Getters and Setters
    public int getX1() {
        return x1;
    }
    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }
    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getR() {
        return r;
    }
    public void setR(int r) {
        this.r = r;
    }
}
