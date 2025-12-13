package ifsc.joe.domain.impl;

import ifsc.joe.domain.Constantes;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.api.ComMontaria;
import ifsc.joe.domain.api.Guerreiro;

import java.util.Set;

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {

    // Constantes de Cavaleiro
    public Cavaleiro(int x, int y) {
        super(x, y, "aldeao",
                Constantes.CAVALEIRO_VIDA_MAXIMA,
                Constantes.CAVALEIRO_ATAQUE,
                Constantes.CAVALEIRO_ALCANCE_ATAQUE,
                Constantes.CAVALEIRO_CHANCE_ESQUIVA,
                Constantes.CAVALEIRO_VELOCIDADE);
    }

    @Override
    public void atacar(Set<Personagem> alvos) {
        // Alterna o estado de ataque para o efeito visual
        this.alternarEstadoAtaque();

        // cavaleiro ataca todos os inimigos próximos ao mesmo tempo
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
                        System.out.println("ESQUIVOU! " + alvo.getClass().getSimpleName() + " esquivou do ataque de Cavaleiro.");
                    }
                }
            }
        }

    }
}
