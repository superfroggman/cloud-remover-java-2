package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
    public static Stage primaryStage;

    @FXML
    Label selectedfile;
    @FXML
    Label title;

    public void initialize() {
        title.setFont(Font.font("Sans-Serif", 35));
    }
    public void buttonClicked(ActionEvent actionEvent) throws Exception {
        System.out.println("button clicked");
        FileChooser _fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Videos (mp4, mkv, mov, gif)", "*.gif", "*.mp4", "*.mkv", "*.mov");
        _fileChooser.getExtensionFilters().add(extFilter);
        File file = _fileChooser.showOpenDialog(primaryStage);
        System.out.println(file.getAbsolutePath());
        selectedfile.setText("Selected file: " + file.getName());

        //Backend.ffmpegTest();
    }

}
