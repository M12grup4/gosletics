/**
 * 
 * classe ActivitatsDAO
 * @autor: Conxi Gàlvez Baixench
 * @versio: 20/10/2022
 * @descripcio: objecte d'accès a les dades de les Activitats. Les consultes respondran a les següents peticions:
 *               - Consulta de totes les activitats:   https://localhost:8080/gosletic/actividades 
 *               - Consulta del detall d'una activitat per {id}:   https://localhost:8080/gosletic/actividades/{id} 
 *               - Consulta de les activitats per data {YYYY-MM-DD} ordenades per hora: https://localhost:8080/gosletic/horario/{YYYY-MM-DD}
 */

package cat.xtec.ioc.domain;


import java.util.List;
import cat.xtec.ioc.db.dbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.util.Assert;

@Configuration
public class ActividadesDAO {

public ActividadesDAO() {
    }

    /**
     * Consulta de totes les activitats:   https://localhost:8080/gosletic/actividades   
     * @return List llista de totes les activitats
     */

    public List<Actividades> getAllActividades() {
        String qry = "select ID,NOMBRE,N_PARTICIPANTES_MAX,DESCRIPCION, ACTIVA, N_PARTICIPANTES_MIN from GL_ACTIVIDADES";
        dbConnection dbConnection = new dbConnection();      
        
        List<Actividades> actividades_list= new ArrayList<>();
        try (
               Connection conn =  dbConnection.getConnection();
                Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(qry);
                
            ) 
        {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Integer n_participantes_max=rs.getInt("n_participantes_max");
                String descripcion=rs.getString("descripcion");
                Integer activa=rs.getInt("activa");
                Integer n_participantes_min=rs.getInt("n_participantes_min");
                
                Actividades actividades = new Actividades(id,nombre,n_participantes_max,descripcion,activa,n_participantes_min);
                actividades_list.add(actividades);
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return actividades_list;
    }
    
    /**
     * Consulta del detall d'una activitat per {id}:   https://localhost:8080/gosletic/actividades/{id} 
     * @param idparam (String): id de l'activitat
     * @return Actividades (objecte)
     */
    
    public Actividades getById (int idparam) {
   
        String qry = "select ID,NOMBRE,N_PARTICIPANTES_MAX,DESCRIPCION, ACTIVA, N_PARTICIPANTES_MIN, FECHA, h.HORA, h.ID_ACTIVIDAD, "
                +"h.TIEMPO_ACTIVIDAD,h.PLAZAS_OCUPADAS "
                +"from GL_ACTIVIDADES as a,GL_HORARIO as h where a.ID="+idparam+" AND a.ID=h.ID_ACTIVIDAD ";
                        
        
        dbConnection dbConnection = new dbConnection();
        Actividades actividades = null;
       
        try (
                Connection conn = (Connection) dbConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Integer n_participantes_max=rs.getInt("n_participantes_max");
                String descripcion=rs.getString("descripcion");
                Integer activa=rs.getInt("activa");
                Integer n_participantes_min=rs.getInt("n_participantes_min");
                
                actividades = new Actividades(id,nombre,n_participantes_max,descripcion,activa,n_participantes_min);
       
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return actividades;
    }
    
    /**
     * Consulta de les activitats per data {YYYY-MM-DD} ordenades per hora: https://localhost:8080/gosletic/horario/{YYYY-MM-DD}
     * @param dia_param2 data format YYYY-MM-DD
     * @return List llista de les activitats per data
     */
    public List <Activitats_dia> getByDate(String dia_param2) {
        String qry = "select A.ID, A.NOMBRE,  H.FECHA, H.HORA, H.TIEMPO_ACTIVIDAD, H.PLAZAS_OCUPADAS, A.N_PARTICIPANTES_MAX "
                + "FROM GL_ACTIVIDADES A INNER JOIN GL_HORARIO H ON H.ID_ACTIVIDAD = A.ID " 
                + "WHERE h.fecha='"+ dia_param2+ "'"
                + "ORDER by h.HORA";        
        System.out.println(qry);
        
        //es passa a Date la cadena del paràmetre dia_param2
        LocalDate dia_param =  LocalDate.parse(dia_param2);
             
        dbConnection dbConnection = new dbConnection();
        List<Activitats_dia> activitats_dia_list= new ArrayList();
       
        try (
               Connection conn = (Connection)dbConnection.getConnection();
               Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(qry);) {
            
            while (rs.next()) {
                Integer id = rs.getInt("A.ID");
                String nombre = rs.getString("A.nombre");
                Date fecha=rs.getDate("h.fecha");
                //Date fecha = new Date();
                Integer hora=rs.getInt("h.hora");
                Integer tiempo_actividad=rs.getInt("h.tiempo_actividad");
                Integer plazas_ocupadas=rs.getInt("h.plazas_ocupadas");
                Integer n_participantes_max=rs.getInt("a.n_participantes_max");

                Activitats_dia activitats_dia = new Activitats_dia(id,nombre,fecha,hora,tiempo_actividad,plazas_ocupadas,n_participantes_max);
                activitats_dia_list.add(activitats_dia);  
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return activitats_dia_list;
    }
}
   

/*
 Field               | Type        | Null | Key | Default             | Extra |
+---------------------+-------------+------+-----+---------------------+-------+
| ID                  | int(11)     | NO   | PRI | NULL                |       |
| NOMBRE              | varchar(32) | NO   | PRI | NULL                |       |
| N_PARTICIPANTES_MAX | int(11)     | NO   |     | NULL                |       |
| FX_INSERT           | timestamp   | YES  |     | NULL                |       |
| FX_PROC_INFO        | timestamp   | NO   |     | current_timestamp() |       |
| DESCRIPCION         | text        | NO   |     | NULL                |       |
| ACTIVA              | tinyint(1)  | NO   |     | 0                   |       |
| N_PARTICIPANTES_MIN | int(11)     | NO   |     | NULL                |       |
+---------------------+-------------+------+-----+---------------------+-------+


*/