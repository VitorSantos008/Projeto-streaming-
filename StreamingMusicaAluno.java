import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusicaAluno {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        usuario.nome = "Usuário";
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());
            processarOpcao(opcao);
        } while (opcao != 0);

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarMusica(); break;
            case 4: criarPlaylist(); break;
            case 5: gerenciarPlaylists(); break;
            case 6: exibirEstatisticas(); break;
            case 0: break;
            default: System.out.println("❌ Opção inválida!");
        }
    }

    static void cadastrarMusica() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Duração (segundos): ");
        int duracao = Integer.parseInt(scanner.nextLine());

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        Musica m = new Musica();
        m.titulo = titulo;
        m.artista = artista;
        m.duracaoSegundos = duracao;
        m.genero = genero;

        musicas.add(m);
    }

    static void listarMusicas() {
    if (musicas.isEmpty()) {
        System.out.println("Nenhuma música cadastrada.");
        return;
    }

    for (int i = 0; i < musicas.size(); i++) {
        System.out.print((i + 1) + ". ");
        musicas.get(i).exibir();
    }
}
    static void buscarMusica() {
        System.out.print("Buscar: ");
        String busca = scanner.nextLine();

        for (Musica m : musicas) {
            if (m.contemTitulo(busca) || m.contemArtista(busca)) {
                m.exibir();
            }
        }
    }

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        usuario.criarPlaylist(nome);
    }

    static void gerenciarPlaylists() {
        usuario.listarPlaylists();

        System.out.print("Escolha: ");
        int i = Integer.parseInt(scanner.nextLine()) - 1;

        Playlist p = usuario.getPlaylist(i);
        if (p == null) return;

        System.out.println("1. Adicionar música");
        System.out.println("2. Remover música");
        System.out.println("3. Listar músicas");

        int op = Integer.parseInt(scanner.nextLine());

        switch (op) {
            case 1:
                listarMusicas();
                int idx = Integer.parseInt(scanner.nextLine()) - 1;
                p.adicionarMusica(musicas.get(idx));
                break;
            case 2:
                p.listarMusicas();
                int r = Integer.parseInt(scanner.nextLine()) - 1;
                p.removerMusica(r);
                break;
            case 3:
                p.listarMusicas();
                break;
        }
    }

    static void exibirEstatisticas() {
        int total = musicas.size();
        int soma = 0;

        for (Musica m : musicas) {
            soma += m.duracaoSegundos;
        }

        System.out.println("Total: " + total);
        System.out.println("Duração total: " + soma + " segundos");
    }

    static void adicionarMusicasTeste() {
        Musica m1 = new Musica();
        m1.titulo = "Bohemian Rhapsody";
        m1.artista = "Queen";
        m1.duracaoSegundos = 354;
        m1.genero = "Rock";

        musicas.add(m1);
    }
}
