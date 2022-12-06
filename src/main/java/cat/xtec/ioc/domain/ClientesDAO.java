/**
 * 
 * classe ClientsDAO
 * @autor: Conxi Gàlvez
 * @versio: 05/12/2022
 * @autor: Conxi Gàlvez
 * @descripcio: objecte d'accès a les dades de les Activitats. Les consultes respondran a les següents peticions:
 *               - Consulta de tots els clients - (List<Clientes> getAllClientes):   https://localhost:8080/gosletic/clients 
 *               - Consulta del detall d'un client per {idclient} - (Clientes getClienteById (int idparam)):   https://localhost:8080/gosletic/clients/{idClient} 
 *               - Alta d'un client : https://localhost:8080/gosletic/clients/alta
 *               - Baixa d'un client : https://localhost:8080/gosletic/clients/baixa/{idClient}
 *               - Modificació d'un client : https://localhost:8080/gosletic/clients/modif/ídClient}
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
     * Consulta de tots els clients:   https://localhost:8080/gosletic/clients  
     * @return List llista de tots els clients
     */

    public List<Clientes> getAllClientes() throws SQLException, IOException {
        //String qry = "select * from GL_CLIENTES";
       
        String qry = "SELECT ID, AES_DECRYPT(NOMBRE, 'AES') as nombre,AES_DECRYPT(APELLIDO1, 'AES') as apellido1, AES_DECRYPT(APELLIDO2, 'AES') as apellido2, FECHA_NACIMIENTO,"
                + " AES_DECRYPT(DNI, 'AES') as dni, AES_DECRYPT(EMAIL, 'AES') as email, AES_DECRYPT(CALLE, 'AES') as calle, AES_DECRYPT(NUMERO, 'AES') as numero,"
                + " AES_DECRYPT(PISO, 'AES') as piso, AES_DECRYPT(CP, 'AES') as cp, AES_DECRYPT(POBLACION, 'AES') as poblacion, AES_DECRYPT(PASS, 'AES') as pass,"
                + " FX_INSERT, FX_PROC_INFO FROM GL_CLIENTES;";
        
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
                Clientes clientes = new Clientes(nombre,apellido1,apellido2,fecha_nacimiento,dni,email,calle,numero,piso,cp,poblacion,pass);
                  //Clientes clientes = new Clientes(id,nombre,apellido1,apellido2,fecha_nacimiento,dni,email,calle,numero,piso,cp,poblacion,pass);
                clientes_list.add(clientes);
            }
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return clientes_list;
    }
    
    /**
     * Consulta del detall d'un cñient per {id}:   https://localhost:8080/gosletic/clients/{idClient}
     * @param idparam (int): id del client
     * @return Clientes (objecte)
     */
    
    public Clientes getClientById (int idparam) {
   
        String qry = "SELECT ID, AES_DECRYPT(NOMBRE, 'AES') as nombre,AES_DECRYPT(APELLIDO1, 'AES') as apellido1, AES_DECRYPT(APELLIDO2, 'AES') as apellido2, FECHA_NACIMIENTO,"
                 + " AES_DECRYPT(DNI, 'AES') as dni, AES_DECRYPT(EMAIL, 'AES') as email, AES_DECRYPT(CALLE, 'AES') as calle, AES_DECRYPT(NUMERO, 'AES') as numero,"
                 + " AES_DECRYPT(PISO, 'AES') as piso, AES_DECRYPT(CP, 'AES') as cp, AES_DECRYPT(POBLACION, 'AES') as poblacion, AES_DECRYPT(PASS, 'AES') as pass,"
                 + " FX_INSERT, FX_PROC_INFO FROM GL_CLIENTES  WHERE ID= " + idparam +";";
                        
        
        dbConnection dbConnection = new dbConnection();
        Clientes clientes = null;
       
        try (
                Connection conn = (Connection) dbConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {
            while (rs.next()) {
                //  int id = rs.getInt("id");
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
                
               // clientes = new Clientes(id,nombre,apellido1,apellido2,fecha_nacimiento,dni,email,calle,numero,piso,cp,poblacion,pass);
                clientes = new Clientes(nombre,apellido1,apellido2,fecha_nacimiento,dni,email,calle,numero,piso,cp,poblacion,pass);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    
    /* FUNCIÓ INSERTAR CLIENTS
    * @version TEA4
    * @autor Conxi
    * @param client.json String
    */
    public String addClient (Clientes client) throws SQLException, IOException {
         System.out.println("entro a alta client " + client);
      //  int id;
        String nombre;
        String apellido1;
        String apellido2;
        String fecha_nacimiento;
        String dni;
        String email;
        String calle;
        String numero;
        String piso;
        String cp;
        String poblacion;
        String pass;
        
        //id=client.getId();
        nombre=client.getNombre();
        apellido1=client.getApellido1();
        apellido2=client.getApellido2();
        fecha_nacimiento=client.getFecha_nacimiento();
        dni=client.getDni();
        email=client.getEmail();
        calle=client.getCalle();
        numero=client.getNumero();
        piso=client.getPiso();
        cp=client.getCp();
        poblacion=client.getPoblacion();
        pass=client.getPass();
        int rs;
        Connection conn;
        Statement stmt;
        String qry = "INSERT INTO GL_CLIENTES( NOMBRE,APELLIDO1,APELLIDO2,FECHA_NACIMIENTO,DNI,"
                + " EMAIL,CALLE,NUMERO,PISO,CP,POBLACION,PASS)"
                + " VALUES (aes_encrypt('"+nombre+"','AES'),aes_encrypt('"+apellido1+"','AES'),"
                + " aes_encrypt('"+apellido2+"','AES'),'"+fecha_nacimiento+"', aes_encrypt('"+dni+"','AES'),"
                + " aes_encrypt('"+email+"','AES'), aes_encrypt('"+calle+"','AES'), aes_encrypt('"+numero+"','AES'),"
                + " aes_encrypt('"+piso+"','AES'),aes_encrypt('"+cp+"','AES'),aes_encrypt('"+poblacion+"','AES'),"
                + " aes_encrypt('"+pass+"','AES'));";
          
        dbConnection dbConnection = new dbConnection();      
        conn =  dbConnection.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeUpdate(qry);
        String resultat = "OK";
        return resultat;
    }
    
    
    /* FUNCIÓ ESBORRAR CLIENTS
    * @version TEA4
    * @autor Conxi
    * @param client.json String
    */
    public String deleteClients (int idClient) throws SQLException, IOException {
        int id;
        Clientes client =  getClientById (idClient);
        id=client.getId();
        int rs;
        Connection conn;
        Statement stmt;
        System.out.println("client id :" + id);
        /*String qry = "DELETE FROM GL_CLIENTES WHERE idClient=id";
          
        dbConnection dbConnection = new dbConnection();      
        conn =  dbConnection.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeUpdate(qry);*/
        String resultat = "OK";
        return resultat;
    }
    
    
    
    
}
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

    