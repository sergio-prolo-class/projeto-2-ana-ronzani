package ifsc.joe.ui;

import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.TipoSelecao;

import javax.swing.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsável por gerenciar os controles e interações da interface.
 * Conecta os componentes visuais com a lógica do jogo (Tela).
 */
public class PainelControles {

    private JPanel painelPrincipal; // painel gerado pelo UI Designer
    private JButton btnCriarAldeao;
    private JButton btnCriarArqueiro;
    private JButton btnCriarCavaleiro;
    private JButton btnMoverCima;
    private JButton btnAtacar; // botão de ataque
    private JButton btnColetar; //  botão de coleta

    // Radio Buttons
    private JRadioButton rbTodos;
    private JRadioButton rbAldeao;
    private JRadioButton rbArqueiro;
    private JRadioButton rbCavaleiro;

    private final Tela telaJogo; // Referência à Tela

    // construtor para receber a Tela
    public PainelControles(Tela telaJogo) {
        this.telaJogo = telaJogo;

        //chamando o método para configurar os listeners
        configurarListeners();
    }

    private void configurarListeners() {
        // --- botões de criação ---
        // posição inicial aleatória ou fixa (50, 50)
        btnCriarAldeao.addActionListener(e -> telaJogo.criarAldeao(50, 50));
        btnCriarArqueiro.addActionListener(e -> telaJogo.criarArqueiro(100, 50));
        btnCriarCavaleiro.addActionListener(e -> telaJogo.criarCavaleiro(150, 50));

        // --- botões de ação ---
        btnMoverCima.addActionListener(e -> telaJogo.movimentarPersonagens(Direcao.CIMA));
        btnMoverCima.addActionListener(e -> telaJogo.movimentarPersonagens(Direcao.BAIXO));
        btnMoverCima.addActionListener(e -> telaJogo.movimentarPersonagens(Direcao.ESQUERDA));
        btnMoverCima.addActionListener(e -> telaJogo.movimentarPersonagens(Direcao.DIREITA));


        // Botão atacar
        btnAtacar.addActionListener(e -> telaJogo.atacarPersonagens());

        // Botão coletar
        btnColetar.addActionListener(e -> telaJogo.coletarRecursos());

        // --- Radio Buttons de Seleção ---
        ActionListener filtroListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbTodos.isSelected()) {
                    telaJogo.setFiltroSelecao(TipoSelecao.TODOS);
                } else if (rbAldeao.isSelected()) {
                    telaJogo.setFiltroSelecao(TipoSelecao.ALDEAO);
                } else if (rbArqueiro.isSelected()) {
                    telaJogo.setFiltroSelecao(TipoSelecao.ARQUEIRO);
                } else if (rbCavaleiro.isSelected()) {
                    telaJogo.setFiltroSelecao(TipoSelecao.CAVALEIRO);
                }
            }
        };

        // adicionando os listeners aos seus Radio Buttons
        rbTodos.addActionListener(filtroListener);
        rbAldeao.addActionListener(filtroListener);
        rbArqueiro.addActionListener(filtroListener);
        rbCavaleiro.addActionListener(filtroListener);

        // garantindo que o filtro inicial vai ser TODOS
        rbTodos.setSelected(true);
        telaJogo.setFiltroSelecao(TipoSelecao.TODOS);
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }
}