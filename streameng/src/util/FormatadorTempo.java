package util;

public class FormatadorTempo {

    public static String formatar(int segundos) {

        int minutos = segundos / 60;
        int segundosRestantes = segundos % 60;

        return minutos + "min " + segundosRestantes + "s";
    }
}