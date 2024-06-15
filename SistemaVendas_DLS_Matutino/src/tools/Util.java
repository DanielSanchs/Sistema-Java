/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Daniel Lopes
 */
public class Util {
    public void habilitarNovo(boolean valor, JComponent... componentes) {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].setEnabled(valor);

        }
    }
    
    public void limparNovo(JComponent... componentes){
        for (int i = 0; i < componentes.length; i++) {
            if(componentes[i] instanceof JTextField){
                JTextField comp = (JTextField)componentes[i];
                comp.setText("");
            }
            if(componentes[i] instanceof JCheckBox){
                JCheckBox comp = (JCheckBox)componentes[i];
                comp.setSelected(false);
            }
            if(componentes[i] instanceof JComboBox){
                JComboBox comp = (JComboBox)componentes[i];
                comp.setSelectedIndex(-1);
            }
            if(componentes[i] instanceof JFormattedTextField){
                JFormattedTextField comp = (JFormattedTextField)componentes[i];
                comp.setText("");
            }
            if(componentes[i] instanceof JSpinner){
                JSpinner comp = (JSpinner)componentes[i];
                comp.setValue(0);
            }
            if(componentes[i] instanceof JPasswordField){
                JPasswordField comp = (JPasswordField)componentes[i];
                comp.setText("");
            }
        }
    }
    
    public static void mostrar(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    public static int perguntar(String mensagem,String titulo, int opcao){
        return JOptionPane.showConfirmDialog(null, mensagem, titulo, opcao);
    }
    
    public static String dataStr(Date data){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(data);
    }
    
    public static Date strData(String data){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formato.parse(data);
        } catch (ParseException ex) {
            System.out.println("Deu erro na hora de converter string pra data"+ex.getMessage());
        }
        return null;
    }
    
    public static String intStr(int valor){
        return String.valueOf(valor);
    }
    
    public static int strInt(String valor){
        return Integer.valueOf(valor);
    }
    
    public static String doubleStr(Double valor){
        return String.valueOf(valor);
    }
    
    public static Double strDouble(String valor){
        return Double.valueOf(valor);
    }
}
