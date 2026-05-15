package modelo;

import java.util.ArrayList;
import servico.Reproduzivel;

public class Playlist implements Reproduzivel {

    protected String nome;
    protected ArrayList<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    public void removerMusica(Musica musica) {
        musicas.remove(musica);
    }

    public void listarMusicas() {

        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.println((i + 1) + ". " + musicas.get(i));
        }
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo playlist: " + nome);
    }

    @Override
    public void pausar() {
        System.out.println("Playlist pausada.");
    }

    @Override
    public void parar() {
        System.out.println("Playlist parada.");
    }

    @Override
    public int getDuracaoTotal() {

        int total = 0;

        for (Musica musica : musicas) {
            total += musica.getDuracao();
        }

        return total;
    }
}