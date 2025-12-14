package ifsc.joe.ui;

import ifsc.joe.domain.Constantes;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.api.Coletador;
import ifsc.joe.domain.api.Guerreiro;
import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.domain.impl.Recurso;
import ifsc.joe.enums.Direcao;
import ifsc.joe.enums.TipoRecurso;
import ifsc.joe.enums.TipoSelecao;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Tela extends JPanel {

    private final Set<Personagem> personagens;
    private final Map<Class<? extends Personagem>, Integer> baixas;
    private final List<Recurso> recursos;
    private TipoSelecao filtroSelecao;


    public Tela() {

        //TODO preciso ser melhorado

        this.setBackground(Color.white);
        this.personagens = new HashSet<>();
        this.baixas = new HashMap<>();
        this.recursos = new ArrayList<>();
        this.filtroSelecao = TipoSelecao.TODOS; // padrao: todos selecionados

        // adicionando alguns recursos pra teste
        recursos.add(new Recurso(TipoRecurso.MADEIRA, 50, 50, 2));
        recursos.add(new Recurso(TipoRecurso.MADEIRA, 150, 250, 5));
        recursos.add(new Recurso(TipoRecurso.OURO, 300, 150, 10));
        recursos.add(new Recurso(TipoRecurso.OURO, 600, 350, 75));
        recursos.add(new Recurso(TipoRecurso.COMIDA, 500, 50, 8));
        recursos.add(new Recurso(TipoRecurso.COMIDA, 100, 400, 25));
    }

    /**
     * Method que invocado sempre que o JPanel precisa ser resenhado.
     * @param g Graphics componente de java.awt
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //TODO preciso ser melhorado

        // percorrendo a lista de personagens e pedindo para cada um se desenhar na tela
        this.personagens.forEach(personagem -> personagem.desenhar(g, this));

        // desenhando recursos
        this.recursos.forEach(recurso -> recurso.desenhar(g, this));

        // liberando o contexto gráfico
        g.dispose();
    }

    /**
     * cria o personagem e adiciona na coleçao
     *
     * @param personagem adicionando o objeto Personagem
     */
    public void adicionarPersonagem(Personagem personagem) {
        personagem.desenhar(super.getGraphics(), this);
        this.personagens.add(personagem);
    }

    /**
     * Cria um personagem nas coordenadas X e Y, desenha-o neste JPanel
     * e adiciona o mesmo na lista de aldeoes
     *
     * @param x coordenada X
     * @param y coordenada Y
     */
    /**
     * Cria um personagem nas coordenadas X e Y, desenha-o neste JPanel
     * e adiciona o mesmo na lista de aldeoes
     *
     * @param x coordenada X (se -1, será aleatória)
     * @param y coordenada Y (se -1, será aleatória)
     */
    public void criarAldeao(int x, int y) {
        int finalX = (x == -1) ? gerarCoordenadaAleatoriaX() : x;
        int finalY = (y == -1) ? gerarCoordenadaAleatoriaY() : y;
        adicionarPersonagem(new Aldeao(finalX, finalY));
    }

    /**
     * cria um arqueiro nas coordenadas X e Y
     * @param x coordenada X
     * @param y coordenada Y
     *@param x coordenada X (se -1, será aleatória)
     *@param y coordenada Y (se -1, será aleatória)
     */

    public void criarArqueiro(int x,int y){
        int finalX = (x == -1) ? gerarCoordenadaAleatoriaX() : x;
        int finalY = (y == -1) ? gerarCoordenadaAleatoriaY() : y;
        adicionarPersonagem(new Arqueiro(finalX, finalY));
    }

    /**
     * cria um cavaleiro nas coordenadas X e Y
     * @param x coordenada X
     * @param y coordenada Y
     * @param x coordenada X (se -1, será aleatória)
     * @param y coordenada Y (se -1, será aleatória)
     */
    public void criarCavaleiro(int x,int y){

        int finalX = (x == -1) ? gerarCoordenadaAleatoriaX() : x;
        int finalY = (y == -1) ? gerarCoordenadaAleatoriaY() : y;
        adicionarPersonagem(new Cavaleiro(finalX, finalY));
    }

    /**
     * Gera uma coordenada X aleatória dentro dos limites da tela.
     */
    private int gerarCoordenadaAleatoriaX() {
        // Garante que o personagem caiba na tela (largura da tela - tamanho do personagem)
        return (int) (Math.random() * (this.getWidth() - Constantes.TAMANHO_PERSONAGEM));
    }

    /**
     * Gera uma coordenada Y aleatória dentro dos limites da tela.
     */
    private int gerarCoordenadaAleatoriaY() {
        // Garante que o personagem caiba na tela (altura da tela - tamanho do personagem)
        return (int) (Math.random() * (this.getHeight() - Constantes.TAMANHO_PERSONAGEM));    }

    /**
     * att as coordenadas X ou Y de todos os personagens
     *
     * @param direcao direcao para movimentar
     */
    public void movimentarPersonagens(Direcao direcao) {

        // aplica o filtro de seleção
        this.personagens.stream()
                .filter(this::aplicarFiltro)
                .forEach(personagem -> personagem.mover(direcao, this.getWidth(), this.getHeight()));

        // repintar o JPanel
        this.repaint();
    }

    /**
     * Executa a ação de coleta para todos os personagens Coletadores.
     */
    public void coletarRecursos() {
        // Remove recursos esgotados
        this.recursos.removeIf(Recurso::estaEsgotado);

        // Percorre os personagens que são Coletadores e estão selecionados
        this.personagens.stream()
                .filter(this::aplicarFiltro)
                .filter(p -> p instanceof Coletador)
                .map(p -> (Coletador) p)
                .forEach(coletador -> {
                    // Encontra o recurso mais próximo
                    Recurso recursoProximo = encontrarRecursoProximo((Personagem) coletador);

                    if (recursoProximo != null) {
                        // Verifica proximidade (usando a constante de alcance de coleta)
                        double distancia = ((Personagem) coletador).calcularDistancia(recursoProximo.getPosX(), recursoProximo.getPosY(), recursoProximo.getIcone());
                        if (distancia <= Constantes.ALCANCE_COLETA) { // Alcance de coleta                            coletador.coletar(recursoProximo);
                        }
                    }
                });

        this.repaint();
    }

    /**
     * Encontra o recurso mais próximo de um personagem.
     */
    private Recurso encontrarRecursoProximo(Personagem personagem) {
        Recurso maisProximo = null;
        double menorDistancia = Double.MAX_VALUE;

        for (Recurso recurso : recursos) {
            double distancia = personagem.calcularDistancia(recurso.getPosX(), recurso.getPosY(), recurso.getIcone());
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                maisProximo = recurso;
            }
        }
        return maisProximo;
    }

    /**
     * Altera o estado dos guerreiros de atacando para não atacando e vice-versa
     */
    public void atacarPersonagens() {

        //TODO preciso ser melhorado
        // FILTRO DE SELECAO !!!!!!!!!!

        // Cria uma cópia da lista de personagens para evitar ConcurrentModificationException
        // durante a iteração e remoção de mortos (Fase 4).
        Set<Personagem> alvos = new HashSet<>(this.personagens);

        // Percorrendo a lista de personagens e pedindo para os Guerreiros atacarem
        this.personagens.stream()
                .filter(this::aplicarFiltro) // aplica o filtro de seleção
                .filter(p -> p instanceof Guerreiro)
                .map(p -> (Guerreiro) p)
                .forEach(guerreiro -> guerreiro.atacar(alvos));

        // para verificar se vida <= 0 e remover os personagens que morreram
        removerMortos();

        // Fazendo o JPanel ser redesenhado
        this.repaint();
    }
    /**
     * verifica e remove personagens que estão mortos (vidaAtual <= 0)
     */
    private void removerMortos() {
        Iterator<Personagem> iterator = this.personagens.iterator();
        while (iterator.hasNext()) {
            Personagem personagem = iterator.next();
            if (personagem.estaMorto()) {
                //remove personagem morto da coleçao
                iterator.remove();

                // contabilizar
                Class<? extends Personagem> tipo = personagem.getClass();
                baixas.put(tipo, baixas.getOrDefault(tipo, 0) + 1);


                System.out.println("BAIXA: " + tipo.getSimpleName() + " foi eliminado. Total de baixas: " + baixas.get(tipo));
            }
        }
    }

    /**
     * retorna a coleção de personagens;
     * @return Set de Personagens.
     */
    public Set<Personagem> getPersonagens() {
        return personagens;
    }

    /**
     * define o filtro de seleçao atual
     * @param filtro O novo tipo de seleçao
     */
    public void setFiltroSelecao(TipoSelecao filtro) {
        this.filtroSelecao = filtro;
    }

    /**
     * aplica o filtro de seleçao baseado no tipo de personagem
     * @param personagem O personagem a ser verificado
     * @return true se o personagem deve ser incluído na açao, false caso contrário
     */
    private boolean aplicarFiltro(Personagem personagem) {
        return switch (this.filtroSelecao) {
            case TODOS -> true;
            case ALDEAO -> personagem instanceof Aldeao;
            case ARQUEIRO -> personagem instanceof Arqueiro;
            case CAVALEIRO -> personagem instanceof Cavaleiro;
        };
    }
}