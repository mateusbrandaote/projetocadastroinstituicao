/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Model.Agendamento;
import Model.Disciplina;
import Model.DisciplinaNaoinformadaException;
import Model.Instituição;
import Model.Periodo;
import Model.Predio;
import Model.ProfessorNaoAtribuidoException;
import Model.Sala;
import Model.Turma;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateu
 */
public class menu extends javax.swing.JFrame {
    //criacao de lista de predios,turmas,discipinas e salas
    ArrayList<Predio>Listapred;
    ArrayList<Sala>Listasala;
    ArrayList<Turma>Listaturma;
    ArrayList<Disciplina>Listadisciplina;
    ArrayList<Agendamento>Listaagendamento;
    ArrayList<Periodo>Listaperiodo;
    Periodo P;
    String modopred;
    String modosala;
    String modoturma;
    String mododisciplina;
    String modoagendamento;
    String modoinstituicao;
    //carreaga a lista de predios
    public void LoadTablePred(){
     //cria um modelo de tabela para os predios com o id e o nome    
     DefaultTableModel modelo=new DefaultTableModel(new Object []{"id","Nome"},0);
     //implementa esse novo modelo de tabela
     tbl_preds.setModel(modelo);
     //coloca cada predio da lista em uma linha na tabela
     for(int i=0;i<Listapred.size();i++){
        
         modelo.addRow(new Object []{Listapred.get(i).getId(),Listapred.get(i).getNome()});//add a linha
     }       
     //otimiza o espaçamento
       tbl_preds.getColumnModel().getColumn(0).setPreferredWidth(50);
       tbl_preds.getColumnModel().getColumn(1).setPreferredWidth(200);
        LoadCBPred();//caso ocorra alguma edicao nos predios essa edicao vai para o cb das salas
        LoadCBPredioagendamento();//recarrega os predios na guia de agendamento
        LoadCBHoraioagendamento();
        LoadCBDiaagendamento();
        
    }
    //carrega a lista de salas
    public void LoadTableSala(){
     //cria um modelo de tabela para as salas com o nome ,recursos e capacidade maxima;    
     DefaultTableModel modelo=new DefaultTableModel(new Object []{"Nome","Recursos","CapMáx","Prédio"},0);
     //implementa esse novo modelo de tabela
     tbl_salas.setModel(modelo);
     //coloca cada sala da lista em uma linha na tabela
     for(int i=0;i<Listasala.size();i++){
        
         modelo.addRow(new Object []{Listasala.get(i).getNome(),Listasala.get(i).getRecursos(),Listasala.get(i).getCapacidademaxima(),Listasala.get(i).getPred().getNome()});//add a linha
     }       
     //otimiza o espaçamento
       tbl_salas.getColumnModel().getColumn(0).setPreferredWidth(200);
       tbl_salas.getColumnModel().getColumn(1).setPreferredWidth(200);
       tbl_salas.getColumnModel().getColumn(2).setPreferredWidth(50);
       tbl_salas.getColumnModel().getColumn(3).setPreferredWidth(200);
       LoadCBSalaagendamento();//recarrega as salas na guia dos agendamento 
    }
    //carrega a lista de turmas
    public void LoadTableTurma(){
    //cria um modelo de tabela para as turmas nome o codigo e o professor    
     DefaultTableModel modelo=new DefaultTableModel(new Object []{"Nome","Professor","Disciplina"},0);
     //implementa esse novo modelo de tabela
      tbl_turmas.setModel(modelo);
     //coloca cada turma da lista em uma linha na tabela
       for(int i=0;i<Listaturma.size();i++){
        
          modelo.addRow(new Object []{Listaturma.get(i).getNome(),Listaturma.get(i).getProfessor(),Listaturma.get(i).getDisciplina().getNome()});//add a linha
       }       
     //otimiza o espaçamento
       tbl_turmas.getColumnModel().getColumn(0).setPreferredWidth(200);
       tbl_turmas.getColumnModel().getColumn(1).setPreferredWidth(200);
       tbl_turmas.getColumnModel().getColumn(2).setPreferredWidth(200);
       LoadCBturmaagendamento();//atualiza as turmas na guia agendamento 
    
    }
    //carrega a lista de Disciplinas
    public void LoadTableDisciplina(){
    //cria um modelo de tabela para as disciplinas com nome e o codigo     
     DefaultTableModel modelo=new DefaultTableModel(new Object []{"Nome","Código"},0);
     //implementa esse novo modelo de tabela
     tbl_disciplinas.setModel(modelo);
     //coloca cada disciplina da lista em uma linha na tabela
     for(int i=0;i<Listadisciplina.size();i++){
        
         modelo.addRow(new Object []{Listadisciplina.get(i).getNome(),Listadisciplina.get(i).getCódigo()});//add a linha
     }       
     //otimiza o espaçamento
       tbl_disciplinas.getColumnModel().getColumn(0).setPreferredWidth(200);
       tbl_disciplinas.getColumnModel().getColumn(1).setPreferredWidth(50);
       LoadCBDisciplina();//recarrega e Cb das turmas caso tinha alguma alteracao nas disciplinas
       
    }
    public void LoadTableAgendamento(){
    //cria um modelo de tabela para os agendamentos com sala,predio,dia,hora e turma    
     DefaultTableModel modelo=new DefaultTableModel(new Object []{"Sala","Prédio","Horário","Dia","Turma"},0);
     //implementa esse novo modelo de tabela
      tbl_agendamentos.setModel(modelo);
     //coloca cada agendamento da lista em uma linha na tabela
       for(int i=0;i<Listaagendamento.size();i++){
        
          modelo.addRow(new Object []{Listaagendamento.get(i).getSala().getNome(),Listaagendamento.get(i).getPredio().getNome(),Listaagendamento.get(i).getHorario(),Listaagendamento.get(i).getDia(),Listaagendamento.get(i).getTurma().getNome()});//add a linha
           
       }       
     //otimiza o espaçamento
       tbl_turmas.getColumnModel().getColumn(0).setPreferredWidth(200);
       tbl_turmas.getColumnModel().getColumn(1).setPreferredWidth(200);
       tbl_turmas.getColumnModel().getColumn(2).setPreferredWidth(200);
    
    
    
    }
    //Coloca os prédios no CB da Sala
    public void LoadCBPred(){
     cb_sala_predios.removeAllItems();
     cb_sala_predios.addItem("Selecione");
     for(int i=0;i<Listapred.size();i++){
       cb_sala_predios.addItem(Listapred.get(i).getNome());//add cada predio da lista no cb das salas
     }       
     }
    /**
     * Creates new form menu
     */
    //coloca as disciplinas no Cb da turma
      public void LoadCBDisciplina(){
       cb_turma_disciplinas.removeAllItems();
       cb_turma_disciplinas.addItem("Selecione");
       for(int i=0;i<Listadisciplina.size();i++){
       cb_turma_disciplinas.addItem(Listadisciplina.get(i).getNome());//add cada predio da lista no cb das salas
        }       
    }
    //carrega a selecao de sala em agendamento
      public void LoadCBSalaagendamento(){
      cb_agendamento_sala.removeAllItems();
      cb_agendamento_sala.addItem("Selecione");
      for(int i=0;i<Listasala.size();i++){
      cb_agendamento_sala.addItem(Listasala.get(i).getNome());//add cada predio da lista no cb das salas
     }       
      
      }
    //carrega a selecao de predios em agendamentos
       public void LoadCBPredioagendamento(){
      cb_agendamento_predio.removeAllItems();
      cb_agendamento_predio.addItem("Selecione");
      for(int i=0;i<Listapred.size();i++){
      cb_agendamento_predio.addItem(Listapred.get(i).getNome());//add cada predio da lista no cb das salas
     }    
     } 
    //carrega a selecao de horarios em agendamento
       public void LoadCBHoraioagendamento(){
       cb_agendamento_horario.removeAllItems();
       cb_agendamento_horario.addItem("Selecione");
       cb_agendamento_horario.addItem(Listaperiodo.get(0).getHorarios()[0]);
       cb_agendamento_horario.addItem(Listaperiodo.get(0).getHorarios()[1]);
       cb_agendamento_horario.addItem(Listaperiodo.get(0).getHorarios()[2]);
       cb_agendamento_horario.addItem(Listaperiodo.get(0).getHorarios()[3]);
       cb_agendamento_horario.addItem(Listaperiodo.get(0).getHorarios()[4]);
       }
    // carrega a seleção de dia em agendamento
       public void LoadCBDiaagendamento(){
       cb_agendamento_dia.removeAllItems();
       cb_agendamento_dia.addItem("Selecione");
       cb_agendamento_dia.addItem(Listaperiodo.get(0).getDias()[0]);
       cb_agendamento_dia.addItem(Listaperiodo.get(0).getDias()[1]);
       cb_agendamento_dia.addItem(Listaperiodo.get(0).getDias()[2]);
       cb_agendamento_dia.addItem(Listaperiodo.get(0).getDias()[3]);
       cb_agendamento_dia.addItem(Listaperiodo.get(0).getDias()[4]);
       cb_agendamento_dia.addItem(Listaperiodo.get(0).getDias()[5]);
       cb_agendamento_dia.addItem(Listaperiodo.get(0).getDias()[6]);;
       
       }
    // carrega a seleção de turma em agendamento  
       public void LoadCBturmaagendamento(){
       cb_agendamento_turma.removeAllItems();
       cb_agendamento_turma.addItem("Selecione");
       for(int i=0;i<Listaturma.size();i++){
       cb_agendamento_turma.addItem(Listaturma.get(i).getNome());//add cada predio da lista no cb das salas
        }  
       
       }
    public menu() {
        initComponents();
        //centraliza a tela
        setLocationRelativeTo(null);
        //inicialização da lista dos predios ,das salas,das turmas e das disciplinas 
        Listapred=new ArrayList();
        Listasala=new ArrayList();
        Listaturma=new ArrayList();
        Listadisciplina=new ArrayList();
        Listaagendamento=new ArrayList();
        Listaperiodo=new ArrayList();
        P=new Periodo();
        Listaperiodo.add(P);
        modopred="Navegar";//icinializa a interface dos predios no modo navegar
        modosala="Navegar";//icinializa a interface das salas no modo navegar
        modoturma="Navegar";//icinializa a interface das turmas no modo navegar
        mododisciplina="Navegar";//icinializa a interface das disciplinas no modo navegar
        modoagendamento="Navegar";
        modoinstituicao="Navegar";
        //chama os manipuladores das interfaces
        ManipulaInterfacePred();
        ManipulaInterfaceSala();
        ManipulaInterfaceDisciplina();
        ManipulaInterfaceTurma();
        ManipulaInterfaceAgendamento();
        ManipulaInterfaceInstituicao();
       }
    //manipula a guia de predios
    public void ManipulaInterfacePred(){
     //controla as liberaçoes de botoes da interface
        switch(modopred){
        case "Navegar":
           
        btn_pred_salvar.setEnabled(false);//trava botao salvar
        btn_pred_cancelar.setEnabled(false);//trava botao cancelar
        C_pred_id.setEnabled(false);//trava campo id 
        C_pred_nome.setEnabled(false);//trava campo nome
        btn_pred_novo.setEnabled(true);//libera botao novo
        btn_pred_editar.setEnabled(false);//trava botao editar
        btn_predio_excluir.setEnabled(false);//trava botao excluir
            break;
        case "Novo":
            btn_pred_salvar.setEnabled(true);
        btn_pred_cancelar.setEnabled(true);
        C_pred_id.setEnabled(true);
        C_pred_nome.setEnabled(true);
        btn_pred_novo.setEnabled(false);
        btn_pred_editar.setEnabled(false);
        btn_predio_excluir.setEnabled(false);
            
            break;
        case "Edit":
            btn_pred_salvar.setEnabled(true);
        btn_pred_cancelar.setEnabled(true);
        C_pred_id.setEnabled(true);
        C_pred_nome.setEnabled(true);
        btn_pred_novo.setEnabled(false);
        btn_pred_editar.setEnabled(false);
        btn_predio_excluir.setEnabled(false);
            
            break;
        case "Excluir":
            btn_pred_salvar.setEnabled(false);
        btn_pred_cancelar.setEnabled(false);
        C_pred_id.setEnabled(false);
        C_pred_nome.setEnabled(false);
        btn_pred_novo.setEnabled(true);
        btn_pred_editar.setEnabled(false);
        btn_predio_excluir.setEnabled(false);
            
            
            break;
         case "Selecao":
            btn_pred_salvar.setEnabled(false);
        btn_pred_cancelar.setEnabled(false);
        C_pred_id.setEnabled(false);
        C_pred_nome.setEnabled(false);
        btn_pred_novo.setEnabled(true);
        btn_pred_editar.setEnabled(true);
        btn_predio_excluir.setEnabled(true);
            
            
            break;   
        default :System.out.println("modo inválido");    
      
    
    
    
    }
    }
    //manipula a guia de salas
    public void ManipulaInterfaceSala(){
     //controla as liberaçoes de botoes da interface da sala
        switch(modosala){
        case "Navegar":
           
        btn_sala_salvar.setEnabled(false);//trava botao salvar
        btn_sala_cancelar.setEnabled(false);//trava botao cancelar
        C_sala_nome.setEnabled(false);//trava campo nome
        C_sala_recursos.setEnabled(false);//trava campo recursos
        C_sala_capmax.setEnabled(false);//trava campo capmax
        btn_sala_novo.setEnabled(true);//libera botao novo
        btn_sala_editar.setEnabled(false);//trava botao editar
        btn_sala_excluir.setEnabled(false);//trava botao excluir
        cb_sala_predios.setEnabled(false);
            break;
        case "Novo":
          btn_sala_salvar.setEnabled(true);
        btn_sala_cancelar.setEnabled(true);
        C_sala_nome.setEnabled(true);
        C_sala_recursos.setEnabled(true);
        C_sala_capmax.setEnabled(true);
        btn_sala_novo.setEnabled(false);
        btn_sala_editar.setEnabled(false);
        btn_sala_excluir.setEnabled(false);
        cb_sala_predios.setEnabled(true);
           break;
        case "Edit":
         btn_sala_salvar.setEnabled(true);
        btn_sala_cancelar.setEnabled(true);
        C_sala_nome.setEnabled(true);
        C_sala_recursos.setEnabled(true);
        C_sala_capmax.setEnabled(true);
        btn_sala_novo.setEnabled(false);
        btn_sala_editar.setEnabled(false);
        btn_sala_excluir.setEnabled(false);
         cb_sala_predios.setEnabled(true);   
            break;
        case "Excluir":
           btn_sala_salvar.setEnabled(false);
        btn_sala_cancelar.setEnabled(false);
        C_sala_nome.setEnabled(false);
        C_sala_recursos.setEnabled(false);
        C_sala_capmax.setEnabled(false);
        btn_sala_novo.setEnabled(true);
        btn_sala_editar.setEnabled(false);
        btn_sala_excluir.setEnabled(false);
        cb_sala_predios.setEnabled(false);
            
            
            break;
         case "Selecao":
          btn_sala_salvar.setEnabled(false);
        btn_sala_cancelar.setEnabled(false);
        C_sala_nome.setEnabled(false);
        C_sala_recursos.setEnabled(false);
        C_sala_capmax.setEnabled(false);
        btn_sala_novo.setEnabled(true);
        btn_sala_editar.setEnabled(true);
        btn_sala_excluir.setEnabled(true);
        cb_sala_predios.setEnabled(false);    
            
            
            break;   
        default :System.out.println("modo inválido");    
      
    
    
    
    }
    }
    //manipula a guia de turmas
    public void ManipulaInterfaceTurma(){
     switch(modoturma){
     case "Navegar":
           
        btn_turma_salvar.setEnabled(false);//trava botao salvar
        btn_turma_cancelar.setEnabled(false);//trava botao cancelar
        C_turma_nome.setEnabled(false);//trava campo nome
        C_turma_professor.setEnabled(false);//trava campo recursos
        cb_turma_disciplinas.setEnabled(false);//trava campo capmax
        btn_turma_novo.setEnabled(true);//libera botao novo
        btn_turma_excluir.setEnabled(false);//trava botao excluir
        break;
        case "Novo":
         btn_turma_salvar.setEnabled(true);//trava botao salvar
         btn_turma_cancelar.setEnabled(true);//trava botao cancelar
         C_turma_nome.setEnabled(true);//trava campo nome
        cb_turma_disciplinas.setEnabled(true);//trava campo recursos
        C_turma_professor.setEnabled(true);//trava campo capmax
        btn_turma_novo.setEnabled(false);//libera botao novo
        btn_turma_excluir.setEnabled(false);//trava botao excluir
            break;
         case "Excluir":
          btn_turma_salvar.setEnabled(false);//trava botao salvar
          btn_turma_cancelar.setEnabled(false);//trava botao cancelar
          C_turma_nome.setEnabled(false);//trava campo nome
          cb_turma_disciplinas.setEnabled(false);//trava campo recursos
          C_turma_professor.setEnabled(false);//trava campo capmax
          btn_turma_novo.setEnabled(true);//libera botao novo
          btn_turma_excluir.setEnabled(false);//trava botao excluir
           break;
         case "Selecao":
          btn_turma_salvar.setEnabled(false);//trava botao salvar
          btn_turma_cancelar.setEnabled(false);//trava botao cancelar
          C_turma_nome.setEnabled(false);//trava campo nome
          cb_turma_disciplinas.setEnabled(false);//trava campo recursos
          C_turma_professor.setEnabled(false);//trava campo capmax
          btn_turma_novo.setEnabled(true);//libera botao novo
          btn_turma_excluir.setEnabled(true);//trava botao excluir
             break;   
        default :System.out.println("modo inválido");    
       }
}
     //manipula a guia de Disciplinas
    public void ManipulaInterfaceDisciplina(){
    switch(mododisciplina){
     case "Navegar":
           
       btn_disciplina_salvar.setEnabled(false);//trava botao salvar
        btn_disciplina_cancelar.setEnabled(false);//trava botao cancelar
        C_disciplina_nome.setEnabled(false);//trava campo nome
        C_disciplina_codigo.setEnabled(false);
        btn_disciplina_novo.setEnabled(true);//libera botao novo
        btn_disciplina_excluir.setEnabled(false);//trava botao excluir
        
            break;
        case "Novo":
        btn_disciplina_salvar.setEnabled(true);
        btn_disciplina_cancelar.setEnabled(true);
        C_disciplina_nome.setEnabled(true);
        C_disciplina_codigo.setEnabled(true);
        btn_disciplina_novo.setEnabled(false);
        btn_disciplina_excluir.setEnabled(true);
       
           break;
        
        case "Excluir":
        btn_sala_salvar.setEnabled(false);
        btn_sala_cancelar.setEnabled(false);
        C_sala_nome.setEnabled(false);
        C_disciplina_codigo.setEnabled(false);
        btn_sala_novo.setEnabled(true);
        btn_sala_excluir.setEnabled(false);
         break;
         case "Selecao":
        btn_sala_salvar.setEnabled(false);
        btn_sala_cancelar.setEnabled(false);
        C_sala_nome.setEnabled(false);
        C_disciplina_codigo.setEnabled(false);
        btn_sala_novo.setEnabled(true);
        btn_sala_excluir.setEnabled(true);
         
            
            
           // break;   
        //default :System.out.println("modo inválido");  
    }
    }
    //manipula a guia dos agendamento
    public void ManipulaInterfaceAgendamento(){
     switch(modoagendamento){
     case "Navegar":
           
        btn_agendamento_salvar.setEnabled(false);
        btn_agendamento_cancelar.setEnabled(false);
        btn_turma_novo.setEnabled(true);
        btn_turma_excluir.setEnabled(false);
        cb_agendamento_sala.setEnabled(false);
        cb_agendamento_predio.setEnabled(false);
        cb_agendamento_turma.setEnabled(false);
        cb_agendamento_dia.setEnabled(false);
        cb_agendamento_horario.setEnabled(false);
        break;
        case "Novo":
         btn_agendamento_salvar.setEnabled(true);
        btn_agendamento_cancelar.setEnabled(true);
        btn_turma_novo.setEnabled(false);
        btn_turma_excluir.setEnabled(false);
        cb_agendamento_sala.setEnabled(true);
        cb_agendamento_predio.setEnabled(true);
        cb_agendamento_turma.setEnabled(true);
        cb_agendamento_dia.setEnabled(true);
        cb_agendamento_horario.setEnabled(true);
            break;
         case "Excluir":
         btn_agendamento_salvar.setEnabled(false);
        btn_agendamento_cancelar.setEnabled(false);
        btn_turma_novo.setEnabled(true);
        btn_turma_excluir.setEnabled(false);
        cb_agendamento_sala.setEnabled(false);
        cb_agendamento_predio.setEnabled(false);
        cb_agendamento_turma.setEnabled(false);
        cb_agendamento_dia.setEnabled(false);
        cb_agendamento_horario.setEnabled(false);
           break;
         case "Selecao":
          btn_agendamento_salvar.setEnabled(false);
        btn_agendamento_cancelar.setEnabled(false);
        btn_turma_novo.setEnabled(true);
        btn_turma_excluir.setEnabled(true);
        cb_agendamento_sala.setEnabled(false);
        cb_agendamento_predio.setEnabled(false);
        cb_agendamento_turma.setEnabled(false);
        cb_agendamento_dia.setEnabled(false);
        cb_agendamento_horario.setEnabled(false);
             break;   
        default :System.out.println("modo inválido");    
       }
    
    
    
    
    }
    public void ManipulaInterfaceInstituicao(){
    switch(modoinstituicao){
    case "Navegar":
           
        btn_instituicao_salvar.setEnabled(false);
        btn_instituicao_cancelar.setEnabled(false);
        btn_instituicao_novo.setEnabled(true);
        C_instituicao_nome.setEnabled(false);
        C_instituicao_cidade.setEnabled(false);
        C_instituicao_bairro.setEnabled(false);
        C_instituicao_telefone.setEnabled(false);
        break;
        case "Novo":
          btn_instituicao_salvar.setEnabled(true);
        btn_instituicao_cancelar.setEnabled(true);
        btn_instituicao_novo.setEnabled(false);
        C_instituicao_nome.setEnabled(true);
        C_instituicao_cidade.setEnabled(true);
        C_instituicao_bairro.setEnabled(true);
        C_instituicao_telefone.setEnabled(true);
            break;
         case "Salvar":
          btn_instituicao_salvar.setEnabled(false);
        btn_instituicao_cancelar.setEnabled(false);
        btn_instituicao_novo.setEnabled(false);
        C_instituicao_nome.setEnabled(false);
        C_instituicao_cidade.setEnabled(false);
        C_instituicao_bairro.setEnabled(false);
        C_instituicao_telefone.setEnabled(false);
           break;
           default :System.out.println("modo inválido");    
    }
    
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        C_instituicao_nome = new javax.swing.JTextField();
        C_instituicao_cidade = new javax.swing.JTextField();
        btn_instituicao_salvar = new javax.swing.JButton();
        btn_instituicao_cancelar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        C_instituicao_bairro = new javax.swing.JTextField();
        C_instituicao_telefone = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btn_instituicao_novo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_preds = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        C_pred_id = new javax.swing.JTextField();
        C_pred_nome = new javax.swing.JTextField();
        btn_pred_salvar = new javax.swing.JButton();
        btn_pred_cancelar = new javax.swing.JButton();
        btn_pred_novo = new javax.swing.JButton();
        btn_pred_editar = new javax.swing.JButton();
        btn_predio_excluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btn_sala_novo = new javax.swing.JButton();
        btn_sala_editar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_salas = new javax.swing.JTable();
        btn_sala_excluir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        C_sala_nome = new javax.swing.JTextField();
        C_sala_recursos = new javax.swing.JTextField();
        btn_sala_salvar = new javax.swing.JButton();
        btn_sala_cancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        C_sala_capmax = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cb_sala_predios = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btn_disciplina_novo = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_disciplinas = new javax.swing.JTable();
        btn_disciplina_excluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        C_disciplina_nome = new javax.swing.JTextField();
        C_disciplina_codigo = new javax.swing.JTextField();
        btn_disciplina_salvar = new javax.swing.JButton();
        btn_disciplina_cancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btn_turma_novo = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_turmas = new javax.swing.JTable();
        btn_turma_excluir = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        C_turma_nome = new javax.swing.JTextField();
        C_turma_professor = new javax.swing.JTextField();
        btn_turma_salvar = new javax.swing.JButton();
        btn_turma_cancelar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cb_turma_disciplinas = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        btn_agendamento_novo = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_agendamentos = new javax.swing.JTable();
        btn_agendamento_excluir = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_agendamento_salvar = new javax.swing.JButton();
        btn_agendamento_cancelar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cb_agendamento_dia = new javax.swing.JComboBox<>();
        cb_agendamento_sala = new javax.swing.JComboBox<>();
        cb_agendamento_predio = new javax.swing.JComboBox<>();
        Horário = new javax.swing.JLabel();
        cb_agendamento_horario = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cb_agendamento_turma = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Prédio"));

        jLabel16.setText("Nome:");

        jLabel17.setText("Cidade:");

        C_instituicao_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_instituicao_nomeActionPerformed(evt);
            }
        });

        btn_instituicao_salvar.setText("Salvar");
        btn_instituicao_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_instituicao_salvarActionPerformed(evt);
            }
        });

        btn_instituicao_cancelar.setText("Cancelar");
        btn_instituicao_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_instituicao_cancelarActionPerformed(evt);
            }
        });

        jLabel18.setText("Bairro:");

        jLabel19.setText("Tefefone:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_instituicao_salvar)
                                .addGap(193, 193, 193)
                                .addComponent(btn_instituicao_cancelar))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(C_instituicao_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(361, 361, 361))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(C_instituicao_bairro)
                            .addComponent(C_instituicao_cidade, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                            .addComponent(C_instituicao_telefone))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(C_instituicao_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(C_instituicao_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(C_instituicao_bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(C_instituicao_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_instituicao_salvar)
                    .addComponent(btn_instituicao_cancelar))
                .addGap(23, 23, 23))
        );

        btn_instituicao_novo.setText("Novo");
        btn_instituicao_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_instituicao_novoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_instituicao_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 512, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_instituicao_novo)
                .addGap(276, 276, 276))
        );

        jTabbedPane1.addTab("Instituição", jPanel11);

        tbl_preds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_preds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_predsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_preds);
        if (tbl_preds.getColumnModel().getColumnCount() > 0) {
            tbl_preds.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbl_preds.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Prédio"));

        jLabel1.setText("Id:");

        jLabel2.setText("Nome:");

        C_pred_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_pred_idActionPerformed(evt);
            }
        });

        btn_pred_salvar.setText("Salvar");
        btn_pred_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pred_salvarActionPerformed(evt);
            }
        });

        btn_pred_cancelar.setText("Cancelar");
        btn_pred_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pred_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_pred_salvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                        .addComponent(btn_pred_cancelar))
                    .addComponent(C_pred_nome)
                    .addComponent(C_pred_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(C_pred_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(C_pred_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pred_salvar)
                    .addComponent(btn_pred_cancelar))
                .addGap(23, 23, 23))
        );

        btn_pred_novo.setText("Novo");
        btn_pred_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pred_novoActionPerformed(evt);
            }
        });

        btn_pred_editar.setText("Editar");
        btn_pred_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pred_editarActionPerformed(evt);
            }
        });

        btn_predio_excluir.setText("Excluir");
        btn_predio_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_predio_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_pred_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(btn_pred_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btn_predio_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(191, 191, 191))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pred_novo)
                    .addComponent(btn_pred_editar)
                    .addComponent(btn_predio_excluir))
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        jTabbedPane1.addTab("Prédios", jPanel1);

        btn_sala_novo.setText("Novo");
        btn_sala_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sala_novoActionPerformed(evt);
            }
        });

        btn_sala_editar.setText("Editar");
        btn_sala_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sala_editarActionPerformed(evt);
            }
        });

        tbl_salas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Recursos", "CapMáx", "Prédio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_salas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_salasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_salas);
        if (tbl_salas.getColumnModel().getColumnCount() > 0) {
            tbl_salas.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbl_salas.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbl_salas.getColumnModel().getColumn(2).setPreferredWidth(50);
            tbl_salas.getColumnModel().getColumn(2).setHeaderValue("CapMáx");
            tbl_salas.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        btn_sala_excluir.setText("Excluir");
        btn_sala_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sala_excluirActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sala"));

        jLabel3.setText("Nome:");

        jLabel4.setText("Recursos:");

        C_sala_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_sala_nomeActionPerformed(evt);
            }
        });

        btn_sala_salvar.setText("Salvar");
        btn_sala_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sala_salvarActionPerformed(evt);
            }
        });

        btn_sala_cancelar.setText("Cancelar");
        btn_sala_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sala_cancelarActionPerformed(evt);
            }
        });

        jLabel5.setText("CapMáx:");

        jLabel6.setText("Prédio:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cb_sala_predios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btn_sala_salvar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                                        .addComponent(btn_sala_cancelar))
                                    .addComponent(C_sala_recursos)
                                    .addComponent(C_sala_nome)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(C_sala_capmax, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(C_sala_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(C_sala_recursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(C_sala_capmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cb_sala_predios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_sala_salvar)
                    .addComponent(btn_sala_cancelar))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_sala_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(btn_sala_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btn_sala_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(191, 191, 191))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_sala_novo)
                    .addComponent(btn_sala_editar)
                    .addComponent(btn_sala_excluir))
                .addGap(30, 30, 30)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        jTabbedPane1.addTab("Salas", jPanel2);

        btn_disciplina_novo.setText("Novo");
        btn_disciplina_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_disciplina_novoActionPerformed(evt);
            }
        });

        tbl_disciplinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Código"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_disciplinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_disciplinasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_disciplinas);
        if (tbl_disciplinas.getColumnModel().getColumnCount() > 0) {
            tbl_disciplinas.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbl_disciplinas.getColumnModel().getColumn(1).setPreferredWidth(50);
            tbl_disciplinas.getColumnModel().getColumn(1).setHeaderValue("CapMáx");
        }

        btn_disciplina_excluir.setText("Excluir");
        btn_disciplina_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_disciplina_excluirActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sala"));

        jLabel7.setText("Nome:");

        C_disciplina_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_disciplina_nomeActionPerformed(evt);
            }
        });

        btn_disciplina_salvar.setText("Salvar");
        btn_disciplina_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_disciplina_salvarActionPerformed(evt);
            }
        });

        btn_disciplina_cancelar.setText("Cancelar");
        btn_disciplina_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_disciplina_cancelarActionPerformed(evt);
            }
        });

        jLabel8.setText("Código:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(C_disciplina_nome)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btn_disciplina_salvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                                .addComponent(btn_disciplina_cancelar))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(C_disciplina_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(C_disciplina_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(C_disciplina_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_disciplina_salvar)
                    .addComponent(btn_disciplina_cancelar))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_disciplina_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_disciplina_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(191, 191, 191))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_disciplina_novo)
                    .addComponent(btn_disciplina_excluir))
                .addGap(30, 30, 30)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        jTabbedPane1.addTab("Disciplinas", jPanel5);

        btn_turma_novo.setText("Novo");
        btn_turma_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_turma_novoActionPerformed(evt);
            }
        });

        tbl_turmas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Prof/Profs", "Disciplina"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_turmas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_turmasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_turmas);
        if (tbl_turmas.getColumnModel().getColumnCount() > 0) {
            tbl_turmas.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_turmas.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbl_turmas.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        btn_turma_excluir.setText("Excluir");
        btn_turma_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_turma_excluirActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sala"));

        jLabel9.setText("Nome:");

        jLabel10.setText("Prof/Profs:");

        C_turma_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_turma_nomeActionPerformed(evt);
            }
        });

        btn_turma_salvar.setText("Salvar");
        btn_turma_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_turma_salvarActionPerformed(evt);
            }
        });

        btn_turma_cancelar.setText("Cancelar");
        btn_turma_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_turma_cancelarActionPerformed(evt);
            }
        });

        jLabel12.setText("Disciplina:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(btn_turma_salvar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                                        .addComponent(btn_turma_cancelar))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(C_turma_professor))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(C_turma_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(cb_turma_disciplinas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(C_turma_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(C_turma_professor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cb_turma_disciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_turma_salvar)
                    .addComponent(btn_turma_cancelar))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_turma_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_turma_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 413, Short.MAX_VALUE))
                .addGap(191, 191, 191))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_turma_novo)
                    .addComponent(btn_turma_excluir))
                .addGap(30, 30, 30)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        jTabbedPane1.addTab("Turmas", jPanel7);

        btn_agendamento_novo.setText("Novo");
        btn_agendamento_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agendamento_novoActionPerformed(evt);
            }
        });

        tbl_agendamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sala", "Prédio", "Horário", "Dia", "Turma"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_agendamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_agendamentosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_agendamentos);
        if (tbl_agendamentos.getColumnModel().getColumnCount() > 0) {
            tbl_agendamentos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_agendamentos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbl_agendamentos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbl_agendamentos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbl_agendamentos.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        btn_agendamento_excluir.setText("Excluir");
        btn_agendamento_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agendamento_excluirActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sala"));

        jLabel11.setText("Sala:");

        jLabel13.setText("Prédio:");

        btn_agendamento_salvar.setText("Salvar");
        btn_agendamento_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agendamento_salvarActionPerformed(evt);
            }
        });

        btn_agendamento_cancelar.setText("Cancelar");
        btn_agendamento_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agendamento_cancelarActionPerformed(evt);
            }
        });

        jLabel14.setText("Dia:");

        cb_agendamento_sala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_agendamento_salaActionPerformed(evt);
            }
        });

        Horário.setText("Horário:");

        jLabel15.setText("Turma:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(btn_agendamento_salvar)
                                        .addGap(193, 193, 193)
                                        .addComponent(btn_agendamento_cancelar))
                                    .addComponent(cb_agendamento_sala, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_agendamento_predio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_agendamento_dia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(Horário)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_agendamento_horario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_agendamento_turma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(cb_agendamento_sala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cb_agendamento_predio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cb_agendamento_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Horário)
                    .addComponent(cb_agendamento_horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cb_agendamento_turma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agendamento_salvar)
                    .addComponent(btn_agendamento_cancelar))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btn_agendamento_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_agendamento_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(194, 194, 194))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agendamento_novo)
                    .addComponent(btn_agendamento_excluir))
                .addGap(30, 30, 30)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Agendamentos", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sala_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sala_cancelarActionPerformed
        modosala="Navegar";
        ManipulaInterfaceSala();
    }//GEN-LAST:event_btn_sala_cancelarActionPerformed

    private void btn_sala_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sala_salvarActionPerformed
        //forço o usuario a selecionar um prédio
        int index=cb_sala_predios.getSelectedIndex();
        if(index==0){
            JOptionPane.showMessageDialog(this,"Você deve selecionar um Prédio!");
        }else{
            //crio um novo Objeto de tipo sala com os dados dos campos nome ,recursos e capacidade máxima e o predio com o index selecionado do cb.
            Sala s =new Sala();
            s.setNome(C_sala_nome.getText());
            s.setRecursos(C_sala_recursos.getText());
            s.setCapacidademaxima(Integer.parseInt(C_sala_capmax.getText()));
            s.setPred(Listapred.get(index-1));
            Listasala.add(s);
            Listapred.get(index-1).addsala(s);//add essa sala a lista de pred
        }
        LoadTableSala();
        modosala="Navegar";
        ManipulaInterfaceSala();
    }//GEN-LAST:event_btn_sala_salvarActionPerformed

    private void C_sala_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_sala_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_sala_nomeActionPerformed

    private void btn_sala_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sala_excluirActionPerformed
        //modosala="Navegar";
        //ManipulaInterfaceSala();
    }//GEN-LAST:event_btn_sala_excluirActionPerformed

    private void tbl_salasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_salasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_salasMouseClicked

    private void btn_sala_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sala_editarActionPerformed
        modosala="Edit";
        ManipulaInterfaceSala();
    }//GEN-LAST:event_btn_sala_editarActionPerformed

    private void btn_sala_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sala_novoActionPerformed
        C_sala_nome.setText("");
        C_sala_recursos.setText("");
        C_sala_capmax.setText("");
        cb_sala_predios.setSelectedIndex(0);
        modosala="Novo";
        ManipulaInterfaceSala();
    }//GEN-LAST:event_btn_sala_novoActionPerformed

    private void btn_predio_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_predio_excluirActionPerformed
        // TODO add your handling code here:
        int index=tbl_preds.getSelectedRow();
        //confere se a linha selecionada é valida
        if(index>=0 && index<Listapred.size()){
            Listapred.remove(index);//ecluir a linha de acordo com o indice selecionado
        }

        LoadTablePred();
        modopred="Navegar";
        ManipulaInterfacePred();
    }//GEN-LAST:event_btn_predio_excluirActionPerformed

    private void btn_pred_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pred_editarActionPerformed
        // TODO add your handling code here:
        modopred="Edit";
        ManipulaInterfacePred();
    }//GEN-LAST:event_btn_pred_editarActionPerformed

    private void btn_pred_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pred_novoActionPerformed

        //limpa os campos do id e do nome
        C_pred_id.setText("");
        C_pred_nome.setText("");

        modopred="Novo";
        ManipulaInterfacePred();
    }//GEN-LAST:event_btn_pred_novoActionPerformed

    private void btn_pred_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pred_cancelarActionPerformed
        // TODO add your handling code here:
        //limpa os campos de id e nome
        C_pred_id.setText("");
        C_pred_nome.setText("");

        modopred="Navegar";
        ManipulaInterfacePred();
    }//GEN-LAST:event_btn_pred_cancelarActionPerformed

    private void btn_pred_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pred_salvarActionPerformed
        // TODO add your handling code here:
        //converte a string recebida no campo id para um inteiro
        int id=Integer.parseInt(C_pred_id.getText());
        //se o modo selecionado for novo
        if(modopred.equals("Novo")){
            //cria um objeto de tipo predio com o nome e o id do campo de preenchimento do nome e da id
            Predio P=new Predio(C_pred_nome.getText(),id);
            //adiciona esse objeto na lista
            Listapred.add(P);
            //se o modo for o edit
        }else if(modopred.equals("Edit")){
            //pega a linha selecionada
            int index=tbl_preds.getSelectedRow();
            //seta os novo valores na linha
            Listapred.get(index).setId(id);
            Listapred.get(index).setNome(C_pred_nome.getText());
        }
        //recarrega a lista com o predio adicionado
        LoadTablePred();
        C_pred_id.setText("");
        C_pred_nome.setText("");
        modopred="Navegar";
        ManipulaInterfacePred();
    }//GEN-LAST:event_btn_pred_salvarActionPerformed

    private void C_pred_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_pred_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_pred_idActionPerformed

    private void tbl_predsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_predsMouseClicked
        //seleciona uma linha da tabela
        int index=tbl_preds.getSelectedRow();
        //confere se a linha selecionada é valida
        if(index>=0 && index<Listapred.size()){
            //pego o objeto predio da linha selecionada
              Predio p=Listapred.get(index);
            //preencho o campo id e nome com os dados da linha
            C_pred_id.setText(String.valueOf(p.getId()));
            C_pred_nome.setText(p.getNome());
            modopred="Selecao";
            ManipulaInterfacePred();
             }
    }//GEN-LAST:event_tbl_predsMouseClicked

    private void btn_disciplina_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_disciplina_novoActionPerformed
        // TODO add your handling code here:
        C_disciplina_codigo.setText("");
        C_disciplina_nome.setText("");
        mododisciplina="Novo";
        ManipulaInterfaceDisciplina();
    }//GEN-LAST:event_btn_disciplina_novoActionPerformed

    private void tbl_disciplinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_disciplinasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_disciplinasMouseClicked

    private void btn_disciplina_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_disciplina_excluirActionPerformed
        // TODO add your handling code here:
        mododisciplina="Navegar";
        ManipulaInterfaceDisciplina();
    }//GEN-LAST:event_btn_disciplina_excluirActionPerformed

    private void C_disciplina_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_disciplina_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_disciplina_nomeActionPerformed

    private void btn_disciplina_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_disciplina_salvarActionPerformed
        // TODO add your handling code here:
        Disciplina d=new Disciplina();
        d.setNome(C_disciplina_nome.getText());
        d.setCódigo(Integer.parseInt(C_disciplina_codigo.getText()));
        Listadisciplina.add(d);
        LoadTableDisciplina();
        mododisciplina="Navegar";
        ManipulaInterfaceDisciplina();
        
    }//GEN-LAST:event_btn_disciplina_salvarActionPerformed

    private void btn_disciplina_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_disciplina_cancelarActionPerformed
        // TODO add your handling code here:
          C_disciplina_codigo.setText("");
        C_disciplina_nome.setText("");
        mododisciplina="Navegar";
        ManipulaInterfaceDisciplina();
    }//GEN-LAST:event_btn_disciplina_cancelarActionPerformed

    private void btn_turma_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_turma_novoActionPerformed
        C_turma_nome.setText("");
        C_turma_professor.setText("");
        cb_turma_disciplinas.setSelectedIndex(0);
        modoturma="Novo";
        ManipulaInterfaceTurma();
    }//GEN-LAST:event_btn_turma_novoActionPerformed

    private void tbl_turmasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_turmasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_turmasMouseClicked

    private void btn_turma_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_turma_excluirActionPerformed
         modoturma="Navegar";
        ManipulaInterfaceTurma();
                                  
    }//GEN-LAST:event_btn_turma_excluirActionPerformed

    private void C_turma_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_turma_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_turma_nomeActionPerformed

    private void btn_turma_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_turma_salvarActionPerformed
         //forço o usuario a selecionar uma disciplina para a turma
        int index=cb_turma_disciplinas.getSelectedIndex();
        if(index==0){
            try {
                throw new DisciplinaNaoinformadaException();
            } catch (DisciplinaNaoinformadaException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
           // JOptionPane.showMessageDialog(this, "É necessário informar ao menos um Professor!");
           JOptionPane.showMessageDialog(this, "É necessário informar ao menos uma disciplina!");
        }else if(C_turma_professor.getText().equalsIgnoreCase("")){
        
            try {
                throw new ProfessorNaoAtribuidoException();
            } catch (ProfessorNaoAtribuidoException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
         JOptionPane.showMessageDialog(this, "É necessário informar um Professor");
        }
        
        
        
        else{
            //crio um novo Objeto de tipo turma com os dados dos campos nome ,professor e a disciplina com o index selecionado do cb.
            Turma t =new Turma();
            t.setNome(C_turma_nome.getText());
            t.setProfessor(C_turma_professor.getText());
            t.setDisciplina(Listadisciplina.get(index-1));
            Listaturma.add(t);
            Listadisciplina.get(index-1).addturma(t);//add essa sala a lista de pred
        }
        LoadTableTurma();
        
        
        modoturma="Navegar";
        ManipulaInterfaceTurma();
    }//GEN-LAST:event_btn_turma_salvarActionPerformed

    private void btn_turma_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_turma_cancelarActionPerformed
       C_turma_nome.setText("");
        C_turma_professor.setText("");
        modoturma="Navegar";
        ManipulaInterfaceTurma();
    }//GEN-LAST:event_btn_turma_cancelarActionPerformed

    private void btn_agendamento_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agendamento_novoActionPerformed
        cb_agendamento_sala.setSelectedIndex(0);
        cb_agendamento_predio.setSelectedIndex(0);
        cb_agendamento_dia.setSelectedIndex(0);
        cb_agendamento_horario.setSelectedIndex(0);
        cb_agendamento_turma.setSelectedIndex(0);
        modoagendamento="Novo";
        ManipulaInterfaceAgendamento();
    }//GEN-LAST:event_btn_agendamento_novoActionPerformed

    private void tbl_agendamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_agendamentosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_agendamentosMouseClicked

    private void btn_agendamento_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agendamento_excluirActionPerformed
       modoagendamento="Navegar";
        ManipulaInterfaceAgendamento();
    }//GEN-LAST:event_btn_agendamento_excluirActionPerformed

    private void btn_agendamento_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agendamento_salvarActionPerformed
       int indexsala=cb_agendamento_sala.getSelectedIndex();
       int indexpredio=cb_agendamento_predio.getSelectedIndex();
       int indexdia=cb_agendamento_dia.getSelectedIndex();
       int indexhorario=cb_agendamento_horario.getSelectedIndex();
       int indexturma=cb_agendamento_turma.getSelectedIndex();
      
      
            if(indexsala==0){
            JOptionPane.showMessageDialog(this,"Você deve selecionar uma Disciplina!");
            }//crio um novo Objeto de tipo turma com os dados dos campos nome ,professor e a disciplina com o index selecionado do cb.
            else{Agendamento a=new Agendamento();
            a.setSala(Listasala.get(indexsala-1));
            a.setPredio(Listapred.get(indexpredio-1));
            String horario=P.getHorarios()[indexhorario-1];
            String dia=P.getDias()[indexdia-1];
            a.setHorario(horario);
            a.setDia(dia);
            a.setTurma(Listaturma.get(indexturma-1));
            Listaagendamento.add(a);
            LoadTableAgendamento();
            modoagendamento="Navegar";
            ManipulaInterfaceAgendamento();}
    }//GEN-LAST:event_btn_agendamento_salvarActionPerformed

    private void btn_agendamento_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agendamento_cancelarActionPerformed
        modoagendamento="Navegar";
        ManipulaInterfaceAgendamento();
    }//GEN-LAST:event_btn_agendamento_cancelarActionPerformed

    private void cb_agendamento_salaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_agendamento_salaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_agendamento_salaActionPerformed

    private void btn_instituicao_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_instituicao_salvarActionPerformed
       Instituição i=new Instituição();
       i.setNome(C_instituicao_nome.getText());
       i.setCidade(C_instituicao_cidade.getText());
       i.setBairro(C_instituicao_bairro.getText());
       i.setTelefone(C_instituicao_telefone.getText());
       
        
        modoinstituicao="Salvar";
        ManipulaInterfaceInstituicao();
    }//GEN-LAST:event_btn_instituicao_salvarActionPerformed

    private void btn_instituicao_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_instituicao_cancelarActionPerformed
       C_instituicao_nome.setText("");
        C_instituicao_cidade.setText("");
        C_instituicao_bairro.setText("");
        C_instituicao_telefone.setText("");
        modoinstituicao="Navegar";
        ManipulaInterfaceInstituicao();
    }//GEN-LAST:event_btn_instituicao_cancelarActionPerformed

    private void btn_instituicao_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_instituicao_novoActionPerformed
       modoinstituicao="Novo";
        ManipulaInterfaceInstituicao();
        
    }//GEN-LAST:event_btn_instituicao_novoActionPerformed

    private void C_instituicao_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_instituicao_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_instituicao_nomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField C_disciplina_codigo;
    private javax.swing.JTextField C_disciplina_nome;
    private javax.swing.JTextField C_instituicao_bairro;
    private javax.swing.JTextField C_instituicao_cidade;
    private javax.swing.JTextField C_instituicao_nome;
    private javax.swing.JTextField C_instituicao_telefone;
    private javax.swing.JTextField C_pred_id;
    private javax.swing.JTextField C_pred_nome;
    private javax.swing.JTextField C_sala_capmax;
    private javax.swing.JTextField C_sala_nome;
    private javax.swing.JTextField C_sala_recursos;
    private javax.swing.JTextField C_turma_nome;
    private javax.swing.JTextField C_turma_professor;
    private javax.swing.JLabel Horário;
    private javax.swing.JButton btn_agendamento_cancelar;
    private javax.swing.JButton btn_agendamento_excluir;
    private javax.swing.JButton btn_agendamento_novo;
    private javax.swing.JButton btn_agendamento_salvar;
    private javax.swing.JButton btn_disciplina_cancelar;
    private javax.swing.JButton btn_disciplina_excluir;
    private javax.swing.JButton btn_disciplina_novo;
    private javax.swing.JButton btn_disciplina_salvar;
    private javax.swing.JButton btn_instituicao_cancelar;
    private javax.swing.JButton btn_instituicao_novo;
    private javax.swing.JButton btn_instituicao_salvar;
    private javax.swing.JButton btn_pred_cancelar;
    private javax.swing.JButton btn_pred_editar;
    private javax.swing.JButton btn_pred_novo;
    private javax.swing.JButton btn_pred_salvar;
    private javax.swing.JButton btn_predio_excluir;
    private javax.swing.JButton btn_sala_cancelar;
    private javax.swing.JButton btn_sala_editar;
    private javax.swing.JButton btn_sala_excluir;
    private javax.swing.JButton btn_sala_novo;
    private javax.swing.JButton btn_sala_salvar;
    private javax.swing.JButton btn_turma_cancelar;
    private javax.swing.JButton btn_turma_excluir;
    private javax.swing.JButton btn_turma_novo;
    private javax.swing.JButton btn_turma_salvar;
    private javax.swing.JComboBox<String> cb_agendamento_dia;
    private javax.swing.JComboBox<String> cb_agendamento_horario;
    private javax.swing.JComboBox<String> cb_agendamento_predio;
    private javax.swing.JComboBox<String> cb_agendamento_sala;
    private javax.swing.JComboBox<String> cb_agendamento_turma;
    private javax.swing.JComboBox<String> cb_sala_predios;
    private javax.swing.JComboBox<String> cb_turma_disciplinas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbl_agendamentos;
    private javax.swing.JTable tbl_disciplinas;
    private javax.swing.JTable tbl_preds;
    private javax.swing.JTable tbl_salas;
    private javax.swing.JTable tbl_turmas;
    // End of variables declaration//GEN-END:variables
}
