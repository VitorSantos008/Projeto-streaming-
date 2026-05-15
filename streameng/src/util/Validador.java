package util;

public class Validador {

    public static boolean textoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean numeroNegativo(int numero) {
        return numero < 0;
    }
}