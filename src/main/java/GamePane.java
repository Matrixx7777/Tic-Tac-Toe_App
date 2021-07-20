import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GamePane extends  Pane {

    Label firstPlayName = new Label();
    Label secondPlayName = new Label();
    Label firstPlayerScore = new Label("0");
    Label secondPlayerScore = new Label("0");
    Label currentPlayerSymbolLabel = new Label();
    GridPane gridPane = new GridPane();
    Button[] boardButtons = new Button[3 * 3];
    Button back = new Button("Back");
    Button newGame = new Button("New Game");
    ImageView boardBackground = new ImageView();

    Player currentPlayer = Player.X;
    Player winner;
    Button[] winnerSquares;

    Random random = new Random();

    private String winnerStyle = "-fx-background-color: yellow;";

    private void colorBackgroundWinnerButtons() {
        for (Button button : winnerSquares) {
            button.setStyle(winnerStyle);
        }
    }

    private void setDisabledAllButtons(boolean disabled) {
        for (Button button : boardButtons) {
            button.setMouseTransparent(disabled);
        }
    }

    private void createGameBoard() {
        gridPane.getChildren().clear();
        for (int i = 0; i < boardButtons.length; i++) {
            boardButtons[i] = new Button();

            boardButtons[i].setPrefSize(90, 90);
            boardButtons[i].setFocusTraversable(false);

            GridPane.setMargin(boardButtons[i], new Insets(5));
            boardButtons[i].setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));

            gridPane.add(boardButtons[i], i % 3, Math.floorDiv(i,  3));

            boardButtons[i].addEventHandler(ActionEvent.ACTION, this::actionPerformed);
        }
    }

    private boolean doesGameEnded() {
        String a0 = boardButtons[0].getText();
        String a1 = boardButtons[1].getText();
        String a2 = boardButtons[2].getText();
        String a3 = boardButtons[3].getText();
        String a4 = boardButtons[4].getText();
        String a5 = boardButtons[5].getText();
        String a6 = boardButtons[6].getText();
        String a7 = boardButtons[7].getText();
        String a8 = boardButtons[8].getText();

        //Rows
        if (a0.equals(a1) && a0.equals(a2) && !a0.equals("")) {
            winner = currentPlayer;
            winnerSquares = new Button[]{boardButtons[0], boardButtons[1], boardButtons[2]};
            colorBackgroundWinnerButtons();
            return true;
        }

        if (a3.equals(a4) && a3.equals(a5) && !a3.equals("")) {
            winner = currentPlayer;
            winnerSquares = new Button[]{boardButtons[3], boardButtons[4], boardButtons[5]};
            colorBackgroundWinnerButtons();
            return true;
        }

        if (a6.equals(a7) && a6.equals(a8) && !a6.equals("")) {
            winner = currentPlayer;
            winnerSquares = new Button[]{boardButtons[6], boardButtons[7], boardButtons[8]};
            colorBackgroundWinnerButtons();
            return true;
        }

        //Columns
        if (a0.equals(a3) && a0.equals(a6) && !a0.equals("")) {
            winner = currentPlayer;
            winnerSquares = new Button[]{boardButtons[0], boardButtons[3], boardButtons[6]};
            colorBackgroundWinnerButtons();
            return true;
        }

        if (a1.equals(a4) && a1.equals(a7) && !a1.equals("")) {
            winner = currentPlayer;
            winnerSquares = new Button[]{boardButtons[1], boardButtons[4], boardButtons[7]};
            colorBackgroundWinnerButtons();
            return true;
        }

        if (a2.equals(a5) && a2.equals(a8) && !a2.equals("")) {
            winner = currentPlayer;
            winnerSquares = new Button[]{boardButtons[2], boardButtons[5], boardButtons[8]};
            colorBackgroundWinnerButtons();
            return true;
        }

        //Diagonals
        if (a0.equals(a4) && a0.equals(a8) && !a0.equals("")) {
            winner = currentPlayer;
            winnerSquares = new Button[]{boardButtons[0], boardButtons[4], boardButtons[8]};
            colorBackgroundWinnerButtons();
            return true;
        }

        if (a2.equals(a4) && a2.equals(a6) && !a2.equals("")) {
            winner = currentPlayer;
            winnerSquares = new Button[]{boardButtons[2], boardButtons[4], boardButtons[6]};
            colorBackgroundWinnerButtons();
            return true;
        }

        //Tie
        boolean allButtonsClicked = true;
        for (Button boardButton : boardButtons) {
            if (boardButton.getText().equals("")) {
                allButtonsClicked = false;
                break;
            }
        }

        if (allButtonsClicked) {
            winner = null;
            winnerSquares = new Button[]{};
            return true;
        }

        return false;
    }

    private void startNewGame(String firstPlayerName, String secondPlayerName, boolean resetScore) {
        firstPlayName.setText(firstPlayerName);
        secondPlayName.setText(secondPlayerName);
        firstPlayerScore.setText(resetScore ? "0" : firstPlayerScore.getText());
        secondPlayerScore.setText(resetScore ? "0" : secondPlayerScore.getText());
        currentPlayer = Player.X;
        winner = null;
        winnerSquares = null;

        for (Button button : boardButtons) {
            button.setMouseTransparent(false);
            button.setText("");
            button.setStyle("-fx-background-color: none; -fx-cursor: hand;");
        }
        updateCurrentPlayerSymbolLabel();
    }

    private void resetCurrentPlayerSymbol() {
        currentPlayer = Player.X;
        updateCurrentPlayerSymbol();
    }

    private void updateCurrentPlayerSymbol() {
        currentPlayer = currentPlayer.nextPlayer();
        updateCurrentPlayerSymbolLabel();
    }

    private void updateCurrentPlayerSymbolLabel() {
        currentPlayerSymbolLabel.setText(currentPlayer.getSymbol());
        currentPlayerSymbolLabel.setTextFill(currentPlayer.getColor());
    }

    private void actionPerformed(ActionEvent e) {
        Button buttonClicked = (Button) e.getSource();

        if (buttonClicked.isMouseTransparent()) return;

        buttonClicked.setMouseTransparent(true);
        buttonClicked.setText(currentPlayer.getSymbol());
        buttonClicked.setTextFill(currentPlayer.getColor());

        if (doesGameEnded()) {
            if (Player.X.equals(winner)) {
                firstPlayerScore.setText(String.valueOf(Integer.parseInt(firstPlayerScore.getText()) + 1));
            } else if (Player.O.equals(winner)) {
                secondPlayerScore.setText(String.valueOf(Integer.parseInt(secondPlayerScore.getText()) + 1));
            }

            setDisabledAllButtons(true);
            resetCurrentPlayerSymbol();
            return;
        }

        updateCurrentPlayerSymbol();

        if (AppManager.challengeComputer) {
            List<Integer> freeIdxList = new ArrayList<>();
            for (int i = 0; i < boardButtons.length; i++) {
                if (boardButtons[i].getText().equals("")) {
                    freeIdxList.add(i);
                }
            }

            int randomNumber = random.nextInt(freeIdxList.size());
            int randomIdx = freeIdxList.get(randomNumber);
            boardButtons[randomIdx].setMouseTransparent(true);
            boardButtons[randomIdx].setText(currentPlayer.getSymbol());
            boardButtons[randomIdx].setTextFill(currentPlayer.getColor());

            if (doesGameEnded()) {
                if (Player.X.equals(winner)) {
                    firstPlayerScore.setText(String.valueOf(Integer.parseInt(firstPlayerScore.getText()) + 1));
                } else if (Player.O.equals(winner)) {
                    secondPlayerScore.setText(String.valueOf(Integer.parseInt(secondPlayerScore.getText()) + 1));
                }

                setDisabledAllButtons(true);
                resetCurrentPlayerSymbol();
                return;
            }

            updateCurrentPlayerSymbol();
        }
    }

    public GamePane() {
        init("Player1", "Player2");
    }

    public void init(String name1, String name2) {
        firstPlayName.setPrefSize(150, 30);
        secondPlayName.setPrefSize(150, 30);
        firstPlayerScore.setPrefSize(150, 30);
        secondPlayerScore.setPrefSize(150, 30);
        currentPlayerSymbolLabel.setPrefSize(150, 30);
        gridPane.setPrefSize(300, 300);
        newGame.setPrefSize(140, 30);

        firstPlayName.setTranslateY(10);
        secondPlayName.setTranslateX(250);
        secondPlayName.setTranslateY(10);
        firstPlayerScore.setTranslateY(40);
        secondPlayerScore.setTranslateX(250);
        secondPlayerScore.setTranslateY(40);
        currentPlayerSymbolLabel.setTranslateX(120);
        currentPlayerSymbolLabel.setTranslateY(25);
        boardBackground.setFitWidth(300);
        boardBackground.setFitHeight(300);
        boardBackground.setTranslateX(45);
        boardBackground.setTranslateY(105);
        gridPane.setTranslateX(45);
        gridPane.setTranslateY(105);
        gridPane.setGridLinesVisible(true);
        back.setPrefSize(140, 30);
        back.setTranslateX(20);
        back.setTranslateY(455);
        newGame.setTranslateX(230);
        newGame.setTranslateY(455);

        firstPlayName.setAlignment(Pos.CENTER);
        secondPlayName.setAlignment(Pos.CENTER);
        firstPlayerScore.setAlignment(Pos.CENTER);
        secondPlayerScore.setAlignment(Pos.CENTER);
        currentPlayerSymbolLabel.setAlignment(Pos.CENTER);

        createGameBoard();

        getChildren().clear();
        getChildren().add(firstPlayName);
        getChildren().add(secondPlayName);
        getChildren().add(firstPlayerScore);
        getChildren().add(secondPlayerScore);
        getChildren().add(currentPlayerSymbolLabel);
        getChildren().add(gridPane);
        getChildren().add(boardBackground);
        getChildren().add(back);
        getChildren().add(newGame);

        startNewGame(name1, name2, true);

        back.setOnAction(ignoredAction -> AppManager.viewPane(AppManager.challengeComputer ? AppManager.singlePlayerPane : AppManager.multiPlayerPane));
        newGame.setOnAction(ignoredAction -> startNewGame(name1, name2, false));
    }
}

