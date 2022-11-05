/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.util;
import java.util.List;
import cat.xtec.ioc.db.dbConnection;
import cat.xtec.ioc.domain.Perros;
import cat.xtec.ioc.domain.PerrosDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author CONXI
 */


public class HibernateCRUDPerro  {
   
    public static void addGos(Perros perro){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Perros.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            miSession.save(perro);
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
     public static void updateGos(Perros perro){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Perros.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            int perroId=perro.getId();
            
            Perros perroBD = miSession.get(Perros.class, perroId);
            System.out.println (perroBD);
                                  
            perroBD.setNombre(perro.getNombre());
            perroBD.setId(perro.getId());
            perroBD.setIdCliente(perro.getIdCliente());
            perroBD.setObservaciones(perro.getObservaciones());
            perroBD.setPeso(perro.getPeso());
            perroBD.setRaza(perro.getRaza());
            perroBD.setSexo(perro.getSexo());
            perroBD.setFechaNacimiento(perro.getFechaNacimiento());
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
      public static void deleteGos(Perros perro){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Perros.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            Perros perroBD = miSession.get(Perros.class, perro.getId());
            
            System.out.println("CRUD: "+perroBD);
            
            miSession.delete(perroBD);
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
       public static void deleteGos(int gosid){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Perros.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            Perros perroBD = miSession.get(Perros.class,gosid);
            
            miSession.delete(perroBD);
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
}
