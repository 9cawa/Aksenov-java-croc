package ru.croc.task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String str = "/*\n" +
                " * My first ever program in Java!\n" +
                " */\n" +
                "class Hello { // class body starts here \n" +
                "\n" +
                "    /* \n" +
                "    * main method \n" +
                "    */\n" +
                "    public static void main(String[] args/* we put command line arguments here*/) {\n" +
                "        // this line prints my first greeting to the screen\n" +
                "        System.out.println(\"Hi!\"); // :)\n" +
                "    }\n" +
                "} // the end\n" +
                "// to be continued...\n";

        System.out.print(deleteComments(str));
    }

    static String deleteComments(String str) {
        StringBuilder strWithoutComments = new StringBuilder();

        // Компилирую входную строку в регулярное выражение, где в первых скобках
        // проверка на комметарии типа /* comment */ , а во вторых скобках на комментарии типа //comment
        Pattern pattern = Pattern.compile("(/\\*([\\S\\s]+?)\\*/)|(//.*)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            matcher.appendReplacement(strWithoutComments, "");
        }
        matcher.appendTail(strWithoutComments);

        return String.valueOf(strWithoutComments);
    }
}
