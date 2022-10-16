/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.domain;


import java.util.List;
import cat.xtec.ioc.db.dbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 *
 * @author ladmin
 */

@Configuration

public class ActividadesDAO {
    


public ActividadesDAO() {
    }
    
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
    public Actividades getById (int idparam) {
        String qry2 = "select ID,NOMBRE,N_PARTICIPANTES_MAX,DESCRIPCION, ACTIVA, N_PARTICIPANTES_MIN, FECHA, h.HORA, h.ID_ACTIVIDAD, h.TIEMPO_ACTIVIDAD,h.PLAZAS_OCUPADAS from GL_ACTIVIDADES as a,GL_HORARIO as h "
                + " where ID=" + idparam + "AND a.ID=h,ID_ACTIVIDAD";
        
        
        
        String qry = "select ID,NOMBRE,N_PARTICIPANTES_MAX,DESCRIPCION, ACTIVA, N_PARTICIPANTES_MIN, FECHA, h.HORA, h.ID_ACTIVIDAD, "
                +"h.TIEMPO_ACTIVIDAD,h.PLAZAS_OCUPADAS "
                +"from GL_ACTIVIDADES as a,GL_HORARIO as h where a.ID="+idparam+" AND a.ID=h.ID_ACTIVIDAD "
                +"GROUP BY FECHA ORDER by h.HORA";
        
        
        
        
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