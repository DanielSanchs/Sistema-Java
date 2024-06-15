/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Cursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Daniel Lopes
 */
public class CursosDAO extends DAO_Abstract {

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

    @Override
    public Object list(int codigo) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Cursos.class);
        criteria.add(Restrictions.eq("idcursos", codigo));
        List lista = criteria.list();
        session.getTransaction().commit();
        
        return lista.get(0);
    }

    @Override
    public ArrayList listAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Cursos.class);
        List lista = criteria.list();
        session.getTransaction().commit();
        
        return (ArrayList) lista;
    }
    
    public List listConsulta(String nome, String certificado) {
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Cursos.class);
        criteria.add(Restrictions.like("nome", nome));
        criteria.add(Restrictions.like("certificado", certificado));
        List lista = criteria.list();
        session.getTransaction().commit();
        
        return lista;
    }
}