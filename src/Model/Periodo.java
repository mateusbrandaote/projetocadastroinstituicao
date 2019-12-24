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
public class Periodo {
   public String []dias={"Segunda","Terça","Quarta","Quinta","Sexta","Sábado","Domingo"};
   public String []horarios={"de 8:00 ás 10:00","de 10:00 ás 12:00","de 12:00 ás 14:00","de 14:00 ás 16:00","de 16:00 ás 18:00"};
 private int indexdia;
 private int indexhorario;
    public Periodo() {
    }
    
    
   public String[] getDias() {
        return dias;
    }

    public void setDias(String[] dias) {
        this.dias = dias;
    }

    public String[] getHorarios() {
        return horarios;
    }

    public void setHorarios(String[] horarios) {
        this.horarios = horarios;
    }

    public int getIndexdia() {
        return indexdia;
    }

    public void setIndexdia(int indexdia) {
        this.indexdia = indexdia;
    }

    public int getIndexhorario() {
        return indexhorario;
    }

    public void setIndexhorario(int indexhorario) {
        this.indexhorario = indexhorario;
    }
   



}

