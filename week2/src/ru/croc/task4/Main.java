package ru.croc.task4;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество аннотаций:");
        Annotation[] annotations = new Annotation[sc.nextInt()];
        for (int i = 0; i < annotations.length; i++) {
            System.out.println("Как вы хотите выделить область?");
            System.out.println("1. Прямоугольником\n2. Окружностью");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Введите кооридинаты левого нижнего и правого верхнего углов.");
                    Figure rectangle = new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                    System.out.println("Введите подпись: ");
                    Annotation annotation = new Annotation(rectangle, sc.next());
                    annotations[i] = annotation;
                    break;
                case 2:
                    System.out.println("Введите координаты центра и радиус.");
                    Figure circle = new Circle(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    System.out.println("Введите подпись: ");
                    Annotation annotationTwo = new Annotation(circle, sc.next());
                    annotations[i] = annotationTwo;
                    break;
                default:
                    System.out.println("Такая команда не поддерживается");
                    i--;
                    break;
            }
        }

        System.out.println("Ваши аннотации:");
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
    }
}
