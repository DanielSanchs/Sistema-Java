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
public class UsuariosPesquisa extends AbstractTableModel{
    private List lista;

    public UsuariosPesquisa() {
    }
 
    public void setList(List list){
        this.lista = list;
        this.fireTableDataChanged();
    }
    
    public Usuarios getRegistro(int linha){
        return (Usuarios) lista.get(linha);
    }
    
    public String getColumnName(int num){
        if(num == 0){return "Código";}
        if(num == 1){return "Nome";}
        if(num == 2){return "Apelido";}
        if(num == 3){return "CPF";}
        if(num == 4){return "Senha";}
        if(num == 5){return "Nível";}
        if(num == 6){return "Ativo";}
        if(num == 7){return "DataN";}
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
        Usuarios usuario = new Usuarios();
        usuario = (Usuarios) lista.get(rowIndex);
        if(columnIndex == 0){return usuario.getIdusuarios();}
        if(columnIndex == 1){return usuario.getNome();}
        if(columnIndex == 2){return usuario.getApelido();}
        if(columnIndex == 3){return usuario.getCpf();}
        if(columnIndex == 4){return usuario.getSenha();}
        if(columnIndex == 5){return usuario.getNivel();}
        if(columnIndex == 6){return usuario.getAtivo();}
        if(columnIndex == 7){return usuario.getDataNascimento();}
        return null;
    }
}
