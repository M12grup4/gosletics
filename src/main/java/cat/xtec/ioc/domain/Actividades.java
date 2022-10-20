package cat.xtec.ioc.domain;

import java.util.Objects;

public class Actividades {

    private int id;
    private String nombre;
    private int n_participantes_max;
    private String descripcion;
    private int activa;
    private int n_participantes_min;

    
    
    public Actividades(int id, String nombre, int n_participantes_max, String descripcion, int activa, int n_participantes_min) {
        this.id = id;
        this.nombre = nombre;
        this.n_participantes_max = n_participantes_max;
        this.descripcion = descripcion;
        this.activa = activa;
        this.n_participantes_min = n_participantes_min;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getN_participantes_max() {
        return n_participantes_max;
    }

    public void setN_participantes_max(int n_participantes_max) {
        this.n_participantes_max = n_participantes_max;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getActiva() {
        return activa;
    }

    public void setActiva(int activa) {
        this.activa = activa;
    }

    public int getN_participantes_min() {
        return n_participantes_min;
    }

    public void setN_participantes_min(int n_participantes_min) {
        this.n_participantes_min = n_participantes_min;
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
    
    
    
  