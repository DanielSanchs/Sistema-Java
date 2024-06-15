/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Daniel Lopes
 */
public class ProfessoresPesquisa extends AbstractTableModel{
    private List lista;

    public ProfessoresPesquisa() {
    }
 
    public void setList(List list){
        this.lista = list;
        this.fireTableDataChanged();
    }
    
    public Professores getRegistro(int linha){
        return (Professores) lista.get(linha);
    }
    
    public String getColumnName(int num){
        if(num == 0){return "Código";}
        if(num == 1){return "Nome";}
        if(num == 2){return "Sexo";}
        if(num == 3){return "RG";}
        if(num == 4){return "CPF";}
        if(num == 5){return "DataN";}
        if(num == 6){return "Graduação";}
        if(num == 7){return "Especialização";}
        return "";
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Professores professores = new Professores();
        professores = (Professores) lista.get(rowIndex);
        if(columnIndex == 0){return professores.getIdprofessores();}
        if(columnIndex == 1){return professores.getNome();}
        if(columnIndex == 2){return professores.getSexo();}
        if(columnIndex == 3){return professores.getRg();}
        if(columnIndex == 4){return professores.getCpf();}
        if(columnIndex == 5){return professores.getDataNascimento();}
        if(columnIndex == 6){return professores.getGraduacao();}
        if(columnIndex == 7){return professores.getEspecializacao();}
        
        return null;
    }
}
