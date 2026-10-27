package appconsole;

import java.util.List;

import modelo.Cliente;
import modelo.Orcamento;
import modelo.Peca;
import util.Util;

public class Cadastrar {

    public static void main(String[] args) {
        Util.conectar();

        // Evita cadastrar de novo por cima de um banco que já tem dados
        List<Cliente> existentes = Util.getManager().query(Cliente.class);
        if (!existentes.isEmpty()) {
            System.out.println("O banco já tem dados. Apague os arquivos .db4o para cadastrar de novo.");
            Util.desconectar();
            return;
        }

        // Peças
        Peca placaMae = new Peca("Placa Mãe Asus B550", 1200.00, 10);
        Peca processador = new Peca("Processador AMD Ryzen 5", 1500.00, 5);
        Peca memoria = new Peca("Memória RAM 16GB", 300.00, 20);
        Peca fonte = new Peca("Fonte 500W", 280.00, 15);

        // Clientes
        Cliente roberto = new Cliente("123.456.789-00", "Roberto Luciano");
        Cliente ana = new Cliente("987.654.321-00", "Ana Souza");

        // Orçamentos (a data é guardada como texto)
        Orcamento o1 = new Orcamento("08/10/2026", roberto.getCpf());
        o1.adicionarComponente(placaMae);
        o1.adicionarComponente(processador);
        o1.adicionarComponente(memoria);

        Orcamento o2 = new Orcamento("09/10/2026", roberto.getCpf());
        o2.adicionarComponente(processador);   // mesma peça em outro orçamento
        o2.adicionarComponente(fonte);

        Orcamento o3 = new Orcamento("10/10/2026", ana.getCpf());
        o3.adicionarComponente(processador);
        o3.adicionarComponente(memoria);

        // Relacionamento cliente -> orçamentos
        roberto.adicionarOrcamento(o1);
        roberto.adicionarOrcamento(o2);
        ana.adicionarOrcamento(o3);

        // Ao gravar o cliente, vão junto seus orçamentos e as peças ligadas a eles
        Util.getManager().store(roberto);
        Util.getManager().store(ana);
        Util.getManager().commit();

        System.out.println("Cadastrado: 2 clientes, 3 orçamentos e 4 peças.");
        Util.desconectar();
    }
}