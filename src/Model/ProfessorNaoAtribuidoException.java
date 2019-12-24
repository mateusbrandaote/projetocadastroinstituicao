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
public class ProfessorNaoAtribuidoException extends Exception  { 
@Override
public String getMessage(){
return "Professor Não atribuido";
}
}
