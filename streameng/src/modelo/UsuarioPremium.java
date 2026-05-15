package modelo;

import java.util.ArrayList;

import servico.Baixavel;

public class UsuarioPremium extends Usuario implements Baixavel {

    private ArrayList<Musica> downloads;

    public UsuarioPremium(String nome, String email) {

        super(nome, email);

        this.downloads = new ArrayList<>();
    }

    public ArrayList<Musica> getDownloads() {
        return downloads;
    }

    @Override
    public void baixar(Musica musica) {

        if (!downloads.contains(musica)) {

            downloads.add(musica);

            System.out.println("Download realizado.");
        } else {
            System.out.println("Música já baixada.");
        }
    }

    @Override
    public void removerDownload(Musica musica) {

        if (downloads.remove(musica)) {
            System.out.println("Download removido.");
        } else {
            System.out.println("Música não encontrada.");
        }
    }

    @Override
    public boolean estaBaixada(Musica musica) {
        return downloads.contains(musica);
    }

    @Override
    public int getTamanhoBaixados() {
        return downloads.size();
    }

    public void listarDownloads() {

        if (downloads.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }

        for (Musica musica : downloads) {
            System.out.println(musica);
        }
    }

    @Override
    public void exibirTipoUsuario() {
        System.out.println("Usuário Premium");
    }

    @Override
    public String toString() {
        return nome + " (Premium)";
    }
}