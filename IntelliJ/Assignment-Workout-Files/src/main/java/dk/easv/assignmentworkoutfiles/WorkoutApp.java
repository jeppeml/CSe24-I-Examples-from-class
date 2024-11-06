package dk.easv.assignmentworkoutfiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkoutApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/easv/assignmentworkoutfiles/gui/views/WorkoutUserView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Workout with INSANE GAINS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}