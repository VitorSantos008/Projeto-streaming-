import java.util.ArrayList;

public class Playlist {

    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist() {
        this.musicas = new ArrayList<>();
    }

    public Playlist(String nome) {
        setNome(nome);
        this.musicas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da playlist inválido!");
        }
        this.nome = nome.trim();
    }

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            System.out.println("Música inválida!");
            return;
        }
        musicas.add(musica);
    }

    public void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            musicas.remove(indice);
        } else {
            System.out.println("Índice inválido!");
        }
    }

    public void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.getDuracaoSegundos();
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return musicas.size();
    }
}