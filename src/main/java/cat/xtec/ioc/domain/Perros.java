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
@Table(name="gl_perros")
public class Perros {

/*------------------+---------------+------+-----+---------------------+-------------------------------+
    | Field            | Type          | Null | Key | Default             | Extra                         |
    +------------------+---------------+------+-----+---------------------+-------------------------------+
    | ID               | int(11)       | NO   | PRI | NULL                | auto_increment                |
    | ID_CLIENTE       | int(11)       | NO   |     | NULL                |                               |
    | NOMBRE           | varchar(64)   | NO   |     | NULL                |                               |
    | RAZA             | varchar(64)   | NO   |     | NULL                |                               |
    | SEXO             | text          | NO   |     | NULL                |                               |
    | PESO             | decimal(10,0) | NO   |     | NULL                |                               |
    | FECHA_NACIMIENTO | date          | NO   |     | NULL                |                               |
    | OBSERVACIONES    | text          | NO   |     | NULL                |                               |
    | FX_INSERT        | timestamp     | NO   |     | current_timestamp() | on update current_timestamp() |
    | FC_PROC_INFO     | timestamp     | NO   |     | current_timestamp() |                               |
    +------------------+---------------+------+-----+---------------------+-------------------------------+
    10 rows in set (0.009 sec)
     */
   

   /* public Perros(int idCliente, String nombre, String raza, String sexo, float peso, String fechaNacimiento, String observaciones) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.peso = peso;
        this.fechaNacimiento = fechaNacimiento;
        this.observaciones = observaciones;
    }*/
    @JsonCreator
    public Perros(@JsonProperty("nombre") String nombre, @JsonProperty("raza")String raza,@JsonProperty("id_cliente") int idCliente,
        @JsonProperty("fecha_nacimiento") String fechaNacimiento,@JsonProperty("observaciones") String observaciones2,@JsonProperty("sexo") String sexo,@JsonProperty("peso") Float peso) {      
        
//    public Perros(@JsonProperty("nombre") String nombre, @JsonProperty("raza")String raza,@JsonProperty("idCliente") int idCliente, @JsonProperty("idGos") int id,
  //      @JsonProperty("fechaNacimiento") String fechaNacimiento,@JsonProperty("observaciones") String observaciones2,@JsonProperty("sexo") String sexo,@JsonProperty("peso") Float peso) {      
     
        System.out.println ("ENTRAREMIS ====??");
        //this.id = id;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.peso = peso;
        this.fechaNacimiento = fechaNacimiento;
        this.observaciones=observaciones2;
         System.out.println ("SALIMOS DEL CONTSTRUCTOR ENTRAREMIS ====??");
         System.out.println (this);
        
}
 public Perros() {
    }
 
    @Override
    public String toString() {
        return "Perros{" + "id=" + id + ", id_cliente=" + idCliente + ", nombre=" + nombre + ", raza=" + raza + ", sexo=" + sexo + ", peso=" + peso + ", fecha_nacimiento=" + fechaNacimiento + ", observaciones=" + observaciones + '}';
    }

    
    
    @Id
    @Column(name="ID")
    private int id;
    
    @Column(name="ID_CLIENTE")
    private int idCliente;
    
    @Column(name="NOMBRE")
    private String nombre;
    
   @Column(name="RAZA")
    private String raza;
    
    @Column(name="SEXO")
    private String sexo;
    
    @Column(name="PESO")
    private float peso;
    
    @Column(name="FECHA_NACIMIENTO")
    private String fechaNacimiento;
    
    @Column(name="OBSERVACIONES")
    private String observaciones;
    

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
       
    
}

