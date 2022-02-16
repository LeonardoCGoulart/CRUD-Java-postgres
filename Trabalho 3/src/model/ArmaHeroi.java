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
public class ArmaHeroi{
    int id;
    private String nome;
    private int dano;
    private int municao; 
    private boolean longaDistancia; // arremessável? arma ou machado?
    
    public ArmaHeroi() {
    
    }

    public ArmaHeroi(int id, String nome, int dano, int municao, boolean longaDistancia) {
        this.id = id;
        this.nome = nome;
        this.dano = dano;
        this.municao = municao;
        this.longaDistancia = longaDistancia; 
    }
    
    @Override
    public String toString() {
        String resposta = this.getId() + "\n";
        resposta += this.getNome() + "\n";
        resposta += this.getMunicao() + "\n";
        resposta += this.isLongaDistancia() + "\n";
        return resposta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDano() {
        return dano;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getMunicao() {
        return municao;
    }

    public void setMunicao(int municao) {
        this.municao = municao;
    }

    public boolean isLongaDistancia() {
        return longaDistancia;
    }

    public void setLongaDistancia(boolean longaDistancia) {
        this.longaDistancia = longaDistancia;
    }


    
}
