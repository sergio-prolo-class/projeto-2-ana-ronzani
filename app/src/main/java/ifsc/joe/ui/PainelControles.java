package ifsc.joe.ui;

import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.TipoSelecao;

import javax.swing.*;
import java.awt.*; // Importação necessária para Layouts e Dimension
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
        // Quando instanciado manualmente, precisamos chamar createUIComponents() explicitamente
        createUIComponents();
        initComponents(); // Chamada para inicializar todos os componentes manualmente
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

    /**
     * Inicializa todos os componentes da interface manualmente
     */
    private void initComponents() {
        // Criar painel principal
        painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setPreferredSize(new Dimension(800, 600));
        painelPrincipal.setMinimumSize(new Dimension(800, 600));
        painelPrincipal.setMaximumSize(new Dimension(800, 600));

        GridBagConstraints gbc = new GridBagConstraints();

        // Adicionar JanelaJogo (Tela) à esquerda
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        painelPrincipal.add(JanelaJogo, gbc);

        // Criar painel de controles à direita
        JPanel painelControles = criarPainelControles();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        painelPrincipal.add(painelControles, gbc);
    }

    /**
     * Cria o painel de controles com todos os botões e radio buttons
     */
    private JPanel criarPainelControles() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createLoweredBevelBorder());

        // Painel de criação de personagens
        JPanel painelCriar = new JPanel(new GridLayout(1, 3));
        painelCriar.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Criar"));

        bCriaAldeao = new JButton();
        bCriaAldeao.setIcon(carregarIcone("aldeao.png"));
        bCriaAldeao.setFocusPainted(false);
        bCriaAldeao.setFocusable(false);

        bCriaArqueiro = new JButton();
        bCriaArqueiro.setIcon(carregarIcone("arqueiro.png"));

        bCriaCavaleiro = new JButton();
        bCriaCavaleiro.setIcon(carregarIcone("cavaleiro.png"));

        painelCriar.add(bCriaAldeao);
        painelCriar.add(bCriaArqueiro);
        painelCriar.add(bCriaCavaleiro);

        painel.add(painelCriar);

        // Painel de movimentação
        JPanel painelMovimentar = new JPanel(new BorderLayout());
        painelMovimentar.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Movimentar"));

        // Radio buttons à esquerda
        JPanel painelRadio = new JPanel();
        painelRadio.setLayout(new BoxLayout(painelRadio, BoxLayout.Y_AXIS));

        ButtonGroup grupo = new ButtonGroup();

        todosRadioButton = new JRadioButton("Todos");
        aldeaoRadioButton = new JRadioButton("Aldeao");
        arqueiroRadioButton = new JRadioButton("Arqueiro");
        cavaleiroRadioButton = new JRadioButton("Cavaleiro");

        grupo.add(todosRadioButton);
        grupo.add(aldeaoRadioButton);
        grupo.add(arqueiroRadioButton);
        grupo.add(cavaleiroRadioButton);

        painelRadio.add(todosRadioButton);
        painelRadio.add(aldeaoRadioButton);
        painelRadio.add(arqueiroRadioButton);
        painelRadio.add(cavaleiroRadioButton);

        painelMovimentar.add(painelRadio, BorderLayout.WEST);

        // Botão atacar à direita
        atacarButton = new JButton("Atacar");
        painelMovimentar.add(atacarButton, BorderLayout.EAST);

        // Botões de direção
        JPanel painelDirecao = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDir = new GridBagConstraints();

        buttonCima = new JButton();
        buttonCima.setIcon(carregarIcone("cima.png"));

        buttonBaixo = new JButton();
        buttonBaixo.setIcon(carregarIcone("baixo.png"));

        buttonEsquerda = new JButton();
        buttonEsquerda.setIcon(carregarIcone("esquerda.png"));

        buttonDireita = new JButton();
        buttonDireita.setIcon(carregarIcone("direita.png"));

        // Posicionar botões
        gbcDir.gridx = 1;
        gbcDir.gridy = 0;
        painelDirecao.add(buttonCima, gbcDir);

        gbcDir.gridx = 0;
        gbcDir.gridy = 1;
        painelDirecao.add(buttonEsquerda, gbcDir);

        gbcDir.gridx = 1;
        gbcDir.gridy = 1;
        painelDirecao.add(buttonBaixo, gbcDir);

        gbcDir.gridx = 2;
        gbcDir.gridy = 1;
        painelDirecao.add(buttonDireita, gbcDir);

        painelMovimentar.add(painelDirecao, BorderLayout.SOUTH);

        painel.add(painelMovimentar);

        // Botão coletar (se necessário)
        btnColetar = new JButton("Coletar");
        JPanel painelColetar = new JPanel();
        painelColetar.add(btnColetar);
        painel.add(painelColetar);

        // Logo
        logo = new JLabel();
        logo.setIcon(carregarIcone("ifsclogo.png"));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(logo);

        return painel;
    }

    /**
     * Carrega um ícone do classpath
     */
    private ImageIcon carregarIcone(String nomeArquivo) {
        try {
            java.net.URL url = getClass().getClassLoader().getResource(nomeArquivo);
            if (url != null) {
                return new ImageIcon(url);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ícone: " + nomeArquivo);
        }
        return null;
    }

    private void configurarListeners() {
        // --- botões de criação ---
        // posição aleatória (-1, -1) para evitar sobreposição
        bCriaAldeao.addActionListener(e -> telaJogo.criarAldeao(-1, -1));
        bCriaArqueiro.addActionListener(e -> telaJogo.criarArqueiro(-1, -1));
        bCriaCavaleiro.addActionListener(e -> telaJogo.criarCavaleiro(-1, -1));

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
