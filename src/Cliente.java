
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String cpf;
    private String nome;
    private List<Orcamento> orcamentos = new ArrayList<>();

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Orcamento> getOrcamentos() { return orcamentos; }
    public void adicionarOrcamento(Orcamento o) { this.orcamentos.add(o); }

    @Override
    public String toString() {
        return "Cliente [cpf=" + cpf + ", nome=" + nome + ", orcamentos=" + orcamentos.size() + "]";
    }
}