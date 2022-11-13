/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.util;
import java.util.List;
import cat.xtec.ioc.db.dbConnection;
import cat.xtec.ioc.domain.Reserva;
import cat.xtec.ioc.domain.ReservaDAO;
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


public class HibernateCRUDReserva  {
   
    public static void addReserva(Reserva reserva){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Reserva.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            miSession.save(reserva);
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
     public static void updateReserva(Reserva reserva){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Reserva.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            int reservaId=reserva.getId();
            
            Reserva reservaBD = miSession.get(Reserva.class, reservaId);
            System.out.println (reservaBD);
                                  
            reservaBD.setFecha(reserva.getFecha());
            reservaBD.setId(reserva.getId());
            reservaBD.setIdCliente(reserva.getIdCliente());
            reservaBD.setHora(reserva.getHora());
            reservaBD.setIdActivitat(reserva.getIdActivitat());
            reservaBD.setIdGos(reserva.getIdGos());
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
      public static void deleteReserva(Reserva reserva){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Reserva.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            Reserva reservaBD = miSession.get(Reserva.class, reserva.getId());
            
            System.out.println("CRUD: "+reservaBD);
            
            miSession.delete(reservaBD);
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
       public static void deleteReserva(int reservaid){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Reserva.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            Reserva reservaBD = miSession.get(Reserva.class,reservaid);
            
                        
            miSession.delete(reservaBD);
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
       
       public static void updateReservaById(int reservaid,Reserva reserva){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Reserva.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        
        try{
            miSession.beginTransaction();
            System.out.println ("reservaBD ABANS: " +reservaid);
            Reserva reservaBD = miSession.get(Reserva.class,reservaid);
            
            System.out.println ("reservaBD: " + reservaBD);
            
            reservaBD.setFecha(reserva.getFecha());
            reservaBD.setHora(reserva.getHora());
            reservaBD.setIdActivitat(reserva.getIdActivitat());
            reservaBD.setIdCliente(reserva.getIdCliente());
            reservaBD.setIdGos(reserva.getIdGos());
                        
                       
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }finally{
            miFactory.close();
        }
    }
       
       
        public static List<Reserva> getReservas(){
            SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Reserva.class).buildSessionFactory();
            Session miSession=miFactory.openSession();
            List<Reserva> llistaReservas;
        
            try{
                miSession.beginTransaction();

                llistaReservas = miSession.createQuery("from Reserva").getResultList();

                miSession.getTransaction().commit();
                System.out.println ("transaccion acabada");

            }finally{
                miFactory.close();
            }
            
            return llistaReservas;
        
        }
       
        public static Reserva getReservaById(int reservaid){
        SessionFactory miFactory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Reserva.class).buildSessionFactory();
        Session miSession=miFactory.openSession();
        Reserva reservaBD ;
        
        try{
            miSession.beginTransaction();
            reservaBD = miSession.get(Reserva.class,reservaid);
                       
            miSession.getTransaction().commit();
           
            
        }finally{
            miFactory.close();
        }
        return reservaBD;
    }
      
       
}
