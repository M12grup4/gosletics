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
        int idActivitat;
        String fecha=reserva.getFecha();
        int hora=reserva.getHora();
        Connection conn;
        Statement stmt = null;
        int n_participantes_max = 0;
        int pl_ocupades = 0;
        
        try{
            //--------actualitzar Places Reservades a taula Horario
            // Verificar si existeixen places disponibles
            idActivitat=reserva.getIdActivitat(); 
            String qryPartiMax = "select N_PARTICIPANTES_MAX from GL_ACTIVIDADES";
            String qryHorario = "select PLAZAS_OCUPADAS  from GL_HORARIO where HORA='" +hora +"' AND FECHA='" +fecha + "' AND ID_ACTIVIDAD=" +idActivitat +"";
            
            dbConnection dbConnection = new dbConnection();      
                
            try {
                   conn =  dbConnection.getConnection();
                   stmt = conn.createStatement();
                   ResultSet rs = stmt.executeQuery(qryPartiMax);
                   
                   while (rs.next()) {
                        n_participantes_max=rs.getInt("n_participantes_max");
                   }
            }   catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            
            try {
                   
                   ResultSet rs2 = stmt.executeQuery(qryHorario);
                   
                   while (rs2.next()) {
                        pl_ocupades=rs2.getInt("PLAZAS_OCUPADAS");
                   }
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
            
            // ------ fi actualitzar Places Reservades a taula Horario
         
        //es comprova que no hem arribat al màxim de places ocupades de l'activitat a reservar   
        //si hi ha places disponibles s'actualitza el camp PLAZAS_OCUPADAS amb el nou valor a la taula HORARIO
        if (pl_ocupades < n_participantes_max ){
            
            pl_ocupades++;
            String qrySumarPlaces = "UPDATE GL_HORARIO SET PLAZAS_OCUPADAS="+ pl_ocupades +" where HORA='" +hora +"' AND FECHA='" +fecha + "' AND ID_ACTIVIDAD=" +idActivitat +"";
            
             try {
                   
                   int rs3 = stmt.executeUpdate(qrySumarPlaces);
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            miSession.beginTransaction();
            miSession.save(reserva);
            
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
            
        }
            else System.out.println("NO HI HA PLACES DISPONIBLES PER RESERVAR ");
            
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
            
            System.out.println("DELETE RESERVA: "+reservaBD);
            
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
        int idActivitat;
        String fecha="";
        int hora=0;
        Connection conn;
        Statement stmt = null;
        int n_participantes_min = 0;
        int pl_ocupades = 0;
        
        try{
            miSession.beginTransaction();
            Reserva reservaBD = miSession.get(Reserva.class,reservaid);
            fecha=reservaBD.getFecha();
            hora=reservaBD.getHora();
            idActivitat=reservaBD.getIdActivitat();
            
            String qryHorario = "select PLAZAS_OCUPADAS  from GL_HORARIO where HORA='" +hora +"' AND FECHA='" +fecha + "' AND ID_ACTIVIDAD=" +idActivitat +"";
            dbConnection dbConnection = new dbConnection();   
            try {
                   conn =  dbConnection.getConnection();
                   stmt = conn.createStatement();
                   ResultSet rs = stmt.executeQuery(qryHorario);
                   
                   while (rs.next()) {
                        pl_ocupades=rs.getInt("plazas_ocupadas");
                   }
            }   catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            
        if (pl_ocupades > 0 ){            
            
            pl_ocupades--;
            String qryRestarPlaces = "UPDATE GL_HORARIO SET PLAZAS_OCUPADAS="+ pl_ocupades +" where HORA='" +hora +"' AND FECHA='" +fecha + "' AND ID_ACTIVIDAD=" +idActivitat +"";
            
             try {
                   
                   int rs3 = stmt.executeUpdate(qryRestarPlaces);
                   
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //esborra reserva a taula Reservas
            miSession.delete(reservaBD);
            miSession.getTransaction().commit();
            System.out.println ("transaccion acabada");
        }
            else System.out.println("NO ÉS POSSIBLE ANUL·LAR MÉS PLACES ");
            
            
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
