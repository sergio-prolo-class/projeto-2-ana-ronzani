package ifsc.joe.domain.impl;

import ifsc.joe.domain.Constantes;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.api.Guerreiro;
import ifsc.joe.domain.api.Coletador;
import ifsc.joe.enums.TipoRecurso;

import java.util.Set;

public class Aldeao extends Personagem implements Guerreiro, Coletador {

    //constantes do aldeao
    public Aldeao(int x, int y) {
        super(x, y, "aldeao",
                Constantes.ALDEAO_VIDA_MAXIMA,
                Constantes.ALDEAO_ATAQUE,
                Constantes.ALDEAO_ALCANCE_ATAQUE,
                Constantes.ALDEAO_CHANCE_ESQUIVA,
                Constantes.ALDEAO_VELOCIDADE);
    }

    // Os métodos desenhar() e mover foram movidos para Personagem.java.


    @Override
    public void atacar(Set<Personagem> alvos) {
        // Alterna o estado de ataque para o efeito visual
        this.alternarEstadoAtaque();

        // ataca todos os inimigos proximos ao mesmo tempo
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
                        System.out.println("ESQUIVOU! " + alvo.getClass().getSimpleName() + "esquivou do ataque de Aldeão.");
                    }
                }
            }
        }
    }


    // O método carregarImagem() foi movido para Personagem.java.

    @Override
    public void coletar(Recurso recurso) {
        // lógica de coleta: Aldeão coleta 5 unidades por vez
        int coletado = recurso.coletar(5);
        System.out.println("Aldeão coletou " + coletado + " de " + recurso.getTipo().name());
        // Se o recurso for COMIDA, o aldeão recupera vida
        if (recurso.getTipo() == TipoRecurso.COMIDA) {
            // Recupera 1 ponto de vida para cada unidade de comida coletada
            this.recuperarVida(coletado);
            System.out.println("Aldeão recuperou " + coletado + " de vida. Vida atual: " + this.getVidaAtual());
        }
    }
}