package ru.croc.task5;

import ru.croc.task4.Figure;
import ru.croc.task4.Annotation;

import java.util.Scanner;

public class Main {
    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Введите количество аннотаций:");
        Annotation[] annotations = new Annotation[sc.nextInt()];
        setAnnotations(annotations);

        System.out.println("Ваши аннотации:");
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
        System.out.println();

        AnnotatedImage annotatedImage = new AnnotatedImage("somepath", annotations);

        moveArea(annotatedImage);
        System.out.println("Ваши аннотации после перемещений:");
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
    }

    static Annotation[] setAnnotations(Annotation[] annotations) {
        for (int i = 0; i < annotations.length; i++) {
            System.out.println("Как вы хотите выделить область?");
            System.out.println("1. Прямоугольником\n2. Окружностью");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Введите кооридинаты левого нижнего и правого верхнего углов.");
                    Figure rectangle = new MovableRectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                    System.out.println("Введите подпись: ");
                    Annotation annotation = new Annotation(rectangle, sc.next());
                    annotations[i] = annotation;
                    break;
                case 2:
                    System.out.println("Введите координаты центра и радиус.");
                    Figure circle = new MovableCircle(sc.nextInt(), sc.nextInt(), sc.nextInt());
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
        return annotations;
    }

    static void moveArea (AnnotatedImage annotatedImage) {
        mark:
        for (int i = 0; i < i+1; i++) {
            System.out.println("Хотите ли вы переместить одну из размеченных областей?");
            System.out.println("1. Да\n2. Нет");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Как вы хотите найти область для перемещения?");
                    System.out.println("1. По координатам точки\n2. По подписи");
                    switch (sc.nextInt()) {
                        case 1:
                            System.out.println("Введите координаты х и у:");
                            int x = sc.nextInt();
                            int y = sc.nextInt();
                            Figure f = null;
                            try {
                                f = (Figure) annotatedImage.findByPoint(x, y).getFigure();
                                System.out.println("Ваша область: " + f.toString());
                                System.out.println("Введите на сколько вы хотите сместить координаты х и у");
                                ((Movable) f).move(sc.nextInt(), sc.nextInt());
                                System.out.println("Ваша область после перемещения: " + f.toString());
                            } catch (Exception e) {
                                System.out.println("Области с такими координатами нет.");
                            }
                            break;
                        case 2:
                            System.out.println("Введите подпись:");
                            String signature = sc.next();
                            Figure f2 = null;
                            try {
                                f2 = annotatedImage.findByLabel(signature).getFigure();
                                System.out.println("Ваша область: " + f2.toString());
                                System.out.println("Введите на сколько вы хотите сместить координаты х и у");
                                ((Movable) f2).move(sc.nextInt(), sc.nextInt());
                                System.out.println("Ваша область после перемещения: " + f2.toString());
                            } catch (Exception e) {
                                System.out.println("Области с такой подписью нет.");
                            }
                            break;
                        default:
                            System.out.println("Такая команда не поддерживается");
                            break;
                    }
                    break;
                case 2:
                    break mark;
                default:
                    System.out.println("Такая команда не поддерживается");
                    i--;
                    break;
            }
        }
    }
}
