import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusicaAluno {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        adicionarMusicasTeste();
        criarConta();

        int opcao;
        do {
            if (usuario instanceof UsuarioFree) {
                menuFree();
            } else {
                menuPremium();
            }

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


    static void criarConta() {
        System.out.println("=== BEM-VINDO AO STREAMING ===");

        String nome;
        do {
            System.out.print("Digite seu nome: ");
            nome = scanner.nextLine();

            if (nome == null || nome.trim().isEmpty()) {
                System.out.println("Nome não pode ser vazio!");
            }

        } while (nome == null || nome.trim().isEmpty());

        String email;
        do {
            System.out.print("Digite seu email: ");
            email = scanner.nextLine();

            if (email == null || email.trim().isEmpty()) {
                System.out.println("❌ Email não pode ser vazio!");
            }

        } while (email == null || email.trim().isEmpty());

        System.out.println("1. Free");
        System.out.println("2. Premium");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            usuario = new UsuarioFree(nome, email);
            System.out.println("✅ Conta Free criada!");
        } else {
            System.out.println("1. Mensal");
            System.out.println("2. Anual");
            System.out.println("3. Familiar");
            System.out.print("Escolha: ");
            int plano = Integer.parseInt(scanner.nextLine());

            String tipoPlano = switch (plano) {
                case 1 -> "Mensal";
                case 2 -> "Anual";
                default -> "Familiar";
            };

            usuario = new UsuarioPremium(nome, email, tipoPlano);
            System.out.println("✅ Conta Premium criada!");
        }
    }


    static void menuFree() {
        System.out.println("\n=== MENU FREE ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Reproduzir música");
        System.out.println("3. Ver histórico");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. 💎 Fazer upgrade para Premium");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    static void menuPremium() {
        System.out.println("\n=== MENU PREMIUM ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Reproduzir música (Alta Qualidade)");
        System.out.println("3. Ver histórico");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Baixar música");
        System.out.println("7. Ver músicas baixadas");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }


    static void processarOpcao(int opcao) {

        try {
            if (usuario instanceof UsuarioFree) {

                switch (opcao) {
                    case 1 -> cadastrarMusica();
                    case 2 -> reproduzirMusica();
                    case 3 -> usuario.exibirHistorico();
                    case 4 -> criarPlaylist();
                    case 5 -> gerenciarPlaylists();
                    case 6 -> fazerUpgrade();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida!");
                }

            } else { // PREMIUM

                switch (opcao) {
                    case 1 -> cadastrarMusica();
                    case 2 -> reproduzirMusica();
                    case 3 -> usuario.exibirHistorico();
                    case 4 -> criarPlaylist();
                    case 5 -> gerenciarPlaylists();
                    case 6 -> baixarMusica();
                    case 7 -> ((UsuarioPremium) usuario).listarMusicasBaixadas();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida!");
                }
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
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

            System.out.println("✅ Música cadastrada!");

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

    static void reproduzirMusica() {
        listarMusicas();
        System.out.print("Escolha a música: ");
        int i = Integer.parseInt(scanner.nextLine()) - 1;

        if (i >= 0 && i < musicas.size()) {
            usuario.reproduzirMusica(musicas.get(i));
        } else {
            System.out.println("Índice inválido!");
        }
    }

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        usuario.criarPlaylist(scanner.nextLine());
    }

    static void gerenciarPlaylists() {
        usuario.listarPlaylists();

        System.out.print("Escolha a playlist: ");
        int i = Integer.parseInt(scanner.nextLine()) - 1;

        Playlist p = usuario.getPlaylist(i);
        if (p == null) {
            System.out.println("Playlist inválida!");
            return;
        }

        System.out.println("1. Adicionar música");
        System.out.println("2. Remover música");
        System.out.println("3. Listar músicas");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(scanner.nextLine());

        switch (op) {
            case 1:
                listarMusicas();
                System.out.print("Escolha a música: ");
                int idx = Integer.parseInt(scanner.nextLine()) - 1;

                if (idx >= 0 && idx < musicas.size()) {
                    p.adicionarMusica(musicas.get(idx));
                    System.out.println("✅ Música adicionada!");
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
    }

    static void baixarMusica() {
        listarMusicas();
        System.out.print("Escolha a música: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;

        if (idx >= 0 && idx < musicas.size()) {
            ((UsuarioPremium) usuario).baixarMusica(musicas.get(idx));
        } else {
            System.out.println("Índice inválido!");
        }
    }

    static void fazerUpgrade() {
        UsuarioFree uf = (UsuarioFree) usuario;

        System.out.println("1. Mensal");
        System.out.println("2. Anual");
        System.out.println("3. Familiar");
        System.out.print("Escolha: ");

        int plano = Integer.parseInt(scanner.nextLine());

        String tipoPlano = switch (plano) {
            case 1 -> "Mensal";
            case 2 -> "Anual";
            default -> "Familiar";
        };

        usuario = new UsuarioPremium(uf.getNome(), uf.getEmail(), tipoPlano);
        System.out.println("💎 Upgrade realizado com sucesso!");
    }


    static void adicionarMusicasTeste() {
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "rock"));
        musicas.add(new Musica("Imagine", "John Lennon", 210, "pop"));
    }
}