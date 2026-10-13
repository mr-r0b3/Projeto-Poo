
import java.util.ArrayList;
import java.util.List;

public class Peca {
    private int id;
    private String descricao;
    private double preco;
    private int estoque;
    private List<Orcamento> orcamentos = new ArrayList<>();

    public Peca(String descricao, double preco, int estoque) {
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    public List<Orcamento> getOrcamentos() { return orcamentos; }
    public void adicionarOrcamento(Orcamento o) { this.orcamentos.add(o); }

    @Override
    public String toString() {
        return "Peca [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", estoque=" + estoque + "]";
    }
}