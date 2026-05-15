package modelo;

import java.util.ArrayList;

public abstract class Usuario {

    protected String nome;
    protected String email;

    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historico;

    public Usuario(String nome, String email) {

        this.nome = nome;
        this.email = email;

        this.playlists = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<Musica> getHistorico() {
        return historico;
    }

    public void adicionarPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public void adicionarHistorico(Musica musica) {
        historico.add(musica);
    }

    public void exibirHistorico() {

        if (historico.isEmpty()) {
            System.out.println("Histórico vazio.");
            return;
        }

        for (Musica musica : historico) {
            System.out.println(musica);
        }
    }

    public void exibirPlaylists() {

        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
            return;
        }

        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
    }

    public abstract void exibirTipoUsuario();
}