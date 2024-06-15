/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Daniel Lopes
 */
public class PesquisarGrupo extends AbstractTableModel{
    
    @Override
    public int getRowCount() {
        return 4;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex == 0 && columnIndex == 0){return 1;}
        if(rowIndex == 0 && columnIndex == 1){return "calça";}
        if(rowIndex == 0 && columnIndex == 2){return 'S';}
        if(rowIndex == 1 && columnIndex == 0){return 2;}
        if(rowIndex == 1 && columnIndex == 1){return "meia";}
        if(rowIndex == 1 && columnIndex == 2){return 'S';}
        if(rowIndex == 2 && columnIndex == 0){return 3;}
        if(rowIndex == 2 && columnIndex == 1){return "saia";}
        if(rowIndex == 2 && columnIndex == 2){return 'N';}
        if(rowIndex == 3 && columnIndex == 0){return 4;}
        if(rowIndex == 3 && columnIndex == 1){return "teste";}
        if(rowIndex == 3 && columnIndex == 2){return 'S';}
        return null;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return "Código";
            case 1: return "Descrição";
            case 2: return "Ativo";
        }
        return null;
    }
}
