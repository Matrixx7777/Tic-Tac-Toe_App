import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

// Single Player  Reprezentuje kontener, który pojawi się po kliknięciu klasy SinglePlayerPane
// na przycisku komputera
public class SinglePlayerPane extends Pane{

    // Tutaj stworzyłem wszystkie rzeczy, które zamierzam włożyć do kontenera
    Label labelPlayerName = new Label("Player name");
    TextField textField = new TextField("player");
    Button start = new Button("Start");
    Button back = new Button("Back");

    public SinglePlayerPane(){

//  SinglePlayerPane Tutaj ustawiłem rozmiar wszystkiego, co dodam do kontenera reprezentowanego przez obiekt,
//  który tworzymy z klasy
        labelPlayerName.setPrefSize(100,30);
        textField.setPrefSize(130,30);
        start.setPrefSize(250,50);
        back.setPrefSize(250,50);

//  SinglePlayerPane Tutaj znajduje się wszystko, co dodam do kontenera reprezentowanego przez obiekt,
//  który tworzymy z klasy
        labelPlayerName.setTranslateX(80);
        labelPlayerName.setTranslateY(170);
        textField.setTranslateX(190);
        textField.setTranslateY(170);
        start.setTranslateX(80);
        start.setTranslateY(220);
        back.setTranslateX(80);
        back.setTranslateY(280);

//  SinglePlayerPane Tutaj znajduje się wszystko, co dodam do kontenera reprezentowanego przez obiekt,
//  który tworzymy z klasy
        getChildren().add(labelPlayerName);
        getChildren().add(textField);
        getChildren().add(start);
        getChildren().add(back);

//  start Tutaj zdefinowałem, co się stanie po kliknięciu przycisku reprezentowanego przez obiekt
//  z 0 jako początkowym wynikiem, a okno gry komputerowej przekaże nazwę wprowadzoną przez użytkownika,
//  jako nazwę gracza, która pojawia się w kontenerze gamePane Wtedy obrazek tła wybrany przez użytkownika
//  lub obrazek wybrany domyślnie jako tło gry zostanie umieszczony w kontenerze lokalizacja bieżącego kontenera
//  gamePane, aby wyświetlić kontener reprezentowany przez obiekt viewPane(), na końcu którego zostanie wywołana
//  funkcja statyczna
        start.setOnAction(ignoredAction -> {
            AppManager.gamePane.init(textField.getText(), "Computer");
            AppManager.challengeComputer = true;
            AppManager.viewPane(AppManager.gamePane);
        });

//  Wskazuje, że zostanie rozegrany z komputerem AppManager w klasie challengeComputer dla zmiennej
//  stałej true przekazujemy wartość
        back.setOnAction(ignoredAction -> AppManager.viewPane(AppManager.startPane));
    }
}