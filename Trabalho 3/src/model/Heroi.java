/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author joeda
 */
public class Heroi{
    private int id;
    private String nome;
    private int velocidade;
    private int forca; 
    
    public Heroi() {
    }

    public Heroi(int id, String nome, int velocidade, int forca) {
        this.id = id;
        this.nome = nome;
        this.velocidade = velocidade;
        this.forca = forca;
    }
    
    @Override
    public String toString() {
        String resposta = this.getId() + "\n";
        resposta += this.getNome() + "\n";
        resposta += this.getVelocidade() + "\n";
        resposta += this.getForca() + "\n";
        return resposta;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the velocidade
     */
    public int getVelocidade() {
        return velocidade;
    }

    /**
     * @param velocidade the velocidade to set
     */
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    /**
     * @return the forca
     */
    public int getForca() {
        return forca;
    }

    /**
     * @param forca the forca to set
     */
    public void setForca(int forca) {
        this.forca = forca;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
