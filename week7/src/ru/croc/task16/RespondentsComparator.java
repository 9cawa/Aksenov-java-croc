package ru.croc.task16;

import java.util.Comparator;

public class RespondentsComparator implements Comparator<Respondent> {

    @Override
    public int compare(Respondent o1, Respondent o2) {
        if (o1.getAge() != o2.getAge())
            return (-Integer.compare(o1.getAge(), o2.getAge()));
        else {
            for (int i = 0; i < o1.getFullName().length(); i++) {
                if (o1.getFullName().charAt(i) != o2.getFullName().charAt(i))
                    return (Character.compare(o1.getFullName().charAt(i), o2.getFullName().charAt(i)));
            }
        }
        return 0;
    }

}
