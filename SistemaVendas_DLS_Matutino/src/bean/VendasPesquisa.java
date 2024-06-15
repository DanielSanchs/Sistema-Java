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
public class VendasPesquisa extends AbstractTableModel{
    private List lista;

    public VendasPesquisa() {
    }
 
    public void setList(List list){
        this.lista = list;
        this.fireTableDataChanged();
    }
    
    public Vendas getRegistro(int linha){
        return (Vendas) lista.get(linha);
    }
    
    public String getColumnName(int num){
        if(num == 0){return "Código";}
        if(num == 1){return "Cliente";}
        if(num == 2){return "Usuario";}
        if(num == 3){return "DataVenda";}
        if(num == 4){return "Total";}
        return "";
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vendas vendas = new Vendas();
        vendas = (Vendas) lista.get(rowIndex);
        if(columnIndex == 0){return vendas.getIdvendas();}
        if(columnIndex == 1){return vendas.getClientes();}
        if(columnIndex == 2){return vendas.getUsuarios();}
        if(columnIndex == 3){return vendas.getDataVenda();}
        if(columnIndex == 4){return vendas.getTotal();}
        
        return null;
    }
}
