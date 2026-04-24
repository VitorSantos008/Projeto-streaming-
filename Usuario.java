// Usuario.java
import java.util.ArrayList;

public class Usuario {

    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historicoReproducao;

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email inválido!");
        }
        this.email = email.trim();
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

    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void exibirHistorico() {
        if (historicoReproducao.isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }

        for (Musica m : historicoReproducao) {
            m.exibir();
        }
    }
}