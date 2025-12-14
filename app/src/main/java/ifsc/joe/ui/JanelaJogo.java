package ifsc.joe.ui;

import javax.swing.*;
import java.awt.*;
import ifsc.joe.domain.Constantes;

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
        // a Tela será criada dentro de PainelControles.createUIComponents()
        this.painelControles = new PainelControles();
        // a referência a telaJogo será obtida do PainelControles após a inicialização
        this.telaJogo = painelControles.getTelaJogo();

        this.configurarJanela();
    }

    /**
     * Configura o conteúdo da janela
     */
    private void configurarJanela() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        JPanel painelPrincipal = new JPanel(new BorderLayout()); //painel principal para organizar a Tela e o PainelControles

        painelPrincipal.add(this.telaJogo, BorderLayout.CENTER); // add a tela no centro
        painelPrincipal.add(painelControles.getPainelPrincipal(), BorderLayout.SOUTH); // add o painel de controles ao sul da tela
        frame.setContentPane(painelPrincipal);

        this.telaJogo.setPreferredSize(new Dimension(Constantes.LARGURA_TELA, Constantes.ALTURA_TELA)); // define o tamanho preferido da Tela usando as constantes

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