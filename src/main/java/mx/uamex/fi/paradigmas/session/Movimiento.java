package mx.uamex.fi.paradigmas.session;

public enum Movimiento {
    PIEDRA('P'),
    PAPEL('L'),
    TIJERAS('T');

    private char simbolo;

    Movimiento(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }
}
