package ifsc.joe.domain.impl;

import ifsc.joe.domain.Personagem;
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

        // arqueiro ataca todos os inimigos próximos ao mesmo tempo
        for (Personagem alvo : alvos) {
            // nao ataca a si mesmo e nem quem ja esta morto
            if (alvo != this && !alvo.estaMorto()) {
                // calcula a distancia entre o atacante e o alvo
                double distancia = calcularDistancia(alvo);

                // ataque só causa dano se a distância for menor ou igual o alcance
                if (distancia <= this.alcanceAtaque) {
                    // aplica dano baseado no atrtibuto ataque e verifica a esquiva
                    boolean danoAplicado = alvo.sofrerDano(this.ataque);

                    if (!danoAplicado) {
                        System.out.println("ESQUIVOU! " + alvo.getClass().getSimpleName() + " esquivou do ataque de Arqueiro.");
                    }
                }
            }
        }
    }
}