package cat.xtec.ioc.domain;

import java.sql.Date;
import java.util.Objects;

public class Horario {
    private Date fecha;
    private int hora;
    private int id_actividad;
    private int tiempo_actividad;
    private int plazas_ocupadas;

    public Horario(Date fecha, int hora, int id_actividad, int tiempo_actividad, int plazas_ocupadas) {
        this.fecha = fecha;
        this.hora = hora;
        this.id_actividad = id_actividad;
        this.tiempo_actividad = tiempo_actividad;
        this.plazas_ocupadas = plazas_ocupadas;
    }

    public Date getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha (YYYY-MM-DD)
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public int getTiempo_actividad() {
        return tiempo_actividad;
    }

    public void setTiempo_actividad(int tiempo_actividad) {
        this.tiempo_actividad = tiempo_actividad;
    }

    /**
     *
     * @return plazas_ocupadas (Integer): reserves fetes de l'activitat
     */
    public int getPlazas_ocupadas() {
        return plazas_ocupadas;
    }

    public void setPlazas_ocupadas(int plazas_ocupadas) {
        this.plazas_ocupadas = plazas_ocupadas;
    }
   
}

      
/*+------------------+-----------+------+-----+---------------------+-------------------------------+
| Field            | Type      | Null | Key | Default             | Extra                         |
+------------------+-----------+------+-----+---------------------+-------------------------------+
| FECHA            | date      | NO   | PRI | NULL                |                               |
| HORA             | int(11)   | NO   | PRI | NULL                |                               |
| ID_ACTIVIDAD     | int(11)   | NO   | PRI | NULL                |                               |
| TIEMPO_ACTIVIDAD | int(11)   | NO   |     | 60                  |                               |
| PLAZAS_OCUPADAS  | int(11)   | NO   |     | NULL                |                               |
| FX_INSERT        | timestamp | NO   |     | current_timestamp() | on update current_timestamp() |
| FX_PROC_INFO     | timestamp | NO   |     | 0000-00-00 00:00:00 |                               |
+------------------+-----------+------+-----+---------------------+-------------------------------+
*/
