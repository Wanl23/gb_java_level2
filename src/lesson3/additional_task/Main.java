package lesson3.additional_task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
//    Необходимо из консоли считать пароль и проверить валидность,
//    результат будет true или false
//
//    Требования:
//  1. Пароль должен быть не менее 8ми символов.
//  2. В пароле должно быть число
//  3. В пароле должна быть хотя бы 1 строчная буква
//  4. В пароле должна быть хотя бы 1 заглавная буква
    public static boolean isPassValid(String pass){
        String pattern  = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}";
        if(pass.matches(pattern)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pass = br.readLine();

        System.out.println(isPassValid(pass));
    }
}
