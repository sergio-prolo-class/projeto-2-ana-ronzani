package ifsc.joe.domain.impl;

import ifsc.joe.enums.TipoRecurso;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Recurso {
    private final TipoRecurso tipo;
    private final int posX, posY;
    private final Image icone;
    private int quantidade;

    public Recurso(TipoRecurso tipo, int x, int y, int quantidade) {
        this.tipo = tipo;
        this.posX = x;
        this.posY = y;
        this.quantidade = quantidade;
        this.icone = carregarImagem(tipo.name().toLowerCase());
    }

    /**
     * desenha o recurso no painel
     * @param g Objeto Graphics.
     * @param painel O JPanel pai.
     */
    public void desenhar(Graphics g, JPanel painel) {
        g.drawImage(this.icone, this.posX, this.posY, painel);
        // Opcional: desenhar a quantidade
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(quantidade), posX + 10, posY + 10);
    }

    /**
     * tenta coletar uma quantidade do recurso
     * @param valor A quantidade a ser coletada.
     * @return A quantidade realmente coletada.
     */
    public int coletar(int valor) {
        int coletado = Math.min(valor, this.quantidade);
        this.quantidade -= coletado;
        return coletado;
    }

    public boolean estaEsgotado() {
        return this.quantidade <= 0;
    }

    private Image carregarImagem(String nome) {
        try {
            return new ImageIcon(Objects.requireNonNull(
                    getClass().getClassLoader().getResource(nome + ".png")
            )).getImage();
        } catch (NullPointerException e) {
            System.err.println("Erro ao carregar imagem do recurso: " + nome + ".png. Verifique o caminho.");
            return new ImageIcon().getImage();
        }
    }

    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public Image getIcone() { return icone; }
    public TipoRecurso getTipo() { return tipo; }
}
