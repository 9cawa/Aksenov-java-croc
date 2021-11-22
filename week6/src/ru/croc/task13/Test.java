package ru.croc.task13;

import java.util.*;

public class Test extends Filter{
    public static void main(String[] args) {
        ArrayList<String> comments = new ArrayList<>();
        comments.add("Я ненавижу КРОК.");
        comments.add("Мне нравится заниматься в КРОК!");
        comments.add("Дарим до 10 тыс. бонусов к депозиту...");
        System.out.println("До фильтрации:    " + comments);

        HashSet<String> blackList = new HashSet<>();
        blackList.add("НЕНАВИЖУ");
        blackList.add("ДЕПОЗИТ");

        Filter.filterComments(comments,blackList);
        System.out.println("После фильтрации: " + comments);
    }
}
