package ifsc.joe.domain.impl;

import ifsc.joe.domain.api.ComMontaria;
import ifsc.joe.domain.api.Guerreiro;

import java.util.Set;

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {

    // Constantes de Cavaleiro (serão movidas para Constantes.java depois)
    private static final int VIDA_MAXIMA = 150;
    private static final int ATAQUE = 20;
    private static final int ALCANCE_ATAQUE = 75; // 75px
    private static final double CHANCE_ESQUIVA = 0.15; // 15%
    private static final int VELOCIDADE = 15; // Mais rápido por ter montaria

    public Cavaleiro(int x, int y) {
        super(x, y, "cavaleiro", VIDA_MAXIMA, ATAQUE, ALCANCE_ATAQUE, CHANCE_ESQUIVA, VELOCIDADE);
    }

    @Override
    public void atacar(Set<Personagem> alvos) {
        // Alterna o estado de ataque para o efeito visual
        this.alternarEstadoAtaque();

    }
}
