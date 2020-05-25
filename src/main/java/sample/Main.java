package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
<<<<<<< Updated upstream
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
=======
        primaryStage.setTitle("Cloud Remover");
        primaryStage.setScene(new Scene(root, 500, 375));
        primaryStage.show();
        //Backend.customizedCopyPastedThing();
>>>>>>> Stashed changes
    }


    public static void main(String[] args) {
        launch(args);
    }


}
