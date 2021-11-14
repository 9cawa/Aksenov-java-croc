package ru.croc.task14;

import java.util.Objects;

public class User {
    private String filmsWatched;

    public User(String filmsWatched) {
        this.filmsWatched = filmsWatched;
    }

    @Override
    public String toString() {
        return filmsWatched;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return filmsWatched.equals(user.filmsWatched);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmsWatched);
    }
}
