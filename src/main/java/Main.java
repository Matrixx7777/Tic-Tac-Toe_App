import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) {
        AppManager.setDefaultSettings();
        Pane root = new Pane();
        root.getChildren().add(AppManager.startPane);
        root.getChildren().add(AppManager.singlePlayerPane);
        root.getChildren().add(AppManager.multiPlayerPane);
        root.getChildren().add(AppManager.settingsPane);
        root.getChildren().add(AppManager.gamePane);
        AppManager.viewPane(AppManager.startPane);
        Scene scene = new Scene(root, 380, 500);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

