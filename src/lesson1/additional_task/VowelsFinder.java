package lesson1.additional_task;

public class VowelsFinder {
    public static int counter(String s){
        char[] chars = s.toCharArray();
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        int counter = 0;
        for (int i = 0; i < vowels.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if(chars[j] == vowels[i]){
                    counter++;
                }
            }
        }
        return counter;
    }
}
