package ifsc.joe.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável pela configuração e exibição da janela principal do jogo.
 */
public class JanelaJogo {

    private static final String TITULO = "Java of Empires";
    private final JFrame frame;
    private final PainelControles painelControles;
    private final Tela telaJogo;


    public JanelaJogo() {
        this.frame = new JFrame(TITULO);
        this.telaJogo = new Tela(); // Instância de Tela
        this.painelControles = new PainelControles(telaJogo);

        this.configurarJanela();
    }

    /**
     * Configura o conteúdo da janela
     */
    private void configurarJanela() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout()); //painel principal para organizar a Tela e o PainelControles

        painelPrincipal.add(telaJogo, BorderLayout.CENTER); // add a tela no centro
        painelPrincipal.add(painelControles.getPainelPrincipal(), BorderLayout.SOUTH);

        frame.setContentPane(painelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null); // centralizar na tela
    }

    /**
     * Torna a janela visível.
     */
    public void exibir() {
        frame.setVisible(true);
    }
}