package ifsc.joe.ui;

import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.TipoSelecao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsável por gerenciar os controles e interações da interface.
 * Conecta os componentes visuais com a lógica do jogo (Tela).
 */
public class PainelControles {

    private JPanel painelPrincipal; // painel gerado pelo UI Designer
    private JButton bCriaAldeao;
    private JButton bCriaArqueiro;
    private JButton bCriaCavaleiro;
    private JButton buttonCima;
    private JButton buttonBaixo;
    private JButton buttonEsquerda;
    private JButton buttonDireita;
    private JButton atacarButton; // botão de ataque
    private JButton btnColetar; //  botão de coleta
    private JPanel JanelaJogo;
    private JPanel Tela;
    private boolean listenersConfigurados = false;

    // Radio Buttons
    private JRadioButton todosRadioButton;
    private JRadioButton aldeaoRadioButton;
    private JRadioButton arqueiroRadioButton;
    private JRadioButton cavaleiroRadioButton;
    private JLabel logo;


    private Tela telaJogo; //
    // construtor para receber a Tela
    // Construtor padrão (não usado quando o UI Designer está ativo)
    public PainelControles() {
        // A inicialização de telaJogo é feita em createUIComponents()
        // O UI Designer chama createUIComponents() antes do construtor.
    }
    // Construtor para uso manual (sem UI Designer)
    public PainelControles(Tela telaJogo) {
        this.telaJogo = telaJogo;

    }

    /**
     * Método chamado pelo UI Designer para inicializar componentes com custom-create="true".
     */
    private void createUIComponents() {
        // inicializa o componente JanelaJogo (que é a Tela)
        // o campo JanelaJogo deve ser do tipo Tela, que é um JPanel customizado.
        JanelaJogo = new ifsc.joe.ui.Tela();
        telaJogo = (Tela) JanelaJogo;// garante que a referência interna a telaJogo seja a mesma
        Tela = JanelaJogo;
    }

    private void configurarListeners() {
        // --- botões de criação ---
        // posição inicial aleatória ou fixa (50, 50)
        bCriaAldeao.addActionListener(e -> telaJogo.criarAldeao(50, 50));
        bCriaArqueiro.addActionListener(e -> telaJogo.criarArqueiro(100, 50));
        bCriaCavaleiro.addActionListener(e -> telaJogo.criarCavaleiro(150, 50));

        // --- botões de ação ---
        buttonCima.addActionListener(e -> telaJogo.movimentarPersonagens(Direcao.CIMA));
        buttonBaixo.addActionListener(e -> telaJogo.movimentarPersonagens(Direcao.BAIXO));
        buttonEsquerda.addActionListener(e -> telaJogo.movimentarPersonagens(Direcao.ESQUERDA));
        buttonDireita.addActionListener(e -> telaJogo.movimentarPersonagens(Direcao.DIREITA));


        // Botão atacar
        atacarButton.addActionListener(e -> telaJogo.atacarPersonagens());

        // Botão coletar
        btnColetar.addActionListener(e -> telaJogo.coletarRecursos());

        // --- Radio Buttons de Seleção ---
        ActionListener filtroListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (todosRadioButton.isSelected()) {
                    telaJogo.setFiltroSelecao(TipoSelecao.TODOS);
                } else if (aldeaoRadioButton.isSelected()) {
                    telaJogo.setFiltroSelecao(TipoSelecao.ALDEAO);
                } else if (arqueiroRadioButton.isSelected()) {
                    telaJogo.setFiltroSelecao(TipoSelecao.ARQUEIRO);
                } else if (cavaleiroRadioButton.isSelected()) {
                    telaJogo.setFiltroSelecao(TipoSelecao.CAVALEIRO);
                }
            }
        };

        // adicionando os listeners aos seus Radio Buttons
        todosRadioButton.addActionListener(filtroListener);
        aldeaoRadioButton.addActionListener(filtroListener);
        arqueiroRadioButton.addActionListener(filtroListener);
        cavaleiroRadioButton.addActionListener(filtroListener);

        // garantindo que o filtro inicial vai ser TODOS
        todosRadioButton.setSelected(true);
        telaJogo.setFiltroSelecao(TipoSelecao.TODOS);
    }

    public JPanel getPainelPrincipal() {
        if (!listenersConfigurados) {
            configurarListeners();
            listenersConfigurados = true;
        }
        return painelPrincipal;
    }
    public Tela getTelaJogo() {
        return telaJogo;
    }
}