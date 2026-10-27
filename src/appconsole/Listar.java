package appconsole;

import java.util.List;

import modelo.Cliente;
import modelo.Orcamento;
import modelo.Peca;
import util.Util;

public class Listar {

    public static void main(String[] args) {
        Util.conectar();

        List<Cliente> clientes = Util.getManager().query(Cliente.class);
        System.out.println("===== CLIENTES (" + clientes.size() + ") =====");
        for (Cliente c : clientes) {
            System.out.println(c);
            for (Orcamento o : c.getOrcamentos()) {
                System.out.println("    orçamento " + o.getId() + " de " + o.getData());
            }
        }

        List<Orcamento> orcamentos = Util.getManager().query(Orcamento.class);
        System.out.println("\n===== ORÇAMENTOS (" + orcamentos.size() + ") =====");
        for (Orcamento o : orcamentos) {
            System.out.println(o);
            for (Peca p : o.getComponentes()) {
                System.out.println("    peça " + p.getId() + ": " + p.getDescricao());
            }
        }

        List<Peca> pecas = Util.getManager().query(Peca.class);
        System.out.println("\n===== PEÇAS (" + pecas.size() + ") =====");
        for (Peca p : pecas) {
            System.out.println(p);
            for (Orcamento o : p.getOrcamentos()) {
                System.out.println("    no orçamento " + o.getId() + " de " + o.getData());
            }
        }

        Util.desconectar();
    }
}