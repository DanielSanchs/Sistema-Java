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
public class CursosPesquisa extends AbstractTableModel{
    private List lista;

    public CursosPesquisa() {
    }
 
    public void setList(List list){
        this.lista = list;
        this.fireTableDataChanged();
    }
    
    public Cursos getRegistro(int linha){
        return (Cursos) lista.get(linha);
    }
    
    public String getColumnName(int num){
        if(num == 0){return "Código";}
        if(num == 1){return "Nome";}
        if(num == 2){return "Duração";}
        if(num == 3){return "Certificado";}
        if(num == 4){return "Nível";}
        if(num == 5){return "Módulos";}
        if(num == 6){return "Professor";}
        return "";
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cursos cursos = new Cursos();
        cursos = (Cursos) lista.get(rowIndex);
        if(columnIndex == 0){return cursos.getIdcursos();}
        if(columnIndex == 1){return cursos.getNome();}
        if(columnIndex == 2){return cursos.getDuracao();}
        if(columnIndex == 3){return cursos.getCertificado();}
        if(columnIndex == 4){return cursos.getNivel();}
        if(columnIndex == 5){return cursos.getModulos();}
        if(columnIndex == 6){return cursos.getProfessores();}
        
        return null;
    }
}
