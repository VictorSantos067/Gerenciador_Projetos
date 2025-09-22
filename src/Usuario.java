/**
 * Representa um usuário do sistema, com login, senha e perfil.
 * Herda de Pessoa, mostrando herança.
 */
public class Usuario extends Pessoa {
    private String login;
    private String senha;
    private Perfil perfil;

    public enum Perfil { ADMINISTRADOR, GERENTE, COLABORADOR }

    public Usuario(String nomeCompleto, String cpf, String email, String login, String senha, Perfil perfil) {
        super(nomeCompleto, cpf, email);
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Getters e setters
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    // Sobrescrita de toString
    @Override
    public String toString() {
        return "Usuario{" +
                "nomeCompleto='" + getNomeCompleto() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", login='" + login + '\'' +
                ", perfil=" + perfil +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getCpf().equals(usuario.getCpf());
    }

    @Override
    public int hashCode() { return getCpf().hashCode(); }

    // Método sobrecarregado (overload)
    public void imprimirInfo() {
        System.out.println(this);
    }

    public void imprimirInfo(boolean detalhado) {
        if(detalhado) {
            System.out.println("Usuário detalhado: " + this);
        } else {
            System.out.println("Usuário: " + getNomeCompleto());
        }
    }
}
