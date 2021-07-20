import javafx.scene.paint.Color;

public enum Player {
    X("X", Color.BLACK),
    O("O", Color.GREEN);

    private String symbol;
    private Color color;

    Player(String symbol, Color color) {
        this.symbol = symbol;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public Color getColor() {
        return color;
    }

    public Player nextPlayer() {
        if (this.symbol.equals("X")) return O;
        else return X;
    }
}
