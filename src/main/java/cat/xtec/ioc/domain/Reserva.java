/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

/**
 *
 * @author CONXI
 */
@Entity
@Table(name="gl_reserva")
public class Reserva {
    

    @JsonCreator
    public Reserva(@JsonProperty("id_cliente") Integer idClient, @JsonProperty("id_perro") Integer idGos, @JsonProperty("id_actividad") Integer idActivitat,
        @JsonProperty("fecha") String fecha,@JsonProperty("hora") Integer hora) {   
      
        //this.id = id;
       
        this.idCliente = idClient;
        this.fecha = fecha;
        this.hora = hora;
        this.idActivitat=idActivitat;
        this.idGos = idGos;
        
}
    @JsonCreator
    public Reserva(@JsonProperty("id_cliente") Integer idClient, @JsonProperty("id_perro") Integer idGos, @JsonProperty("id_actividad") Integer idActivitat,
        @JsonProperty("fecha") String fecha,@JsonProperty("hora") Integer hora, @JsonProperty("id") Integer id) {   
      
        //this.id = id;
       
        this.idCliente = idClient;
        this.fecha = fecha;
        this.hora = hora;
        this.idActivitat=idActivitat;
        this.idGos = idGos;
        this.id = id;
        
}
    
     public Reserva( Reserva reserva) {
        this.idCliente = reserva.getIdCliente();
        this.fecha = reserva.getFecha();
        this.hora = reserva.getHora();
        this.idActivitat=reserva.getIdActivitat();
        this.idGos = reserva.getIdGos();
        this.id = reserva.getId(); 
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
  
 public Reserva() {
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", idCliente=" + idCliente + ", idGos=" + idGos + ", idActivitat=" + idActivitat + ", fecha=" + fecha + ", hora=" + hora + '}';
    }
 
    
   @GeneratedValue(strategy=GenerationType.IDENTITY)  
   @Id
   @Column(name="ID")
   private int id;
    
    @Column(name="ID_CLIENTE")
    private int idCliente;
    
    @Column(name="ID_PERRO")
    private int idGos;
    
   @Column(name="ID_ACTIVIDAD")
    private int idActivitat;
    
   
    @Column(name="FECHA")
    private String fecha;
    
    @Column(name="HORA")
    private int hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdGos() {
        return idGos;
    }

    public void setIdGos(int idGos) {
        this.idGos = idGos;
    }

    public int getIdActivitat() {
        return idActivitat;
    }

    public void setIdActivitat(int idActivitat) {
        this.idActivitat = idActivitat;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    

  
}

