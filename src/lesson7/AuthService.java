package lesson7;

import java.sql.*;
import java.util.ArrayList;

public class AuthService {

    private static Connection connection;
    private static Statement statement;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass){
        String sql = String.format("SELECT nickname, password FROM main" +
                " WHERE login = '%s'", login);

        int myHash = pass.hashCode();

        ResultSet rs = null;
        try{
            rs = statement.executeQuery(sql);
            if(rs.next()){
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);

                if(myHash == dbHash){
                    return nick;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void addUser(String login, String pass, String nick){
        String sql = String.format("INSERT INTO main (login, password, nickname)"
                + "VALUES ('%s', '%s', '%s')", login, pass.hashCode(), nick);
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addUserToBL(String nickToBL, String forWhomBlocking){
        if(nickToBL.equals(forWhomBlocking)){
            return false;
        }

        String sql = String.format("INSERT INTO BLACKLIST (nick, blacklist)" +
            "VALUES('%s', '%s')", forWhomBlocking, nickToBL);

        try{
            statement.execute(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkBL(String nickFrom, String nickTo){

        String sql = String.format("select * FROM BLACKLIST \n " +
                "WHERE nick = '%s'", nickTo);

        ResultSet rs = null;

        try{
            rs = statement.executeQuery(sql);
            if(rs.next()){
                String nickInBL = rs.getString(2);

                if(nickFrom.equals(nickInBL)){
                    return true;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
