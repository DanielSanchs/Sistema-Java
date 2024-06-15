/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Daniel Lopes
 */
public class VendasCursosPesquisa extends AbstractTableModel {

    private List lista;

    public VendasCursosPesquisa() {
    }

    public void setList(List list) {
        this.lista = list;
        this.fireTableDataChanged();
    }

    public Vendascursos getRegistro(int linha) {
        return (Vendascursos) lista.get(linha);
    }

    public String getColumnName(int num) {
        if (num == 0) {
            return "Num venda";
        }
        if (num == 1) {
            return "Curso";
        }
        if (num == 2) {
            return "Valor unitário";
        }
        if (num == 3) {
            return "Quant modulos";
        }
        if (num == 4) {
            return "Total";
        }
        return "";
    }

    public void addList(Vendascursos beanVendasCursos) {
        this.lista.add(beanVendasCursos);
        this.fireTableDataChanged();
    }

    public void removeList(int linha) {
        this.lista.remove(linha);
        this.fireTableDataChanged();
    }

    public void clearList() {
        this.lista = new ArrayList();
        this.fireTableDataChanged();
    }

    public void updateList(Vendascursos beanVendasCursos) {
        
        for (Object O : this.lista) {
            //((Vendascursos) O).getId().getIdvendas() == beanVendasCursos.getId().getIdvendas() &&
            if (((Vendascursos) O).getId().getCurso() == beanVendasCursos.getId().getCurso()) {
                //((Vendascursos) O).setValorUnitario(beanVendasCursos.getValorUnitario());
                ((Vendascursos) O).setTotal(beanVendasCursos.getTotal());
                //((Vendascursos) O).setId(new VendascursosId(beanVendasCursos.getId().getIdvendas(), beanVendasCursos.getId().getIdvendas()));
                ((Vendascursos) O).setQuantidadeModulos(beanVendasCursos.getQuantidadeModulos());
                this.fireTableDataChanged();
            }
        }
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
        Vendascursos vendasCursos = new Vendascursos();
        vendasCursos = (Vendascursos) lista.get(rowIndex);

        if (columnIndex == 0) {
            return vendasCursos.getId().getIdvendas();
        }
        if (columnIndex == 1) {
            return vendasCursos.getCursos();
        }
        if (columnIndex == 2) {
            return vendasCursos.getValorUnitario();
        }
        if (columnIndex == 3) {
            return vendasCursos.getQuantidadeModulos();
        }
        if (columnIndex == 4) {
            return vendasCursos.getTotal();
        }

        return null;
    }
}
