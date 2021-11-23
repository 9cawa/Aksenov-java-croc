package ru.croc.task16;

import java.util.Objects;

public class Respondent {
    private String fullName;
    private int age;

    public Respondent(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return fullName + " (" + age + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Respondent))
            return false;
        Respondent that = (Respondent) o;
        return age == that.age && fullName.equals(that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, age);
    }
}
