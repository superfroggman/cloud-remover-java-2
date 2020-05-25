package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {
    public static Stage primaryStage;
    public static File file = null;

    @FXML
    Label selectedfile;
    @FXML
    Label title;
    @FXML
    Label percentagerunning;
    @FXML
    GridPane primary;
    @FXML
    GridPane save;
    @FXML
    GridPane startscene;
    @FXML
    ImageView currently;
    @FXML
    ImageView finishview;
    @FXML
    GridPane running;
    @FXML
    Button choosebutton;

    /**
     * Sets the color and font, also hides to scenes not in use.
     */
    public void initialize() {
        title.setFont(Font.font("Sans-Serif", 35));
        primary.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        save.setVisible(false);
        running.setVisible(false);
    }

    /**
     * Shows the file chooser and sets the selected file to variable.
     * Extension filter for allowed formats is also in use here.
     * @param actionEvent
     * @throws Exception
     */
    public void buttonClicked(ActionEvent actionEvent) throws Exception {
        FileChooser _fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Videos (mp4, mkv, mov, gif)", "*.gif", "*.mp4", "*.mkv", "*.mov");
        _fileChooser.getExtensionFilters().add(extFilter);
        file = _fileChooser.showOpenDialog(primaryStage);

        System.out.println(file.getAbsolutePath());
        choosebutton.setText("Selected file: " + file.getName());
    }
    public void saveButtonClicked(ActionEvent actionEvent) {

        FileChooser saver = new FileChooser();
        FileChooser.ExtensionFilter saveFilter = new FileChooser.ExtensionFilter("Image (*.png)", "*.png");
        saver.getExtensionFilters().add(saveFilter);
        File save = saver.showSaveDialog(primaryStage);
    }

    public void runButtonClicked(ActionEvent actionEvent) throws FileNotFoundException {
        if (!(file == null)) {
            percentagerunning.setText("29,7363% finished!");
            currently.setImage(new Image("/pic.png"));
            currently.setVisible(true);
            currently.setFitWidth(200);
            currently.setFitHeight(175);
            startscene.setVisible(false);
            running.setVisible(true);

        }{
            selectedfile.setText("Choose file first!");
        }
    }

    public void temp(ActionEvent actionEvent) {
        running.setVisible(false);
        save.setVisible(true);
        finishview.setImage(new Image("/pic.png"));
        currently.setFitWidth(200);
        currently.setFitHeight(175);
    }
}
