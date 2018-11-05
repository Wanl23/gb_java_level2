package lesson3.main_task;

import java.util.HashMap;
import java.util.Map;
//2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
// В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
// номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
// тогда при запросе такой фамилии должны выводиться все телефоны.
//    Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще
// дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.).
// Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main(), прописывая add() и get().

public class PhoneBook {
    private Map<Integer, String> phoneBook = new HashMap<>();

    public void add(Integer phoneNumber, String surname){
        phoneBook.put(phoneNumber, surname);
    }

    public String get(String surname){
        String result = "";
        for(Map.Entry<Integer, String> pair : phoneBook.entrySet()){
            if(surname.equals(pair.getValue())){
                result += pair.getKey() + ", ";
            }
        }
        result = result.substring(0, result.length() - 2);

        return result;
    }
}
