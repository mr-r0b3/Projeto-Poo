// Teste em memória (sem banco): objetos e seus relacionamentos
public class Teste {

    public static void main(String[] args) {
        Peca p1 = new Peca("Placa Mãe Asus B550", 1200.00, 10);
        Peca p2 = new Peca("Processador AMD Ryzen 5", 1500.00, 5);
        Peca p3 = new Peca("Memória RAM 16GB", 300.00, 20);

        Cliente c1 = new Cliente("123.456.789-00", "Roberto Luciano");
        Cliente c2 = new Cliente("987.654.321-00", "Ana Souza");

        Orcamento o1 = new Orcamento("08/10/2026", c1.getCpf());
        o1.adicionarComponente(p1);
        o1.adicionarComponente(p2);
        c1.adicionarOrcamento(o1);

        Orcamento o2 = new Orcamento("09/10/2026", c2.getCpf());
        o2.adicionarComponente(p2);   // mesma peça em outro orçamento
        o2.adicionarComponente(p3);
        c2.adicionarOrcamento(o2);

        for (Cliente c : new Cliente[]{c1, c2}) {
            System.out.println(c);
            for (Orcamento o : c.getOrcamentos()) {
                System.out.println("  " + o);
                for (Peca p : o.getComponentes()) {
                    System.out.println("    - " + p);
                }
            }
        }

        System.out.println("\nOrçamentos em que o Ryzen 5 aparece: " + p2.getOrcamentos().size());
    }
}