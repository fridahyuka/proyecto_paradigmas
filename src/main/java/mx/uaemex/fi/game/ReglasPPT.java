package mx.uaemex.fi.game;

public class ReglasPPT implements ReglasJuego {

    @Override
    public Resultado determinarGanador(Movimiento jugador, Movimiento oponente) {

        if (jugador == oponente) {
            return Resultado.EMPATE;
        }

        switch (jugador) {
            case PIEDRA:
                return (oponente == Movimiento.TIJERA || oponente == Movimiento.LAGARTO)
                        ? Resultado.GANASTE
                        : Resultado.PERDISTE;

            case PAPEL:
                return (oponente == Movimiento.PIEDRA || oponente == Movimiento.SPOCK)
                        ? Resultado.GANASTE
                        : Resultado.PERDISTE;

            case TIJERA:
                return (oponente == Movimiento.PAPEL || oponente == Movimiento.LAGARTO)
                        ? Resultado.GANASTE
                        : Resultado.PERDISTE;

            case LAGARTO:
                return (oponente == Movimiento.SPOCK || oponente == Movimiento.PAPEL)
                        ? Resultado.GANASTE
                        : Resultado.PERDISTE;

            case SPOCK:
                return (oponente == Movimiento.TIJERA || oponente == Movimiento.PIEDRA)
                        ? Resultado.GANASTE
                        : Resultado.PERDISTE;

            default:
                return Resultado.EMPATE;
        }
    }
}
