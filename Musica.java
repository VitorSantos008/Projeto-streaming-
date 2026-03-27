public class Musica {

    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;

    void exibir() {
        System.out.println(titulo + " | " + artista + " | " + getDuracaoFormatada() + " | " + genero);
    }

    String getDuracaoFormatada() {
        return String.format("%d:%02d", duracaoSegundos / 60, duracaoSegundos % 60);
    }

    boolean contemTitulo(String busca) {
        return titulo.toLowerCase().contains(busca.toLowerCase());
    }

    boolean contemArtista(String busca) {
        return artista.toLowerCase().contains(busca.toLowerCase());
    }
}