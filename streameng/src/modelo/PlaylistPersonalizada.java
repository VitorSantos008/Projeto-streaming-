package modelo;

public class PlaylistPersonalizada extends Playlist {

    public PlaylistPersonalizada(String nome) {
        super(nome);
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo playlist personalizada: " + nome);
    }
}