import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

// Multi Player Reprezentuje kontener, który pokaże po kliknięciu przycisku gry między dwiema osobami
// na tym samym urządzeniu Klasa MultiPlayerPane
public class MultiPlayerPane extends Pane{

    // Tutaj stworzyłem wszystkie rzeczy, które zamierzam włożyć do kontenera
    Label labelPlayerX = new Label("Player X");
    Label labelPlayerO = new Label("Player Y");
    TextField firstPlayerName = new TextField("player 1");
    TextField secondPlayerName = new TextField("player 2");
    Button start = new Button("Start");
    Button back = new Button("Back");


    public MultiPlayerPane(){

//  MultiPlayerPane Tutaj ustawiłem rozmiar wszystkiego co dodam do kontenera reprezentowanego przez obiekt,
//  który tworzymy z klasy
        labelPlayerX.setPrefSize(70,30);
        firstPlayerName.setPrefSize(160,30);
        labelPlayerO.setPrefSize(70,30);
        secondPlayerName.setPrefSize(160,30);
        start.setPrefSize(250,50);
        back.setPrefSize(250,50);

//  MultiPlayerPane Tutaj znajduje się wszystko, co dodamy do kontenera reprezentowanego przez obiekt,
//  który tworzymy z klasy
        labelPlayerX.setTranslateX(80);
        labelPlayerX.setTranslateY(130);
        firstPlayerName.setTranslateX(160);
        firstPlayerName.setTranslateY(130);
        labelPlayerO.setTranslateX(80);
        labelPlayerO.setTranslateY(190);
        secondPlayerName.setTranslateX(160);
        secondPlayerName.setTranslateY(190);
        start.setTranslateX(80);
        start.setTranslateY(250);
        back.setTranslateX(80);
        back.setTranslateY(310);

//  MultiPlayerPane Tutaj dodaliśmy wszystko, co stworzyliśmy w kontenerze reprezentowanym przez obiekt,
//  który tworzymy z klasy
        getChildren().add(labelPlayerX);
        getChildren().add(firstPlayerName);
        getChildren().add(labelPlayerO);
        getChildren().add(secondPlayerName);
        getChildren().add(start);
        getChildren().add(back);

//  start Tutaj zdefinowałem, co się stanie po kliknięciu przycisku reprezentowanego przez obiekt
//  Z wartością ustawioną na 0 jako początkowym wynikiem dla obu graczy w gamePane, nazwy graczy,
//  do których wejdą, zostaną przekazane do kontenera gamePane Wtedy wybrany przez nich obraz tła lub obraz
//  wybrany domyślnie zostanie umieszczony jako tło gry w kontenerze lokalizacja bieżącego kontenera gamePane,
//  aby wyświetlić kontener reprezentowany przez obiekt viewPane(), na końcu którego zostanie wywołana
//  funkcja statyczna
        start.setOnAction(ignoredAction -> {
//  Wskazuje, że AppManager w klasie challengeComputer dla zmiennej stałej false nie będzie odtwarzany
//  z komputerem, któremu przekazujemy wartość
            AppManager.challengeComputer = false;
            AppManager.gamePane.init(firstPlayerName.getText(), secondPlayerName.getText());
            AppManager.viewPane(AppManager.gamePane);
        });

//  back Tutaj zdefinowałem, co się stanie, gdy przycisk reprezentowany przez obiekt zostanie kliknięty
//  lokalizacja bieżącego kontenera startPane Aby wyświetlić kontener reprezentowany przez obiekt viewPane()
//  zostanie wywołana funkcja statyczna
        back.setOnAction(ignoredAction -> AppManager.viewPane(AppManager.startPane));
    }
}

