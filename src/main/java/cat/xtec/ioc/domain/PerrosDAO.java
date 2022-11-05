
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
import cat.xtec.ioc.util.HibernateCRUDPerro;
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
public class PerrosDAO {
    
    
    public  void add (Perros p) {
        
        HibernateCRUDPerro.addGos(p);
    }
    
    
    public  void updatePerros (Perros p) {
        
        HibernateCRUDPerro.updateGos(p);
        
        
        
        System.out.println (p);
        
        
        
    }
    /* NO FUNCIONA *****************
    
    
   public  void deletePerros (Perros p) {
        
        HibernateCRUDPerro.deleteGos(p);
         
        
        System.out.println (p);
        
       
        
    }*/ 
    
    
    
    public  void deletePerros (int gosid) {
        
        HibernateCRUDPerro.deleteGos(gosid);
    }

    public PerrosDAO() {
    }
    
}
