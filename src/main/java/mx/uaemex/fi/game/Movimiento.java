package mx.uaemex.fi.game;

public enum Movimiento {

    PIEDRA("R"),
    PAPEL("P"),
    TIJERA("S"),
    LAGARTO("L"),
    SPOCK("Sp");

    private String symbol;

    Movimiento(String symbol) {

        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Movimiento fromSymbol(String symbol) {
        for (Movimiento m : values()) {
            if (m.symbol.equals(symbol)) {
                return m;
            }
        }
        return null;
    }
}
