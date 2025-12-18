package mx.uaemex.fi.game;

public enum Resultado {
    GANASTE('W'),
    PERDISTE('L'),
    EMPATE('D');

    private char symbol;

    Resultado(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static Resultado fromSymbol(char symbol) {
        for (Resultado m : values()) {
            if (m.symbol == symbol) {
                return m;
            }
        }
        return null;
    }

}
