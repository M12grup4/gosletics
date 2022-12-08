/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.domain;
import java.util.Objects ;
import com.google.gson.annotations.SerializedName;
//import java.util.Objects;

/**
 *
 * @author CONXI
 */
public class Clientes {
 
        @SerializedName("id")
        private int id;
        @SerializedName("nombre")
        private String nombre;
        @SerializedName("apellido1")
        private String apellido1;
        @SerializedName("apellido2")
        private String apellido2;
        @SerializedName("fecha_nacimiento")
        private String fecha_nacimiento;
        @SerializedName("dni")
        private String dni;
        @SerializedName("email")
        private String email;
        @SerializedName("calle")
        private String calle;
        @SerializedName("numero")
        private String numero;
        @SerializedName("piso")
        private String piso;
        @SerializedName("cp")
        private String cp;
        @SerializedName("poblacion")
        private String poblacion;
        @SerializedName("pass")
        private String pass;

   /* public Clientes(int id, String nombre, String apellido1, String apellido2, String fecha_nacimiento, String dni, String email, String calle, String numero, String piso, String cp, String poblacion, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fecha_nacimiento = fecha_nacimiento;
        this.dni = dni;
        this.email = email;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.cp = cp;
        this.poblacion = poblacion;
        this.pass = pass;
    }*/
    public Clientes(String nombre, String apellido1, String apellido2, String fecha_nacimiento, String dni, String email, String calle, String numero, String piso, String cp, String poblacion, String pass) {
      //  this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fecha_nacimiento = fecha_nacimiento;
        this.dni = dni;
        this.email = email;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.cp = cp;
        this.poblacion = poblacion;
        this.pass = pass;
    }

    public Clientes(int id, String nombre, String apellido1, String apellido2, String fecha_nacimiento, String dni, String email, String calle, String numero, String piso, String cp, String poblacion, String pass) {
        this.id = id;
          this.nombre = nombre;
          this.apellido1 = apellido1;
          this.apellido2 = apellido2;
          this.fecha_nacimiento = fecha_nacimiento;
          this.dni = dni;
          this.email = email;
          this.calle = calle;
          this.numero = numero;
          this.piso = piso;
          this.cp = cp;
          this.poblacion = poblacion;
          this.pass = pass;
      }
  

    public Clientes() {
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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
        
   /*@Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.nombre);
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + Objects.hashCode(this.pass);
        hash = 47 * hash + Objects.hashCode(this.error);
        
        return hash;
    }  */
}
        
        /*MariaDB [gosletics]> describe gl_clientes;
+------------------+--------------+------+-----+---------------------+----------------+
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
+------------------+--------------+------+-----+---------------------+----------------+
15 rows in set (0.011 sec)*/

      

