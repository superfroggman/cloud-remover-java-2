package sample;

import javafx.event.ActionEvent;

public class Controller {

    public void buttonClicked(ActionEvent actionEvent) throws Exception {
        System.out.println("button clicked");
        Backend.ffmpegTest();
    }
}
