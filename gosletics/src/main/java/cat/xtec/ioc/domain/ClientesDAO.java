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
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientesDAO {

public ClientesDAO() {
    }

    /**
     * Consulta de totes les activitats:   https://localhost:8080/gosletic/actividades   
     * @return List llista de totes les activitats
     */

    public List<Clientes> getAllClientes() {
        String qry = "select * from GL_CLIENTES";
        dbConnection dbConnection = new dbConnection();      
        
        List<Clientes> clientes_list= new ArrayList<>();
        try (
               Connection conn =  dbConnection.getConnection();
                Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(qry);
                
            ) 
        {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                String fecha_nacimiento = rs.getString("fecha_nacimiento");
                String dni = rs.getString("dni");
                String email = rs.getString("email");
                String calle = rs.getString("calle");
                String numero = rs.getString("numero");
                String piso = rs.getString("piso");
                String cp = rs.getString("cp");
                String poblacion = rs.getString("poblacion");
                String pass = rs.getString("pass");
                Clientes clientes = new Clientes(id,nombre,apellido1,apellido2,fecha_nacimiento,dni,email,calle,numero,piso,cp,poblacion,pass);
                clientes_list.add(clientes);
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return clientes_list;
    }
    
    /**
     * Consulta del detall d'un cñient per {id}:   https://localhost:8080/gosletic/clientess/{id} 
     * @param idparam (int): id del client
     * @return Clientes (objecte)
     */
    
    public Clientes getClienteById (int idparam) {
   
        String qry = "select * from GL_CLIENTES WHERE ID="+idparam ;
                        
        
        dbConnection dbConnection = new dbConnection();
        Clientes clientes = null;
       
        try (
                Connection conn = (Connection) dbConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                String fecha_nacimiento = rs.getString("fecha_nacimiento");
                String dni = rs.getString("dni");
                String email = rs.getString("email");
                String calle = rs.getString("calle");
                String numero = rs.getString("numero");
                String piso = rs.getString("piso");
                String cp = rs.getString("cp");
                String poblacion = rs.getString("poblacion");
                String pass = rs.getString("pass");
                
                clientes = new Clientes(id,nombre,apellido1,apellido2,fecha_nacimiento,dni,email,calle,numero,piso,cp,poblacion,pass);
       /*+------------------+--------------+------+-----+---------------------+----------------+
| Field            | Type         | Null | Key | Default             | Extra          |
+------------------+--------------+------+-----+---------------------+----------------+
| ID               | int(11)      | NO   | PRI | NULL                | auto_increment |
| NOMBRE           | varchar(256) | NO   |     | NULL                |                |
| APELLIDO1        | varchar(256) | NO   |     | NULL                |                |
| APELLIDO2        | varchar(256) | YES  |     | NULL                |                |
| FECHA_NACIMIENTO | date         | NO   |     | NULL                |                |
| DNI              | varchar(256) | NO   |     | NULL                |                |
| EMAIL            | varchar(256) | NO   | UNI | NULL                |                |
| CALLE            | varchar(256) | NO   |     | NULL                |                |
| NUMERO           | varchar(256) | NO   |     | NULL                |                |
| PISO             | varchar(256) | NO   |     | NULL                |                |
| CP               | varchar(256) | NO   |     | NULL                |                |
| POBLACION        | varchar(256) | NO   |     | NULL                |                |
| PASS             | varchar(256) | NO   |     | NULL                |                |
| FX_INSERT        | timestamp    | NO   |     | current_timestamp() |                |
| FX_PROC_INFO     | timestamp    | YES  |     | NULL                |                |
+------------------+--------------+------+-----+---------------------+----------------+*/
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
    