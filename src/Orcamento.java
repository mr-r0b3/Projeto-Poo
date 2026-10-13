import java.util.ArrayList;
import java.util.List;

public class Orcamento {
    private int id;
    private String data;
    private String cpf;
    private double valor;
    private List<Peca> componentes = new ArrayList<>(); // Lista de componentes

    public Orcamento(String data, String cpf) {
        this.data = data;
        this.cpf = cpf;
        this.valor = 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public double getValor() { return valor; }

    public List<Peca> getComponentes() { return componentes; }
    public void adicionarComponente(Peca p) {
        if (!componentes.contains(p)) {
            componentes.add(p);
            p.adicionarOrcamento(this);
            calcularValor();
        }
    }

    public double calcularValor() {
        valor = 0;
        for (Peca p : componentes) {
            valor += p.getPreco();
        }
        return valor;
    }

    @Override
    public String toString() {
        return "Orcamento [id=" + id + ", data=" + data + ", cpf=" + cpf + ", valor=" + valor + ", componentes=" + componentes.size() + "]";
    }
}