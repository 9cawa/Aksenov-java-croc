package ru.croc.task19;

import javax.swing.*;
import java.awt.*;

public class HelloWorld extends JFrame {
    public static void setGUI() {
        JFrame frame = new JFrame(":)"); //Создаем окно и даем ему заголовок ":)"
        frame.setSize(400,400); //Задаем размер фрейма 400х400

        JPanel panel = new JPanel(); //Создаем панель
        JTextArea textArea = new JTextArea(); //Создаем текстовое поле
        textArea.setFont(new Font("Dialog", Font.PLAIN, 50)); //Задаем шрифт
        textArea.setSize(400, 400);   //Задаем размер окна textArea
        textArea.setEnabled(false);               //Делаем его недоступным для изменения
        textArea.append("\n\nHello, World!\n\n"); //Выводим на него сообщение "Hello, World!"
        textArea.setDisabledTextColor(Color.BLACK); //Задаем цвет текста "Черный"
        panel.add(textArea); //Добавляем на панель textArea
        frame.add(panel);    //Добавляем на фрейм панель

        frame.setVisible(true); //Делаем наше окно видимым
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Операцию завершения работы программы
                                                              // задаем на нажатие "крестика"
    }

    public static void main(String[] args) {
        setGUI();
    }
}
