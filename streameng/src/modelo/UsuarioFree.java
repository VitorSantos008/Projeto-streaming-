package modelo;

public class UsuarioFree extends Usuario {

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void exibirTipoUsuario() {
        System.out.println("Usuário Free");
    }

    @Override
    public String toString() {
        return nome + " (Free)";
    }
}