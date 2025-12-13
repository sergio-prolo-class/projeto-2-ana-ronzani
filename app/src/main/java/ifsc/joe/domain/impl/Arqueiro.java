package ifsc.joe.domain.impl;

import ifsc.joe.domain.Constantes;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.api.Guerreiro;

import java.util.Set;

public class Arqueiro extends Personagem implements Guerreiro {

    public Arqueiro(int x, int y) {
        super(x, y, "aldeao",
                Constantes.ARQUEIRO_VIDA_MAXIMA,
                Constantes.ARQUEIRO_ATAQUE,
                Constantes.ARQUEIRO_ALCANCE_ATAQUE,
                Constantes.ARQUEIRO_CHANCE_ESQUIVA,
                Constantes.ARQUEIRO_VELOCIDADE);
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