package principal;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.*;
import util.FormatadorTempo;
import util.Validador;

public class StreamingMusicaAluno {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Musica> catalogo = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        adicionarMusicas();

        boolean executando = true;

        while (executando) {

            System.out.println("\n===== STREAMING DE MÚSICA =====");
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Listar músicas");
            System.out.println("3 - Entrar com usuário");
            System.out.println("0 - Sair");

            String opcao = scanner.nextLine();

            switch (opcao) {

                case "1":
                    criarUsuario();
                    break;

                case "2":
                    listarCatalogo();
                    break;

                case "3":
                    entrarUsuario();
                    break;

                case "0":
                    executando = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        System.out.println("Programa encerrado.");
    }

    public static void adicionarMusicas() {

        catalogo.add(new Musica("Numb", "Linkin Park", "rock", 210));
        catalogo.add(new Musica("Thriller", "Michael Jackson", "pop", 250));
        catalogo.add(new Musica("Take Five", "Dave Brubeck", "jazz", 320));
        catalogo.add(new Musica("Titanium", "David Guetta", "eletrônica", 240));
        catalogo.add(new Musica("Lose Yourself", "Eminem", "hip-hop", 260));
    }

    public static void criarUsuario() {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        if (Validador.textoVazio(nome)) {
            System.out.println("Nome inválido.");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("1 - Free");
        System.out.println("2 - Premium");

        String tipo = scanner.nextLine();

        Usuario usuario;

        if (tipo.equals("2")) {
            usuario = new UsuarioPremium(nome, email);
        } else {
            usuario = new UsuarioFree(nome, email);
        }

        usuarios.add(usuario);

        System.out.println("Usuário criado com sucesso.");
    }

    public static void listarCatalogo() {

        System.out.println("\n===== CATÁLOGO =====");

        for (int i = 0; i < catalogo.size(); i++) {

            Musica musica = catalogo.get(i);

            System.out.println(
                    (i + 1) + " - " +
                            musica.getTitulo() +
                            " | " +
                            musica.getArtista() +
                            " | " +
                            musica.getGenero() +
                            " | " +
                            FormatadorTempo.formatar(musica.getDuracao())
            );
        }
    }

    public static void entrarUsuario() {

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\n===== USUÁRIOS =====");

        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + " - " + usuarios.get(i));
        }

        int escolha = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolha < 0 || escolha >= usuarios.size()) {
            System.out.println("Usuário inválido.");
            return;
        }

        Usuario usuario = usuarios.get(escolha);

        menuUsuario(usuario);
    }

    public static void menuUsuario(Usuario usuario) {

        boolean logado = true;

        while (logado) {

            System.out.println("\n===== MENU =====");

            System.out.println("1 - Ouvir música");
            System.out.println("2 - Criar playlist");
            System.out.println("3 - Ver playlists");
            System.out.println("4 - Ver histórico");

            if (usuario instanceof UsuarioPremium) {
                System.out.println("5 - Download");
                System.out.println("6 - Ver downloads");
            }

            System.out.println("0 - Sair");

            String opcao = scanner.nextLine();

            switch (opcao) {

                case "1":
                    ouvirMusica(usuario);
                    break;

                case "2":
                    criarPlaylist(usuario);
                    break;

                case "3":
                    verPlaylists(usuario);
                    break;

                case "4":
                    usuario.exibirHistorico();
                    break;

                case "5":

                    if (usuario instanceof UsuarioPremium) {
                        fazerDownload((UsuarioPremium) usuario);
                    }

                    break;

                case "6":

                    if (usuario instanceof UsuarioPremium) {
                        ((UsuarioPremium) usuario).listarDownloads();
                    }

                    break;

                case "0":
                    logado = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void ouvirMusica(Usuario usuario) {

        listarCatalogo();

        System.out.print("Escolha a música: ");

        int escolha = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolha < 0 || escolha >= catalogo.size()) {
            System.out.println("Música inválida.");
            return;
        }

        Musica musica = catalogo.get(escolha);

        musica.reproduzir();

        usuario.adicionarHistorico(musica);
    }

    public static void criarPlaylist(Usuario usuario) {

        System.out.print("Nome da playlist: ");

        String nome = scanner.nextLine();

        Playlist playlist = new PlaylistPersonalizada(nome);

        boolean adicionando = true;

        while (adicionando) {

            listarCatalogo();

            System.out.println("0 - Finalizar");

            int escolha = Integer.parseInt(scanner.nextLine());

            if (escolha == 0) {
                adicionando = false;
            } else {

                escolha--;

                if (escolha >= 0 && escolha < catalogo.size()) {

                    playlist.adicionarMusica(catalogo.get(escolha));

                    System.out.println("Música adicionada.");
                }
            }
        }

        usuario.adicionarPlaylist(playlist);

        System.out.println("Playlist criada.");
    }

    public static void verPlaylists(Usuario usuario) {

        usuario.exibirPlaylists();

        if (usuario.getPlaylists().isEmpty()) {
            return;
        }

        System.out.print("Escolha uma playlist: ");

        int escolha = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolha < 0 || escolha >= usuario.getPlaylists().size()) {
            System.out.println("Playlist inválida.");
            return;
        }

        Playlist playlist = usuario.getPlaylists().get(escolha);

        playlist.listarMusicas();

        System.out.println("Duração total: " +
                FormatadorTempo.formatar(playlist.getDuracaoTotal()));
    }

    public static void fazerDownload(UsuarioPremium usuario) {

        listarCatalogo();

        System.out.print("Escolha a música: ");

        int escolha = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolha < 0 || escolha >= catalogo.size()) {
            System.out.println("Música inválida.");
            return;
        }

        usuario.baixar(catalogo.get(escolha));
    }
}