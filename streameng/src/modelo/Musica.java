package modelo;

import servico.Reproduzivel;

public class Musica implements Reproduzivel {

    private String titulo;
    private String artista;
    private String genero;
    private int duracao;

    public Musica(String titulo, String artista, String genero, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo: " + titulo);
    }

    @Override
    public void pausar() {
        System.out.println("Música pausada.");
    }

    @Override
    public void parar() {
        System.out.println("Música parada.");
    }

    @Override
    public int getDuracaoTotal() {
        return duracao;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + genero + ")";
    }
}