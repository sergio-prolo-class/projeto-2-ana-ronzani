package ifsc.joe.domain;

/**
 * Classe final para centralizar todas as constantes de configuração e balanceamento do jogo.
 */
public final class Constantes {

    //Configurações visuais
    public static final int LARGURA_TELA = 800;
    public static final int ALTURA_TELA = 600;
    public static final int VELOCIDADE_MOVIMENTO_PADRAO = 10;
    public static final int ALCANCE_COLETA = 50; // distância máxima para coletar

    // Configurações de tamanho de imagem
    public static final int TAMANHO_PERSONAGEM = 32; // Tamanho padrão 32x32
    public static final int TAMANHO_RECURSO = 32; // Tamanho padrão 32x32

    //Configurações de aldeão
    public static final int ALDEAO_VIDA_MAXIMA = 100;
    public static final int ALDEAO_ATAQUE = 10;
    public static final int ALDEAO_ALCANCE_ATAQUE = 50;
    public static final double ALDEAO_CHANCE_ESQUIVA = 0.10;
    public static final int ALDEAO_VELOCIDADE = 10;

    //Configurações de arqueiro
    public static final int ARQUEIRO_VIDA_MAXIMA = 100;
    public static final int ARQUEIRO_ATAQUE = 15;
    public static final int ARQUEIRO_ALCANCE_ATAQUE = 150;
    public static final double ARQUEIRO_CHANCE_ESQUIVA = 0.25;
    public static final int ARQUEIRO_VELOCIDADE = 10;

    //Configurações de cavaleiro
    public static final int CAVALEIRO_VIDA_MAXIMA = 150;
    public static final int CAVALEIRO_ATAQUE = 20;
    public static final int CAVALEIRO_ALCANCE_ATAQUE = 75;
    public static final double CAVALEIRO_CHANCE_ESQUIVA = 0.15;
    public static final int CAVALEIRO_VELOCIDADE = 15;

    private Constantes() {
        // Construtor privado para evitar instanciação
    }
}
