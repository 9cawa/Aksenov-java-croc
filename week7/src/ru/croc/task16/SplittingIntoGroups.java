package ru.croc.task16;

import java.util.*;

public class SplittingIntoGroups {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int[] bounds = new int[args.length];   // Границы возрастных групп

        for (int i = 0; i < bounds.length; i++) {
            if (Integer.parseInt(args[i]) < 123 && Integer.parseInt(args[i]) > 0)
                bounds[i] = Integer.parseInt(args[i]);
            else {
                System.out.println("Границы возрастных групп введены неверно! Завершение работы...");
                System.exit(0);
            }
        }

        ArrayList<Respondent> respondents = new ArrayList<>();
        respondents.add(new Respondent("Егоров Алан Петрович",7));
        respondents.add(new Respondent("Соколов Андрей Сергеевич", 15));
        respondents.add(new Respondent("Смолов Сергей Сергеевич", 12));
        respondents.add(new Respondent("Иванов Варлам Якунович",88));
        respondents.add(new Respondent("Ярилова Розалия Трофимовна",29));
        respondents.add(new Respondent("Кошельков Захар Брониславович",105));
        respondents.add(new Respondent("Дьячков Нисон Иринеевич",88));
        respondents.add(new Respondent("Старостин Ростислав Ермолаевич",50));

        System.out.println("Введите респондентов (ФИО, возраст). Если хотите завершить ввод введите \"END\".");
        addingToList(respondents);
        listProcessing(bounds, respondents);
    }

    /** Метод добавления респондетов в список **/
    private static void addingToList(ArrayList<Respondent> respondents) {
        WHILE:
        while (true) {
            String fullName = sc.next();
            if (fullName.equalsIgnoreCase("END")) {
                break;
            }
            fullName += " " + sc.next() + " " + sc.next().replace(",","");
            int age = sc.nextInt();

            if (age > 123) {
                System.out.println("Нет людей старше 123 лет! Введите респондента заново.");
                continue;
            }

            if (age < 0) {
                System.out.println("Возраст человека не может быть меньше 0! Введите респондента заново.");
                continue;
            }

            Respondent candidateRespondent = new Respondent(fullName, age);

            for (Respondent respondent : respondents) {
                if (respondent.equals(candidateRespondent)) {
                    System.out.println("В рамках одного опроса не может встретиться полных" +
                            " однофамильцев с одинаковыми возрастами! Введите респондента заново.");
                    continue WHILE;
                }
            }
            respondents.add(candidateRespondent);
        }
    }

    /** Обработка списка респондентов, в соответствии с условием задачи **/
    private static void listProcessing(int[] bounds, ArrayList<Respondent> respondents) {
        //Обработка всех границ кроме 0-первая граница
        for (int i = bounds.length - 1; i >= 0; i--) {
            ArrayList<Respondent> respondentsInBound = new ArrayList<>();
            Iterator<Respondent> it = respondents.iterator();
            while (it.hasNext()) {
                Respondent resp = it.next();
                if (resp.getAge() > bounds[i] ) {
                    respondentsInBound.add(resp);
                    it.remove();
                }
            }
            if (respondentsInBound.size() != 0) {
                //Сортируем респондентов в определенной группе возрастов
                Collections.sort(respondentsInBound, new RespondentsComparator());
                String resps = respondentsInBound
                        .toString()
                        .replace("[", "")
                        .replace("]", "");
                try {
                    System.out.println((bounds[i] + 1) + "-" + bounds[i + 1] + ": " + resps);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println((bounds[i] + 1) + "+: " + resps);
                }
            }
        }

        //0-первая граница
        if (respondents.size() > 0) {
            ArrayList<Respondent> respondentsInBound = new ArrayList<>();
            Iterator<Respondent> it = respondents.iterator();
            while (it.hasNext())
                respondentsInBound.add(it.next());
            //Сортируем респондентов в последней группе возрастов
            Collections.sort(respondentsInBound, new RespondentsComparator());
            System.out.print("0-" + bounds[0] + ": " + respondentsInBound
                    .toString()
                    .replace("[", "")
                    .replace("]", ""));
        }
    }
}
