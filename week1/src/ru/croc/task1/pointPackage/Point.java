package ru.croc.task1.pointPackage;

public class Point {
    double x;
    double y;

    //Getters and Setters
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    //Метод для сравнения значений двух объектов класса Point
    public boolean areEquals (Point p) {
        return this.getX() == p.getX() && this.getY() == p.getY();
    }

    //Метод, который рассчитывает дистанцию между двумя точками
    public double distanceTo(Point p) {
        double distance;
        distance = Math.sqrt(Math.pow((p.getX() - this.getX()), 2) + Math.pow((p.getY() - this.getY()), 2));
        return distance;
    }
}
