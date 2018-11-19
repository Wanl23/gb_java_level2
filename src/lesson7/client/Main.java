package lesson7.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static boolean isAuthorised = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chat");
        Scene scene = new Scene(root, 500, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
        Thread t = new Thread(new TimerToClose());
        t.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
