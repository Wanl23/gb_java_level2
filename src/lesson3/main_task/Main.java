package lesson3.main_task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
//    1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
// Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
// Посчитать, сколько раз встречается каждое слово.

    public static Map<String, Integer> listToMap(ArrayList<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < list.size(); i++){
            int count = 1;
            for (int j = 0; j < list.size(); j++) {
                if(list.get(i).equals(list.get(j))){
                    map.put(list.get(i), count++);
                }
            }
        }
        return map;
    }

    public static void printUniqWords(Map<String, Integer> map) {
        System.out.println("Unique words: ");
        for(Map.Entry<String, Integer> pair : map.entrySet()){
            System.out.print(pair.getKey() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//    1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
// Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
// Посчитать, сколько раз встречается каждое слово.
        ArrayList<String> list = new ArrayList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        list.add("A");
        list.add("B");
        list.add("B");
        list.add("C");
        list.add("C");

        Map<String, Integer> map = listToMap(list);

        printUniqWords(map);

        System.out.println("Words repeated: \n" + map);

        System.out.println("-------------------------------");


//2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
// В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
// номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
// тогда при запросе такой фамилии должны выводиться все телефоны.
//    Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще
// дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.).
// Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main(), прописывая add() и get().
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(123456, "Ivanov");
        phoneBook.add(234567, "Petrov");
        phoneBook.add(345678, "Ivanov");
        phoneBook.add(456789, "Sidorov");
        phoneBook.add(567890, "Smirnov");
        phoneBook.add(678901, "Kovalev");
        phoneBook.add(789012, "Smirnov");
        phoneBook.add(890123, "Sokolov");

        System.out.println(phoneBook.get("Smirnov"));
    }
}
