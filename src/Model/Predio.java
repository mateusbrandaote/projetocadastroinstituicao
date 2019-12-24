/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mateu
 */
public class Predio {
    private String nome;
    private int id;
    ArrayList<Sala>ListaSala;

    public Predio() {
        ListaSala=new ArrayList();
    }
    
    public Predio(String nome, int id) {
        this.nome = nome;
        this.id = id;
        ListaSala=new ArrayList();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Sala> getListaSala() {
        return ListaSala;
    }

    public void setListaSala(ArrayList<Sala> ListaSala) {
        this.ListaSala = ListaSala;
    }
    public void addsala(Sala s){
        s.setPred(this);
        ListaSala.add(s);
    }
     

}
    

    

    
    
    
    
    

  

