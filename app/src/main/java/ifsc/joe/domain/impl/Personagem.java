package ifsc.joe.domain.impl;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Classe abstrata base para todos os personagens do jogo.
 * Centraliza atributos comuns como posição, vida, ataque, alcance e chance de esquiva.
 */
public abstract class Personagem {

    // Atributos de estado
    protected int posX, posY;
    protected boolean atacando;
    protected Image icone;
    protected int vidaAtual;
    protected int vidaMaxima;

    // Atributos de combate e movimento
    protected int ataque;
    protected int alcanceAtaque; // Distância máxima em pixels para atacar
    protected double chanceEsquiva; // Valor entre 0.0 e 1.0 (ex: 0.25 para 25%)
    protected int velocidade; // Pixels por movimento

    // Nome da imagem base (ex: "aldeao", "arqueiro")
    protected final String nomeImagemBase;

    public Personagem(int x, int y, String nomeImagemBase, int vidaMaxima, int ataque, int alcanceAtaque, double chanceEsquiva, int velocidade) {
        this.nomeImagemBase = nomeImagemBase;
        this.icone = this.carregarImagem(nomeImagemBase);
        this.posX = x;
        this.posY = y;
        this.atacando = false;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataque = ataque;
        this.alcanceAtaque = alcanceAtaque;
        this.chanceEsquiva = chanceEsquiva;
        this.velocidade = velocidade;
    }

    /**
     * Desenha o personagem no painel.
     * @param g Objeto Graphics.
     * @param painel O JPanel pai.
     */
    public void desenhar(Graphics g, JPanel painel) {
        // Lógica para alternar a imagem de ataque (se houver imagem "nomeImagemBase2.png")
        String nomeImagem = this.nomeImagemBase + (atacando ? "2" : "");
        this.icone = this.carregarImagem(nomeImagem);

        // Desenha a imagem
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }

    /**
     * Atualiza as coordenadas X e Y do personagem.
     * @param direcao indica a direcao a ir.
     * @param maxLargura limite de largura do painel.
     * @param maxAltura limite de altura do painel.
     */
    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        switch (direcao) {
            case CIMA     -> this.posY -= this.velocidade;
            case BAIXO    -> this.posY += this.velocidade;
            case ESQUERDA -> this.posX -= this.velocidade;
            case DIREITA  -> this.posX += this.velocidade;
        }

        // Não deixa a imagem ser desenhada fora dos limites do JPanel pai
        this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
        this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
    }

    /**
     * Alterna o estado de ataque.
     */
    public void alternarEstadoAtaque() {
        this.atacando = !this.atacando;
    }

    /**
     * Aplica dano ao personagem, considerando a chance de esquiva.
     * @param dano O valor do dano a ser aplicado.
     * @return true se o dano foi aplicado, false se o personagem esquivou.
     */
    public boolean sofrerDano(int dano) {
        if (Math.random() < this.chanceEsquiva) {
            // Esquivou
            return false;
        }
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) {
            this.vidaAtual = 0;
        }
        return true; // Dano aplicado
    }

    /**
     * Metodo auxiliar para carregar uma imagem do disco.
     * @param imagem Caminho da imagem (ex: "aldeao.png").
     * @return Retorna um objeto Image.
     */
    protected Image carregarImagem(String imagem) {
        try {
            return new ImageIcon(Objects.requireNonNull(
                    getClass().getClassLoader().getResource(imagem + ".png")
            )).getImage();
        } catch (NullPointerException e) {
            System.err.println("Erro ao carregar imagem: " + imagem + ".png. Verifique o caminho.");
            // Retorna uma imagem padrão ou nula em caso de erro
            return new ImageIcon().getImage();
        }
    }

    // Métodos Getters
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public int getVidaAtual() { return vidaAtual; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getAlcanceAtaque() { return alcanceAtaque; }
    public double getChanceEsquiva() { return chanceEsquiva; }
    public boolean estaMorto() { return vidaAtual <= 0; }
    public boolean estaAtacando() { return atacando; }

    // Métodos Abstratos ou que serão sobrescritos (opcionalmente)
    // public abstract void atacar(Set<Personagem> alvos);
}