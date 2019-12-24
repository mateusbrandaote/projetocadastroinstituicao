/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author mateu
 */
public class Disciplina {

   private String nome;
    private int Código;
    ArrayList<Turma>ListaTurma;

    public Disciplina() {
        ListaTurma=new ArrayList();
    }
    
    public Disciplina(String nome, int codigo) {
        this.nome = nome;
        this.Código = codigo;
        ListaTurma=new ArrayList();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCódigo() {
        return Código;
    }

    public void setCódigo(int Código) {
        this.Código = Código;
    }

    public ArrayList<Turma> getListaTurma() {
        return ListaTurma;
    }

    public void setListaTurma(ArrayList<Turma> ListaTurma) {
        this.ListaTurma = ListaTurma;
    }

    
    public void addturma(Turma t){
        t.setDisciplina(this);
        ListaTurma.add(t);
    }
     
}