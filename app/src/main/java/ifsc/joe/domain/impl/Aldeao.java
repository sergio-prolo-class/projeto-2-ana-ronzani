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

    // Constantes de Aldeão (serão movidas para Constantes.java depois)
    private static final int VIDA_MAXIMA = 100;
    private static final int ATAQUE = 10;
    private static final int ALCANCE_ATAQUE = 50; // 50px
    private static final double CHANCE_ESQUIVA = 0.10; // 10%
    private static final int VELOCIDADE = 10;

    public Aldeao(int x, int y) {
        super(x, y, "aldeao", VIDA_MAXIMA, ATAQUE, ALCANCE_ATAQUE, CHANCE_ESQUIVA, VELOCIDADE);
    }

    // O método desenhar() foi movido para Personagem.java.
    // Ele será sobrescrito na Fase 8 para incluir a barra de vida.

    // O método mover() foi movido para Personagem.java.


    @Override
    public void atacar(Set<Personagem> alvos) {
        // Alterna o estado de ataque para o efeito visual
        this.alternarEstadoAtaque();

        // A lógica de ataque real será implementada na Fase 4.
    }

    // O método carregarImagem() foi movido para Personagem.java.

    @Override
    public void coletar(Object recurso) {
        // Lógica de coleta será implementada na Fase 9
        System.out.println("Aldeão coletando recurso: " + recurso);
    }

}