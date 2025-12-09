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

    }

    // O método carregarImagem() foi movido para Personagem.java.

    @Override
    public void coletar(Object recurso) {
        System.out.println("Aldeão coletando recurso: " + recurso);
    }

}