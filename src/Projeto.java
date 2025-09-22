import java.time.LocalDate;

/**
 * Representa um projeto do sistema, com nome, descrição, datas, status e gerente responsável.
 */
public class Projeto {
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private Status status;
    private Usuario gerente;

    public enum Status { PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO }

    public Projeto(String nome, String descricao, LocalDate dataInicio, LocalDate dataTerminoPrevista, Status status, Usuario gerente) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = status;
        this.gerente = gerente;
    }

    // Getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataTerminoPrevista() { return dataTerminoPrevista; }
    public void setDataTerminoPrevista(LocalDate dataTerminoPrevista) { this.dataTerminoPrevista = dataTerminoPrevista; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Usuario getGerente() { return gerente; }
    public void setGerente(Usuario gerente) { this.gerente = gerente; }

    @Override
    public String toString() {
        return "Projeto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataTerminoPrevista=" + dataTerminoPrevista +
                ", status=" + status +
                ", gerente=" + gerente.getNomeCompleto() +
                '}';
    }
}
