/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mateu
 */
public class Sala {
    private String nome;
    private String recursos;
    private int capacidademaxima;
    private Predio pred;

    public Sala(String nome, String recursos, int capacidademaxima) {
        this.nome = nome;
        this.recursos = recursos;
        this.capacidademaxima = capacidademaxima;
    }

    public Sala() {
        
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public int getCapacidademaxima() {
        return capacidademaxima;
    }

    public void setCapacidademaxima(int capacidademaxima) {
        this.capacidademaxima = capacidademaxima;
    }

    public Predio getPred() {
        return pred;
    }

    public void setPred(Predio pred) {
        this.pred = pred;
    }
    
    
}
