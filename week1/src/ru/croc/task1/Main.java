package ru.croc.task1;

import ru.croc.task1.pointPackage.Point;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Point p1 = new Point();
        System.out.print("Введите координату х вершины №1: ");
        p1.setX(sc.nextDouble());
        System.out.print("Введите координату y вершины №1: ");
        p1.setY(sc.nextDouble());

        Point p2 = new Point();
        System.out.print("Введите координату х вершины №2: ");
        p2.setX(sc.nextDouble());
        System.out.print("Введите координату y вершины №2: ");
        p2.setY(sc.nextDouble());

        Point p3 = new Point();
        System.out.print("Введите координату х вершины №3: ");
        p3.setX(sc.nextDouble());
        System.out.print("Введите координату y вершины №3: ");
        p3.setY(sc.nextDouble());

        if (p1.areEquals(p2) || p2.areEquals(p3) || p3.areEquals(p1)) {
            System.out.println("Вы ввели две одинаковые точки!");
        } else {
            System.out.println("Площадь треугольника: " + String.format("%.1f",computeArea(p1, p2, p3)));
        }
    }

    //Метод, вычисляющий площадь треугольника по координатам его вершин
    static double computeArea(Point p1, Point p2, Point p3) {
        double area;
        //Стороны a, b, c для нахождения полупериметра
        double a = p1.distanceTo(p2);
        double b = p2.distanceTo(p3);
        double c = p3.distanceTo(p1);
        //Полупериметр для формулы Герона
        double p = (a + b + c) / 2.0;
        //Нахождение площади по формуле Герона
        area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return area;
    }
}
