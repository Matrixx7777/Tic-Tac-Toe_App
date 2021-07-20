import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

//  Po ruruchomieniu programu StartPane, wywołałem kontener.
public class StartPane extends Pane {

//  StartPane Tutaj stworzyłem wszystkie rzeczy, które zamierzam włożyć do kontenera
    Button singlePlayer = new Button("Single player");
    Button multiplayer = new Button("Multi player");
    Button settings = new Button("Single player");
    Button about = new Button("About");
    Button exit = new Button("Exit");

    //  StartPane To użyje do wyświetlenia wyskakującego okienka,
//  gdy użytkownik kliknie przycisk Alert tutaj utworzyłem obiekt z klasy.
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public StartPane(){

//  StartPane Tutaj ustawiłem rozmiary wszystkiego, co dodam do kontenera reprezentowanego przez obiekt,
//  który tworzymy z klasy
        singlePlayer.setPrefSize(250,50);
        multiplayer.setPrefSize(250,50);
        settings.setPrefSize(250,50);
        about.setPrefSize(250,50);
        exit.setPrefSize(250,50);

//  StartPane Tutaj znajduje się wszystko, co dodam do kontenera reprezentowanego przez obiekt,
//  który jest tworzony z klasy
        singlePlayer.setTranslateX(80);
        singlePlayer.setTranslateY(100);
        multiplayer.setTranslateX(80);
        multiplayer.setTranslateY(180);
        settings.setTranslateX(80);
        settings.setTranslateY(100);
        about.setTranslateX(80);
        about.setTranslateY(260);
        exit.setTranslateX(80);
        exit.setTranslateY(340);

//  StartPane Tutaj dodałem wszystko, co stworzyłem w kontenerze reprezentowanym przez obiekt
        getChildren().add(singlePlayer);
        getChildren().add(multiplayer);
        getChildren().add(settings);
        getChildren().add(about);
        getChildren().add(exit);

//  Tutaj zdefinowałem, co się stanie po kliknięciu przycisku reprezentowanego przez obiekt.
//  Lokalizacja bieżącego kontenera singlePlayerPane do wyświetlenia kontenera reprezentowanego przez obiekt
//  viewPane() , zostanie wywołana funkcja statyczna
        singlePlayer.setOnAction((Action) -> AppManager.viewPane(AppManager.singlePlayerPane));

        multiplayer.setOnAction((Action) -> AppManager.viewPane(AppManager.multiPlayerPane));

        settings.setOnAction((Action) -> AppManager.viewPane(AppManager.singlePlayerPane));

        about.setOnAction((Action) ->{
            String a1 = "My name is Dawid Kocik and I have serious plans for the IT industry.\n" +
                    "I would like to join the IT team and implement a lot of new projects. Programming is my passion.\n" +
                    "I am a beginner programmer and I know that I still have to learn a lot of new things.\n" +
                    "If I got a job as a junior java developer it would be my dream come true.";

            alert.setTitle("About me");
            alert.setHeaderText("About me");
            alert.setHeaderText(a1);
            alert.showAndWait();
        });

        exit.setOnAction((Action) -> System.exit(0));
    }
}
