// UsuarioPremium.java
import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    private String tipoPlano;
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String tipoPlano) {
        super(nome, email);
        this.tipoPlano = tipoPlano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("🎧 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void baixarMusica(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("⬇ Música baixada!");
        } else {
            System.out.println("ℹ Música já baixada.");
        }
    }

    public void listarMusicasBaixadas() {
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }

        for (Musica m : musicasBaixadas) {
            m.exibir();
        }
    }
}