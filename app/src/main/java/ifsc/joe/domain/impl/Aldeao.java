package ifsc.joe.domain.impl;

import ifsc.joe.enums.Direcao;
import ifsc.joe.domain.impl.Personagem;
import ifsc.joe.domain.api.Guerreiro;
import ifsc.joe.domain.api.Coletador;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Set;

public class Aldeao extends Personagem implements Guerreiro, Coletador {

    //constantes do aldeao
    private static final int VIDA_MAXIMA = 100;
    private static final int ATAQUE = 10;
    private static final int ALCANCE_ATAQUE = 50; // 50px
    private static final double CHANCE_ESQUIVA = 0.10; // 10%
    private static final int VELOCIDADE = 10;

    public Aldeao(int x, int y) {
        super(x, y, "aldeao", VIDA_MAXIMA, ATAQUE, ALCANCE_ATAQUE, CHANCE_ESQUIVA, VELOCIDADE);
    }

    // O método desenhar() foi movido para Personagem.java.

    // O método mover() foi movido para Personagem.java.


    @Override
    public void atacar(Set<Personagem> alvos) {
        // Alterna o estado de ataque para o efeito visual
        this.alternarEstadoAtaque();

        // ataca todos os inimigos proximos ao mesmo tempo
        for (Personagem alvo : alvos){
            // nao ataca a si mesmo e nem quem ja esta morto
            if (alvo != this && !alvo.estaMorto()){
                // calcula a distancia entre o atacante e o alvo
                double distancia = calcularDistancia(alvo);

                // ataque só causa dano se a distância for menor ou igual o alcance
                if (distancia <= this.alcanceAtaque){

                    // aplica dano baseado no atrtibuto ataque e verifica a esquiva
                    boolean danoAplicado = alvo.sofrerDano(this.ataque);

                    if (!danoAplicado){
                        System.out.println("ESQUIVOU! " + alvo.getClass().getSimpleName() + "esquivou do ataque de Aldeão.");
                    }
                }
            }
        }
    }

    // O método carregarImagem() foi movido para Personagem.java.

    @Override
    public void coletar(Object recurso) {
        System.out.println("Aldeão coletando recurso: " + recurso);
    }

}