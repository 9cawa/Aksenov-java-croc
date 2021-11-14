package ru.croc.task14;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final String listOfFilms = "C:\\Users\\Александр\\Desktop\\films.txt";
    private static final String browsingHistory = "C:\\Users\\Александр\\Desktop\\history.txt";

    /** Записываем параметры из файла в HashMap **/
    static HashMap<Integer,String> filmsToHashMap(String listOfFilms) {
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(listOfFilms));
        } catch (IOException e) {
            System.out.println("Не удалось открыть указанный файл.");
            return null;
        }

        HashMap<Integer,String> films = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] strFilms = scanner.nextLine().split(",");
            films.put(Integer.parseInt(strFilms[0]), strFilms[1]);
        }
        return films;
    }

    /** Записываем параметры из файла в ArrayList **/
    static ArrayList<String> historyToList(String browsingHistory) {
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(browsingHistory));
        } catch (IOException e) {
            System.out.println("Не удалось открыть указанный файл.");
            return null;
        }

        ArrayList<String> history = new ArrayList<>();
        while (scanner.hasNextLine()) {
            history.add(scanner.nextLine());
        }
        return history;
    }

    /** Метод, выбирающий пользователей которые смотрели те же фильмы,
     *  что и выбранный пользователь **/
    static List<User> chooseUsers(User userForRecommendation, List<User> users) {
        List<User> usersWatchedSameFilms = new ArrayList<>();
        for (User user : users) {
            if (compareUsers(userForRecommendation, user))
                usersWatchedSameFilms.add(user);
        }

        return usersWatchedSameFilms;

    }

    /** Метод, для сравнения двух пользователей по их просмотрам фильмов.
     * Возвращает true если пользователь посмотрел хотя бы половину фильмов другого пользователя. **/
    static boolean compareUsers(User user1, User user2) {
        String[] user1Films = user1.toString().split(",");
        String[] user2Films = user2.toString().split(",");
        Set<String> sameFilms = new HashSet<>();

        for (String film : user1Films) {
            for (String film2 : user2Films) {
                if (film.equals(film2))
                    sameFilms.add(film2);
            }
        }

        if (sameFilms.size() >= Math.round(user1Films.length / 2.0))
            return true;
        return false;
    }


    /** Метод, выполняюищй поиск фильмов-кандидатов, исключая те, которые смотрел выбранный пользователь **/
    static ArrayList<String> candidatesForRecommendation(User userForRecommendation, List<User> choseUsers) {
        ArrayList<String> candidateFilms = new ArrayList<>();
        for (User user : choseUsers) {
            String[] userFilms = user.toString().split(",");
            for (String film : userFilms) {
                if (!userForRecommendation.toString().contains(film))
                    candidateFilms.add(film);
            }
        }
        return candidateFilms;
    }

    /** Метод, возвращающий индентификатор фильма для рекомендации **/
    static int recommendFilm(ArrayList<String> candidates) {
        //Находим самый часто встречающийся индентификатор
        HashMap<String, Integer> hm = new HashMap<>();
        int max = 1;
        String temp = "";

        for (String candidate : candidates) {
            if (hm.get(candidate) != null) {
                int count = hm.get(candidate);
                count++;
                hm.put(candidate, count);

                if (count>max) {
                    max = count;
                    temp = candidate;
                }
            } else {
                hm.put(candidate,1);
            }
        }
        return Integer.parseInt(temp);

    }

    public static void main(String[] args) {
        HashMap<Integer,String> films = filmsToHashMap(listOfFilms);
        ArrayList<String> history = historyToList(browsingHistory);

        //System.out.println("All films: " + films);
        //System.out.println("Browsing history: " + history);

        List<User> users = new ArrayList<>();
        for (int i = 0; i < history.size(); i++) {
            users.add(new User(history.get(i)));
        }

        //System.out.println("Users: " + (users));

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the IDs of the movies you have watched (separated by commas without spaces): ");
        String inputFilms = sc.next();
        User userForRecommendation = new User(inputFilms);

        List<User> choseUsers = chooseUsers(userForRecommendation, users);
        if (choseUsers.size() == 0) {
            System.out.println("There is no recommendations for you.");
        } else {

            //System.out.println("Users chose by same films watched by user for rec: " + choseUsers);

            ArrayList<String> candidateFilms = candidatesForRecommendation(userForRecommendation, choseUsers);

            System.out.println("Candidate films for [" + userForRecommendation + "] : " + candidateFilms);

            System.out.println(films.get(recommendFilm(candidateFilms)));
        }
    }

}
