
public class Cadastrar {

    public static void main(String[] args) {
        System.out.println("A iniciar a ligação...");
        Util.conectar();

        System.out.println("A criar dados do Cliente e Orçamento...");
        // Criar Cliente
        Cliente cliente1 = new Cliente("123.456.789-00", "Roberto Luciano");

        // Criar Peças
        Peca peca1 = new Peca("Placa Mãe Asus B550", 1200.00, 10);
        Peca peca2 = new Peca("Processador AMD Ryzen 5", 1500.00, 5);

        // Criar Orçamento
        Orcamento orcamento1 = new Orcamento("08/10/2026", cliente1.getCpf(), 2700.00);

        System.out.println("A organizar as ligações...");
        // Adicionar peças ao orçamento
        orcamento1.adicionarComponente(peca1);
        orcamento1.adicionarComponente(peca2);

        // Associar orçamento ao cliente
        cliente1.adicionarOrcamento(orcamento1);

        System.out.println("A guardar no banco de dados db4o...");
        Util.getManager().store(cliente1);
        Util.getManager().commit();

        System.out.println("Sucesso! Tudo foi guardado.");
        System.out.println(cliente1.toString());

        Util.desconectar();
        System.out.println("Ligação encerrada.");
    }
}