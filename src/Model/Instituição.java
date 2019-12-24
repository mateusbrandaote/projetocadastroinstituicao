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
public class Instituição {
    String Nome;
    String Cidade;
    String Bairro;
    String Telefone;

    public Instituição() {
    }

    public Instituição(String Nome, String Cidade, String Bairro, String Telefone) {
        this.Nome = Nome;
        this.Cidade = Cidade;
        this.Bairro = Bairro;
        this.Telefone = Telefone;
    }
    

   

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
    
}
