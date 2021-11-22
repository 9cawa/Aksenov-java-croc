package ru.croc.task13;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Filter implements BlackListFilter {

    public static void filterComments(List<String> comments, Set<String> blackList) {
        Iterator<String> it = comments.iterator();
        while (it.hasNext()) {
            String comment = it.next();
            for (String banWord : blackList) {
                if (comment.toUpperCase().contains(banWord))
                    it.remove();
            }
        }
    }
}
