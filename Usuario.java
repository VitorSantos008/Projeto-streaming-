import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Playlist> playlists;

    public Usuario() {
        this.playlists = new ArrayList<>();
    }

    public Usuario(String nome) {
        setNome(nome);
        this.playlists = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome = nome.trim();
    }

    public void criarPlaylist(String nome) {
        Playlist p = new Playlist(nome);
        playlists.add(p);
    }

    public Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    public void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
            return;
        }

        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
    }
}