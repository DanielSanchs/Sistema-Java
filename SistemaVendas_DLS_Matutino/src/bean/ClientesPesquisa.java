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
public class ClientesPesquisa extends AbstractTableModel{
    private List lista;

    public ClientesPesquisa() {
    }
 
    public void setList(List list){
        this.lista = list;
        this.fireTableDataChanged();
    }
    
    public Clientes getRegistro(int linha){
        return (Clientes) lista.get(linha);
    }
    
    public String getColumnName(int num){
        if(num == 0){return "Código";}
        if(num == 1){return "Nome";}
        if(num == 2){return "Sexo";}
        if(num == 3){return "RG";}
        if(num == 4){return "CPF";}
        if(num == 5){return "Telefone";}
        if(num == 6){return "Email";}
        if(num == 7){return "DataN";}
        if(num == 8){return "Nacionalidade";}
        if(num == 9){return "Estado";}
        if(num == 10){return "Cidade";}
        if(num == 11){return "Bairro";}
        if(num == 12){return "Escolaridade";}
        if(num == 13){return "Prifissão";}
        if(num == 14){return "Idioma";}
        return "";
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 15;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clientes clientes = new Clientes();
        clientes = (Clientes) lista.get(rowIndex);
        if(columnIndex == 0){return clientes.getIdclientes();}
        if(columnIndex == 1){return clientes.getNome();}
        if(columnIndex == 2){return clientes.getSexo();}
        if(columnIndex == 3){return clientes.getRg();}
        if(columnIndex == 4){return clientes.getCpf();}
        if(columnIndex == 5){return clientes.getTelefone();}
        if(columnIndex == 6){return clientes.getEmail();}
        if(columnIndex == 7){return clientes.getDataNascimento();}
        if(columnIndex == 8){return clientes.getNacionalidade();}
        if(columnIndex == 9){return clientes.getEstado();}
        if(columnIndex == 10){return clientes.getCidade();}
        if(columnIndex == 11){return clientes.getBairro();}
        if(columnIndex == 12){return clientes.getEscolaridade();}
        if(columnIndex == 13){return clientes.getProfissao();}
        if(columnIndex == 14){return clientes.getIdioma();}
        return null;
    }
}
