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

        // usando JSplitPane para dividir a tela em duas partes iguais (horizontalmente)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.telaJogo, painelControles.getPainelPrincipal());
        splitPane.setResizeWeight(0.5); // define a divisão em 50/50
        splitPane.setDividerSize(0); // remove a barra divisória
        splitPane.setEnabled(false); // impede que o usuário redimensione

        frame.setContentPane(splitPane);

        // define o tamanho total da janela (Tela + PainelControles)
        // LARGURA_TELA é 800. para 50/50, a largura total deve ser 1600.
        Dimension tamanhoTotal = new Dimension(Constantes.LARGURA_TELA * 2, Constantes.ALTURA_TELA);
        frame.setPreferredSize(tamanhoTotal);

        // garantir que a Tela e o PainelControles tenham o tamanho definido
        this.telaJogo.setMinimumSize(new Dimension(Constantes.LARGURA_TELA, Constantes.ALTURA_TELA));
        painelControles.getPainelPrincipal().setMinimumSize(new Dimension(Constantes.LARGURA_TELA, Constantes.ALTURA_TELA));

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