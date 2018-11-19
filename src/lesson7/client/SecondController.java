package lesson7.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.DataOutputStream;
import java.io.IOException;

public class SecondController {


    @FXML
    TextField textFieldPersonal;

    public void sendWhisper(ActionEvent actionEvent) {
        DataOutputStream out = ((MiniStage)textFieldPersonal.getScene().getWindow()).out;
        String nick = ((MiniStage)textFieldPersonal.getScene().getWindow()).nickname;

        try {
            out.writeUTF("/w " +  nick + " " + textFieldPersonal.getText());
            textFieldPersonal.requestFocus();
            textFieldPersonal.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
