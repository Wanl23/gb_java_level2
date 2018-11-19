package lesson7.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.DataOutputStream;
import java.io.IOException;

public class MiniStage extends Stage {

    static String nickname;
    DataOutputStream out;

    public MiniStage(String nick, DataOutputStream out) throws IOException {

        this.out = out;
        this.nickname = nick;

        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("SecondWindow.fxml"));
        setTitle("Chat with " + nick);
        Scene scene = new Scene(root, 250, 50);
        setScene(scene);
    }
}
