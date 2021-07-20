import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

// AppManager static stworzyłem go, aby łatwo przekazywać wspólne wartości między kontenerami gier, więc wszystko
// w nim zdefinowałem jako klasę AppManager
public class AppManager {

    // Tutaj stworzyłem obiekt każdej klasy reprezentujący przygotowany wcześniejszy kontener
    static StartPane startPane = new StartPane();
    static SinglePlayerPane singlePlayerPane = new SinglePlayerPane();
    static MultiPlayerPane multiPlayerPane = new MultiPlayerPane();
    static SettingsPane settingsPane = new SettingsPane();
    static GamePane gamePane = new GamePane();

    // SettingsPane Będę przechowywać nazwę obrazu tła gry, który użytkownik może zmienić ze zmiennej
// kontenera preferredBoard
    static String preferredBoard;

    // SettingsPane Będziemy przechowywać rozmiar czcionki każdego przycisku, tekstu i pola tekstowego,
// które dodamy w grze, które użytkownik może zmienić z kontenera obiektu PreferredFont
    static Font preferredFont;

    // Wskazuje, że komputer SinglePlayerPane umieszczony w kontenerze startowym będzie odtwarzany po kliknięciu
// przycisku true zachowamy wartość zmiennej challengeComputer
    static boolean challengeComputer;

    // pane Poniższa funkcja służy do ukrywania dowolnego okna, które jest aktualnie wyświetlane w oknie
// i wyświetlania tylko kontenera, który do niego przekazujemy w miejscu parametru
    public static void viewPane(Pane pane){
        startPane.setVisible(false);
        singlePlayerPane.setVisible(false);
        multiPlayerPane.setVisible(false);
        settingsPane.setVisible(false);
        gamePane.setVisible(false);

        pane.setVisible(true);
    }

    //  settingsPane Poniższa funkcja, której używam, aby umieścić domyślne opcje, które można zmienić w kontenerze
    public static void setDefaultSettings(){

//  fontSizesComboBox i druga opcja w obiekcie boardsComboBox tutaj można powiedzieć, że zostanie wybrana
//  pierwsza opcja w obiekcie
        settingsPane.boardsComboBox.getSelectionModel().selectFirst();
        settingsPane.fontSizesComboBox.getSelectionModel().select(1);

//  favorFont do zmiany rozmiaru czcionki każdego przycisku, tekstu i pola tekstowego umieszczonego w grze
//  względem wartości obiektu setFont() tutaj wywołałem funkcję
        setFont();
    }

    // preferredFont Poniższej funkcji użyłem do określenia rozmiaru czcionki każdego przycisku, tekstu i pola tekstowego
    // umieszczonego w grze w stosunku do wartości obiektu
    public static void setFont(){

        //startPane
        startPane.singlePlayer.setFont(preferredFont);
        startPane.multiplayer.setFont(preferredFont);
        startPane.settings.setFont(preferredFont);
        startPane.about.setFont(preferredFont);
        startPane.exit.setFont(preferredFont);

        //singlePlayerPane
        singlePlayerPane.labelPlayerName.setFont(preferredFont);
        singlePlayerPane.textField.setFont(preferredFont);
        singlePlayerPane.start.setFont(preferredFont);
        singlePlayerPane.back.setFont(preferredFont);

        //multiPlayerPane
        multiPlayerPane.labelPlayerX.setFont(preferredFont);
        multiPlayerPane.labelPlayerO.setFont(preferredFont);
        multiPlayerPane.firstPlayerName.setFont(preferredFont);
        multiPlayerPane.secondPlayerName.setFont(preferredFont);
        multiPlayerPane.start.setFont(preferredFont);
        multiPlayerPane.back.setFont(preferredFont);

        //gamePane
        gamePane.firstPlayName.setFont(preferredFont);
        gamePane.secondPlayName.setFont(preferredFont);
        gamePane.firstPlayerScore.setFont(preferredFont);
        gamePane.secondPlayerScore.setFont(preferredFont);
        gamePane.currentPlayerSymbolLabel.setFont(preferredFont);
        gamePane.newGame.setFont(preferredFont);
        gamePane.back.setFont(preferredFont);

        //settingsPane
        settingsPane.labelForBoards.setFont(preferredFont);
        settingsPane.labelForFontSizes.setFont(preferredFont);
        settingsPane.reset.setFont(preferredFont);
        settingsPane.back.setFont(preferredFont);

//  Aby ustawić  setStyle() to one nie mają funkcji rozmiaru czcionki, więc użyłem obiektów
//  fontSizesComboBox i boardsComboBox
        settingsPane.boardsComboBox.setStyle(
                "-fx-font-family:" + preferredFont.getName() + ";"
                        +"-fx-font-size: " + preferredFont.getSize() +"px;"
                        +"-fx-font-weight: bold;"
        );

        settingsPane.fontSizesComboBox.setStyle(
                "-fx-font-family:" + preferredFont.getName() + ";"
                        +"-fx-font-size: " + preferredFont.getSize() +"px;"
                        +"-fx-font-weight: bold;"
        );
    }
}

