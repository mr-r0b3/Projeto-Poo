package util;

import javax.swing.JOptionPane;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Cliente;
import modelo.Orcamento;
import modelo.Peca;

public class Util {
    private static final String BANCO = "montagem.db4o";
    private static ObjectContainer manager;

    public static void conectar() {
        if (manager != null) return;

        try {
            conectarBancoLocal();
            GeradorID.ativar(manager); // ativa a geração de IDs automáticos
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco " + BANCO + "\n" + e.getMessage());
            System.exit(0);
        }
    }

    private static void conectarBancoLocal() {
        if (manager != null) return;

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().messageLevel(0);

        config.common().objectClass(Cliente.class).cascadeOnDelete(false);
        config.common().objectClass(Cliente.class).cascadeOnUpdate(true);
        config.common().objectClass(Cliente.class).cascadeOnActivate(true);

        config.common().objectClass(Peca.class).cascadeOnDelete(false);
        config.common().objectClass(Peca.class).cascadeOnUpdate(true);
        config.common().objectClass(Peca.class).cascadeOnActivate(true);

        config.common().objectClass(Orcamento.class).cascadeOnDelete(false);
        config.common().objectClass(Orcamento.class).cascadeOnUpdate(true);
        config.common().objectClass(Orcamento.class).cascadeOnActivate(true);

        config.common().activationDepth(Integer.MAX_VALUE);

        try {
            manager = Db4oEmbedded.openFile(config, BANCO);
            System.out.println("Banco de dados ligado com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco local \n" + e.getMessage());
            System.exit(0);
        }
    }

    public static void desconectar() {
        if (manager != null) {
            manager.close();
            manager = null;
        }
    }

    public static ObjectContainer getManager() {
        return manager;
    }

    public static String getBanco() {
        return BANCO;
    }
}