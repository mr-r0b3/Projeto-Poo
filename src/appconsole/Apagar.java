package appconsole;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Cliente;
import modelo.Orcamento;
import modelo.Peca;
import util.Util;

// Apaga o orçamento de 09/10/2026, que tem relacionamento com um cliente e com peças
public class Apagar {

    public static void main(String[] args) {
        Util.conectar();

        Query q = Util.getManager().query();
        q.constrain(Orcamento.class);
        q.descend("data").constrain("09/10/2026");
        List<Orcamento> orcamentos = q.execute();

        if (orcamentos.isEmpty()) {
            System.out.println("Orçamento não encontrado no banco.");
            Util.desconectar();
            return;
        }

        Orcamento orcamento = orcamentos.get(0);
        System.out.println("Apagando: " + orcamento);

        // 1) Desfaz o relacionamento com as peças (cópia da lista, pois ela vai mudar)
        List<Peca> pecas = new ArrayList<>(orcamento.getComponentes());
        for (Peca p : pecas) {
            orcamento.removerComponente(p);
            Util.getManager().store(p);
        }

        // 2) Desfaz o relacionamento com o cliente
        Query qc = Util.getManager().query();
        qc.constrain(Cliente.class);
        qc.descend("cpf").constrain(orcamento.getCpf());
        List<Cliente> clientes = qc.execute();
        for (Cliente c : clientes) {
            c.removerOrcamento(orcamento);
            Util.getManager().store(c);
        }

        // 3) Agora apaga o orçamento
        Util.getManager().delete(orcamento);
        Util.getManager().commit();

        System.out.println("Orçamento apagado. As peças e o cliente continuam no banco.");
        Util.desconectar();
    }
}