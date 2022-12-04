/**
 * 
 * classe ActivitatsDisponiblesDAO
 * @autor: conxigb
 * @versio: 03/11/2022
 * @descripcio: objecte d'accès a les Activitats Disponibles per reservar segons dia i hora del sistema.
 *              La consulta respondrà a la petició:
 *               - https://localhost:8080/gosletic/reservas/{YYYY-MM-DD}
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.util.Assert;

@Configuration
public class ActividadesDisponiblesDAO {

    public ActividadesDisponiblesDAO() {
    }       
    
    /**
     * Consulta de les activitats per data {YYYY-MM-DD} ordenades per hora: https://localhost:8080/gosletic/reservas/{YYYY-MM-DD}
     * @param dia_param2 data format YYYY-MM-DD
     * @return List llista de les activitats que es poden reservar per data donada .
     * Si la data és el dia d'avui es filtraran les activitats a partir de l'hora del sistema
     */
   
    
    public List <Activitats_dia> getReservasByDate(String dia_param2) {
        LocalDateTime ahora= LocalDateTime.now();
        int año = ahora.getYear();
        int mes = ahora.getMonthValue();
        int dia = ahora.getDayOfMonth();
        int horilla = ahora.getHour();
        int minutos = ahora.getMinute();
        int segundos = ahora.getSecond();
            System.out.println("Hora actual: "+ año+"/"+normalizar_numero(mes)+"/"+normalizar_numero(dia) +" "+" hora : " + normalizar_numero(horilla) + normalizar_numero(minutos));

            String qry = "select A.ID, A.NOMBRE,  H.FECHA, H.HORA, H.TIEMPO_ACTIVIDAD, H.PLAZAS_OCUPADAS, A.N_PARTICIPANTES_MAX "
                + "FROM GL_ACTIVIDADES A INNER JOIN GL_HORARIO H ON H.ID_ACTIVIDAD = A.ID " 
                + "WHERE h.fecha='"+ dia_param2+ "'"
                + "ORDER by h.HORA";

            String hoy = normalizar_numero(año)+"-"+normalizar_numero(mes)+"-"+normalizar_numero(dia);

            // es mira si el dia passat per paràmetre és el dia del sistema, per mirar les activitats disponibles a partir de l'hora del sistema.
            if (hoy.compareTo(dia_param2)==0){ //0: data igual a avui
                 System.out.println("entre en el if");
                   qry = "select A.ID, A.NOMBRE,  H.FECHA, H.HORA, H.TIEMPO_ACTIVIDAD, H.PLAZAS_OCUPADAS, A.N_PARTICIPANTES_MAX "
                    + "FROM GL_ACTIVIDADES A INNER JOIN GL_HORARIO H ON H.ID_ACTIVIDAD = A.ID " 
                    + "WHERE h.fecha='"+ dia_param2+ "'"
                    + "and h.hora>'"+ normalizar_numero(horilla)+normalizar_numero(minutos)+ "'"
                    + "ORDER by h.HORA";
             
            } 
      
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
    
 // Passem el format del mes o el dia a DOS DÍGITS
    public String normalizar_numero (int numero) {
        
        String s_numero;
        if (numero < 10) {
            
            s_numero="0"+ Integer.toString(numero);
        } else {
            s_numero=Integer.toString(numero);
            
        }
        
        return s_numero;
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