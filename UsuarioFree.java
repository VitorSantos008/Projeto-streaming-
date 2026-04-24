// UsuarioFree.java
public class UsuarioFree extends Usuario {

    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;

        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }

        super.reproduzirMusica(musica);
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de 3 playlists atingido!");
            return;
        }

        super.criarPlaylist(nome);
        System.out.println("✅ Playlist criada!");
    }

    private void exibirAnuncio() {
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
    }
}