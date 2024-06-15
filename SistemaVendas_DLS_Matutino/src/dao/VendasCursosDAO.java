/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Cursos;
import bean.Vendas;
import bean.Vendascursos;
import bean.VendascursosId;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Daniel Lopes
 */
public class VendasCursosDAO extends DAO_Abstract{

    @Override
    public void insert(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Object object) {
        session.beginTransaction();
        session.flush();
        session.clear();
        session.delete(object);
        session.getTransaction().commit();

    }

    @Override
    public void update(Object object) {
        session.beginTransaction();
        session.flush();
        session.clear();
        session.update(object);
        session.getTransaction().commit();
    }

    public Object list(int codigo) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Vendascursos.class);
        //criteria.createCriteria("vendascursos").createCriteria("curso").createCriteria("idvendas");
        criteria.add(Restrictions.eq("idvendas", codigo));
        //System.out.println(""+criteria.list());
        List lista = criteria.list();
        session.getTransaction().commit();
        
        return lista.get(0);
    }

    
    public ArrayList listAll(int codigo) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Vendascursos.class);
        criteria.add(Restrictions.eq("id.idvendas", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        
        return (ArrayList) lista;
    }

    @Override
    public ArrayList listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Vendascursos.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        
        return (ArrayList) lista;
    }
}
