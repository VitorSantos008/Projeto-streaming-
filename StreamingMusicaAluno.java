import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusicaAluno {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario("Usuário");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (Exception e) {
                System.out.println("Entrada inválida!");
                opcao = -1;
            }
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
            case 0: System.out.println("Encerrando..."); break;
            default: System.out.println("Opção inválida!");
        }
    }

    static void cadastrarMusica() {
        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Artista: ");
            String artista = scanner.nextLine();

            System.out.print("Duração (segundos): ");
            int duracao = Integer.parseInt(scanner.nextLine());

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            Musica m = new Musica(titulo, artista, duracao, genero);
            musicas.add(m);

            System.out.println("Música cadastrada!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
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

        boolean encontrou = false;

        for (Musica m : musicas) {
            if (m.contemTitulo(busca) || m.contemArtista(busca)) {
                m.exibir();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    static void criarPlaylist() {
        try {
            System.out.print("Nome da playlist: ");
            String nome = scanner.nextLine();

            usuario.criarPlaylist(nome);

            System.out.println("Playlist criada!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void gerenciarPlaylists() {
        usuario.listarPlaylists();

        System.out.print("Escolha: ");
        try {
            int i = Integer.parseInt(scanner.nextLine()) - 1;

            Playlist p = usuario.getPlaylist(i);
            if (p == null) {
                System.out.println("Playlist inválida!");
                return;
            }

            System.out.println("1. Adicionar música");
            System.out.println("2. Remover música");
            System.out.println("3. Listar músicas");

            int op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1:
                    listarMusicas();
                    System.out.print("Escolha a música: ");
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;

                    if (idx >= 0 && idx < musicas.size()) {
                        p.adicionarMusica(musicas.get(idx));
                        System.out.println(" Música adicionada!");
                    } else {
                        System.out.println("Índice inválido!");
                    }
                    break;

                case 2:
                    p.listarMusicas();
                    System.out.print("Escolha a música para remover: ");
                    int r = Integer.parseInt(scanner.nextLine()) - 1;
                    p.removerMusica(r);
                    break;

                case 3:
                    p.listarMusicas();
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } catch (Exception e) {
            System.out.println("Entrada inválida!");
        }
    }

    static void exibirEstatisticas() {
        int total = musicas.size();
        int soma = 0;

        for (Musica m : musicas) {
            soma += m.getDuracaoSegundos();
        }

        System.out.println("Total de músicas: " + total);
        System.out.println("Duração total: " + soma + " segundos");
    }

    static void adicionarMusicasTeste() {
        try {
            Musica m1 = new Musica("Bohemian Rhapsody", "Queen", 354, "rock");
            musicas.add(m1);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar música teste.");
        }
    }
}