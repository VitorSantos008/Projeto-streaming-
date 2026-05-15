package modelo;

import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {

    private String genero;

    public PlaylistAutomatica(String nome, String genero) {
        super(nome);
        this.genero = genero;
    }

    public void gerarPlaylist(ArrayList<Musica> catalogo) {

        musicas.clear();

        for (Musica musica : catalogo) {

            if (musica.getGenero().equalsIgnoreCase(genero)) {
                musicas.add(musica);
            }
        }
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo playlist automática: " + nome);
    }
}