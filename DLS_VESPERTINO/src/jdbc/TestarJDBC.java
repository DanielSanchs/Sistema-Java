/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Daniel Lopes
 */
public class TestarJDBC {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cnt;
            cnt = DriverManager.getConnection("jdbc:mysql://localhost/vendas_cursos?useTimezone=true&serverTimezone=UTC","root","");
        } catch (ClassNotFoundException ex) {
            System.out.println("Deu erro no forName do TestarDAO"+ex);
        } catch (SQLException ex) {
            System.out.println("Deu erro na conexão do TestarDAO"+ex);
        }
    }
}
