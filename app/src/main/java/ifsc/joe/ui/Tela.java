package ifsc.joe.ui;

import ifsc.joe.domain.api.Guerreiro;
import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.domain.impl.Personagem;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Tela extends JPanel {

    private final Set<Personagem> personagens;

    public Tela() {

        //TODO preciso ser melhorado

        this.setBackground(Color.white);
        this.personagens = new HashSet<>();
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
    public void criarAldeao(int x, int y) {
        adicionarPersonagem(new Aldeao(x,y));
    }

    /**
     * cria um arqueiro nas coordenadas X e Y
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void criarArqueiro(int x,int y){
        adicionarPersonagem(new Arqueiro(x,y));
    }

    /**
     * cria um cavaleiro nas coordenadas X e Y
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void criarCavaleiro(int x,int y){
        adicionarPersonagem(new Cavaleiro(x,y));
    }

    /**
     * att as coordenadas X ou Y de todos os personagens
     *
     * @param direcao direcao para movimentar
     */
    public void movimentarPersonagens(Direcao direcao) {
        //TODO preciso ser melhorado
        // ADICIONAR FILTRO DE SELECAO !!!!!!!

        this.personagens.forEach(personagem -> personagem.mover(direcao, this.getWidth(), this.getHeight()));

        // repintar o JPanel
        this.repaint();
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
                .filter(p -> p instanceof Guerreiro)
                .map(p -> (Guerreiro) p)
                .forEach(guerreiro -> guerreiro.atacar(alvos));

        // Fazendo o JPanel ser redesenhado
        this.repaint();
    }
}