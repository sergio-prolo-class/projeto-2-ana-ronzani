package ifsc.joe.domain.impl;

import ifsc.joe.domain.impl.Personagem;
import ifsc.joe.domain.api.Guerreiro;

import java.util.Set;

public class Arqueiro extends Personagem implements Guerreiro {

    private static final int VIDA_MAXIMA = 100;
    private static final int ATAQUE = 15;
    private static final int ALCANCE_ATAQUE = 150; // 150px
    private static final double CHANCE_ESQUIVA = 0.25; // 25%
    private static final int VELOCIDADE = 10;

    public Arqueiro(int x, int y) {
        super(x, y, "arqueiro", VIDA_MAXIMA, ATAQUE, ALCANCE_ATAQUE, CHANCE_ESQUIVA, VELOCIDADE);
    }

    @Override
    public void atacar(Set<Personagem> alvos) {
        // Alterna o estado de ataque para o efeito visual
        this.alternarEstadoAtaque();

    }
}