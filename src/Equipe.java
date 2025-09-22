import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma equipe de trabalho.
 * Cada equipe pode ter múltiplos membros e atuar em vários projetos.
 */
public class Equipe {

    private String nome;
    private String descricao;
    private List<Usuario> membros;
    private List<Projeto> projetos; 

    public Equipe(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
        this.projetos = new ArrayList<>(); 
    }

    public void adicionarMembro(Usuario membro) {
        this.membros.add(membro);
    }

    public void adicionarProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", membros=" + membros.size() +
                ", projetos=" + projetos.size() + // mostra quantidade de projetos associados
                '}';
    }

    public void listarMembros(boolean detalhado) {
        for (Usuario u : membros) {
            if (detalhado) {
                System.out.println("- " + u.getNomeCompleto() + " (" + u.getPerfil() + ")");
            } else {
                System.out.println("- " + u.getNomeCompleto());
            }
        }
    }

    public void listarProjetos() {
        System.out.println("Projetos associados à equipe:");
        for (Projeto p : projetos) {
            System.out.println("- " + p.getNome());
        }
    }
}
