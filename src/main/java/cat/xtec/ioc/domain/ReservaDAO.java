
/**
 * 
 * classe ActivitatsDAO
 * @autor: grup4
 * @versio: 20/10/2022
 * @descripcio: objecte d'accès a les dades de les Activitats. Les consultes respondran a les següents peticions:
 *               - Consulta de totes les activitats:   https://localhost:8080/gosletic/actividades 
 *               - Consulta del detall d'una activitat per {id}:   https://localhost:8080/gosletic/actividades/{id} 
 *               - Consulta de les activitats per data {YYYY-MM-DD} ordenades per hora: https://localhost:8080/gosletic/horario/{YYYY-MM-DD}
 */

package cat.xtec.ioc.domain;


import java.util.List;
import cat.xtec.ioc.db.dbConnection;
import cat.xtec.ioc.util.HibernateCRUDReserva;
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

import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservaDAO {
    
    
    public  void add (Reserva p) {
        
        HibernateCRUDReserva.addReserva(p);
    }
    
    
    public  void updateReserva (Reserva p) {
        
        HibernateCRUDReserva.updateReserva(p);
        
        
        
        System.out.println (p);
        
    }
    
    
    
    public  void deleteReserva (int reservaid) {
        
        HibernateCRUDReserva.deleteReserva(reservaid);
    }
    
    
    public  void updateReservabyId (int reservaid, Reserva reserva) {
        
        HibernateCRUDReserva.updateReservaById(reservaid, reserva);
    }
    

    public ReservaDAO() {
    }
    
    public List<Reserva> getReservas () {
        
        return  HibernateCRUDReserva.getReservas();
    }
    
    public Reserva getReservaById(int idReserva){
        return HibernateCRUDReserva.getReservaById(idReserva);
    }
    
    //get reserves per idClient
    /*public List<Reserva> getReservasByIdClient(int idClient, int idAct){
        return HibernateCRUDReserva.getReservasByIdClient(idClient, idAct);
    }*/
    
     public List<Reserva> getReservesByIdClientIdAct(int idClient, int idAct) {
        String qry = "select *  from GL_RESERVA where id_cliente = "+idClient +" and id_actividad = "+idAct+";";
        dbConnection dbConnection = new dbConnection();      
        
        List<Reserva> reservas_list= new ArrayList<>();
        try (
               Connection conn =  dbConnection.getConnection();
               Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(qry);
               
                )
            
             
        {
            while (rs.next()) {
                int idCli = rs.getInt("id_cliente");
                int idGos = rs.getInt("id_perro");
                int idAc = rs.getInt("id_actividad");
                String data = rs.getDate("fecha").toString();
                int hora = rs.getInt("hora");
                System.out.println("reserva amb data: "+ data);
                
                Reserva reserva = new Reserva(idCli,idGos,idAc,data,hora);
                reservas_list.add(reserva);
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return reservas_list;
    }
    
}
 /*
MariaDB [gosletics]> describe gl_reserva;

+--------------+-----------+------+-----+---------------------+----------------+
| Field        | Type      | Null | Key | Default             | Extra          |
+--------------+-----------+------+-----+---------------------+----------------+
| ID           | int(11)   | NO   | PRI | NULL                | auto_increment |
| ID_CLIENTE   | int(11)   | NO   |     | NULL                |                |
| ID_PERRO     | int(11)   | NO   |     | NULL                |                |
| ID_ACTIVIDAD | int(11)   | NO   |     | NULL                |                |
| FECHA        | date      | NO   |     | NULL                |                |
| HORA         | int(11)   | NO   |     | NULL                |                |
| FX_INSERT    | timestamp | NO   |     | current_timestamp() |                |
| FX_PROC_INFO | timestamp | YES  |     | NULL                |                |
+--------------+-----------+------+-----+---------------------+----------------+
   
*/
  