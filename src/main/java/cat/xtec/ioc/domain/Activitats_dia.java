
package cat.xtec.ioc.domain;

import java.util.Date;

public class Activitats_dia {
    private int a_id;
    private String a_nombre;
    //private int a_activa;
    private Date h_fecha;
    private int h_hora;
    private int h_tiempo_actividad;
    private int h_plazas_ocupadas;
    private int a_n_participantes_max;

    public Activitats_dia(int a_id, String a_nombre, Date h_fecha, int h_hora, int h_tiempo_actividad, int h_plazas_ocupadas, int a_n_participantes_max) {
        this.a_id = a_id;
        this.a_nombre = a_nombre;
        //this.a_activa = a_activa;
        this.h_fecha = h_fecha;
        this.h_hora = h_hora;
        this.h_tiempo_actividad = h_tiempo_actividad;
        this.h_plazas_ocupadas = h_plazas_ocupadas;
        this.a_n_participantes_max = a_n_participantes_max;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_nombre() {
        return a_nombre;
    }

    public void setA_nombre(String a_nombre) {
        this.a_nombre = a_nombre;
    }

   /* public int getA_activa() {
        return a_activa;
    }

    public void setA_activa(int a_activa) {
        this.a_activa = a_activa;
    }*/

    public Date getH_fecha() {
        return h_fecha;
    }

    public void setH_fecha(Date h_fecha) {
        this.h_fecha = h_fecha;
    }

    public int getH_hora() {
        return h_hora;
    }

    public void setH_hora(int h_hora) {
        this.h_hora = h_hora;
    }

    public int getH_tiempo_actividad() {
        return h_tiempo_actividad;
    }

    public void setH_tiempo_actividad(int h_tiempo_actividad) {
        this.h_tiempo_actividad = h_tiempo_actividad;
    }

    public int getH_plazas_ocupadas() {
        return h_plazas_ocupadas;
    }

    public void setH_plazas_ocupadas(int h_plazas_ocupadas) {
        this.h_plazas_ocupadas = h_plazas_ocupadas;
    }

    public int getN_participantes_max() {
        return a_n_participantes_max;
    }

    public void setN_participantes_max(int a_n_participantes_max) {
        this.a_n_participantes_max = a_n_participantes_max;
    }
    
    

}