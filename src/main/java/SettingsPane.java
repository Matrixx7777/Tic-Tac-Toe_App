import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Reprezentuje kontener, który pokaże po kliknięciu przycisku klasy GamePane i SettingsPane
public class SettingsPane extends Pane{

//  Tutaj stworzyłem wszystkie rzeczy, które zamierzam włożyć do kontenera
    Label labelForBoards = new Label("Game board");
    Label labelForFontSizes = new Label("Font sizes");
    ComboBox boardsComboBox = new ComboBox();
    ComboBox fontSizesComboBox = new ComboBox();
    Button reset = new Button("Reset Default Settings");
    Button back = new Button("Back");

    public SettingsPane(){

//  fontSizesComboBox i tabliceComboBox Tutaj umieściłem opcje wybierane przez użytkownika w dwóch obiektach
        boardsComboBox.getItems().addAll("Board 1", "Board 2", "Board 3", "Board 4");
        fontSizesComboBox.getItems().addAll("Small", "Medium", "Large");

//  SettingsPane Tutaj ustawiłem rozmiar wszystkiego, co dodam do kontenera, który reprezentuje obiekt,
//  który jest tworzony z klasy
        labelForBoards.setTranslateX(80);
        labelForBoards.setTranslateY(120);
        boardsComboBox.setTranslateX(200);
        boardsComboBox.setTranslateY(120);
        labelForFontSizes.setTranslateX(80);
        labelForFontSizes.setTranslateY(180);
        fontSizesComboBox.setTranslateX(200);
        fontSizesComboBox.setTranslateY(200);
        reset.setTranslateX(80);
        reset.setTranslateY(260);
        back.setTranslateX(80);
        back.setTranslateY(320);

//  SettingsPane Tutaj dodałem wszystko, co stworzyłem w kontenerze reprezentowanym przez obiekt,
//  który tworzymy z klasy
        getChildren().add(labelForBoards);
        getChildren().add(boardsComboBox);
        getChildren().add(labelForFontSizes);
        getChildren().add(fontSizesComboBox);
        getChildren().add(reset);
        getChildren().add(back);

//  boardsComboBox Tutaj zdefinowałem, co się stanie, gdy użytkownik zmieni wartość pokazaną w obiekcie
//  AppManager Wątek w klasie favorBoard na podstawie wybranej wartości nazwa obrazu zostanie przekazana
//  do zmiennej statycznej
        boardsComboBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number oldVal, Number newVal) ->{
                    switch ((int) newVal) {
                        case 0:
                            AppManager.preferredBoard = "board_1.png";
                            break;

                        case 1:
                            AppManager.preferredBoard = "board_2.png";
                            break;

                        case 2:
                            AppManager.preferredBoard = "board_3.png";
                            break;

                        case 3:
                            AppManager.preferredBoard = "board_4.png";
                            break;
                    }
                });

//  fontSizesComboBox Tutaj określam, co się stanie, gdy użytkownik zmieni wartość pokazaną w obiekcie
//  Motyw AppManager w klasie preferowany Czcionka na podstawie wybranej wartości, rozmiar czcionki
//  zostanie przekazany do obiektu statycznego, zmiana rozmiaru czcionki wszystkich przycisków, tekstów
//  i pól tekstowych umieszczonych w grze setFont() jako wywołana funkcja

        fontSizesComboBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> or, Number oldVal, Number newVal) -> {

                    String selectedFont = fontSizesComboBox.getSelectionModel().getSelectedItem().toString();
                    int fontsize = 0;

                    switch (selectedFont){
                        case "Small":
                            fontsize = 13;
                            break;

                        case "Medium":
                            fontsize = 15;
                            break;

                        case"Large":
                            fontsize = 17;
                            break;
                    }
                    AppManager.preferredFont = Font.font("Arial", FontWeight.EXTRA_BOLD, fontsize);
                    AppManager.setFont();
                });

//  reset Tutaj zdefiniowałem, co się stanie po kliknięciu przycisku reprezentowanego przez obiekt
//  aby zwrócić wartości domyślne, które zostały umieszczone w kontenerze AppManager w klasie
//  setDefaultSettings() funkcja zostanie wywołana
        reset.setOnAction((Action) ->{
            AppManager.setDefaultSettings();
            boardsComboBox.getSelectionModel();
            fontSizesComboBox.getSelectionModel();
        });

//  back Tutaj zdefiniowałem, co się stanie, gdy przycisk reprezentowany przez obiekt zostanie kliknięty
//  lokalizacja bieżącego kontenera startPane Aby wyświetlić kontener reprezentowany przez obiekt viewPane()
//  zostanie wywołana funkcja statyczna
        back.setOnAction((Action) -> {
            AppManager.viewPane(AppManager.startPane);
        });
    }
}