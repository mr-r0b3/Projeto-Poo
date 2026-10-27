package appconsole;

import java.util.List;

import com.db4o.query.Query;

import modelo.Orcamento;
import modelo.Peca;
import util.Util;

// Remove um relacionamento existente: tira o Ryzen 5 do orçamento de 08/10/2026
public class Alterar {

    public static void main(String[] args) {
        Util.conectar();

        // Busca o orçamento
        Query q1 = Util.getManager().query();
        q1.constrain(Orcamento.class);
        q1.descend("data").constrain("08/10/2026");
        List<Orcamento> orcamentos = q1.execute();

        // Busca a peça
        Query q2 = Util.getManager().query();
        q2.constrain(Peca.class);
        q2.descend("descricao").constrain("Processador AMD Ryzen 5");
        List<Peca> pecas = q2.execute();

        if (orcamentos.isEmpty() || pecas.isEmpty()) {
            System.out.println("Orçamento ou peça não encontrado no banco.");
            Util.desconectar();
            return;
        }

        Orcamento orcamento = orcamentos.get(0);
        Peca peca = pecas.get(0);

        if (!orcamento.getComponentes().contains(peca)) {
            System.out.println("Esse orçamento não tem essa peça.");
            Util.desconectar();
            return;
        }

        System.out.println("Antes : " + orcamento);

        // Remove o relacionamento nos dois lados (e recalcula o valor)
        orcamento.removerComponente(peca);

        Util.getManager().store(orcamento);
        Util.getManager().store(peca);
        Util.getManager().commit();

        System.out.println("Depois: " + orcamento);
        System.out.println("Relacionamento removido: a peça \"" + peca.getDescricao()
                + "\" saiu do orçamento " + orcamento.getId() + ".");

        Util.desconectar();
    }
}