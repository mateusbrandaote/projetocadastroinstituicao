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
public class Agendamento {
 ArrayList<Periodo>Listaperiodo; 
 Predio predio;
 Sala sala;
 Turma Turma;
 Periodo periodo;
 String Horario;
 String Dia;


    public Agendamento() {
    }

    public ArrayList<Periodo> getListaperido() {
        return Listaperiodo;
    }

    public void setListaperido(ArrayList<Periodo> Listaperido) {
        this.Listaperiodo = Listaperido;
    }
 
   void addperiodo(Periodo perido){
   Listaperiodo.add(perido);
   }

    public ArrayList<Periodo> getListaperiodo() {
        return Listaperiodo;
    }

    public void setListaperiodo(ArrayList<Periodo> Listaperiodo) {
        this.Listaperiodo = Listaperiodo;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Turma getTurma() {
        return Turma;
    }

    public void setTurma(Turma Turma) {
        this.Turma = Turma;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
    }

  


}
