package lesson7.client;

import javafx.concurrent.Task;

public class TimerToClose extends Task<String> {

    private int timeOut = 10;

    @Override
    public String call() {
        for (int i = 0; i < timeOut; i++) {
            if (Main.isAuthorised == true) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == timeOut - 1) {
                System.exit(0);
            }
        }
        return null;
    }
}