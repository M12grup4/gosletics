package cat.xtec.ioc.controller;
 
import cat.xtec.ioc.domain.Actividades;
import cat.xtec.ioc.domain.ActividadesDAO;
import cat.xtec.ioc.domain.PerrosDAO;
import cat.xtec.ioc.domain.ActividadesDisponiblesDAO;
import cat.xtec.ioc.domain.Activitats_dia;
import cat.xtec.ioc.domain.Clientes;
import cat.xtec.ioc.domain.ClientesDAO;
import cat.xtec.ioc.domain.Perros;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
//import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
/**
 *
 * @author CONXI
 */
@RestController
public class ActividadesController {
 
 
@Autowired
private ActividadesDAO actividadesDAO;


@Autowired
private ActividadesDisponiblesDAO actividadesDisponiblesDAO;

@Autowired
private ClientesDAO clientesDAO;

    /**
     *
     */
    public ActividadesController() {
    
    
}
 
    /**
     *
     * @param actividadesDAO - constructor classe objecte d'accès a les dades
     */
    public ActividadesController(ActividadesDAO actividadesDAO) {
        this.actividadesDAO = actividadesDAO;
    }
 
    /**
     *
     * @return Retorna totes les activitats a la petició GET http://localhost:8080/gosletics/actividades
     */
    @RequestMapping(method = RequestMethod.GET, value = "/actividades")
    public @ResponseBody
        List<Actividades> getAll() {
        System.out.println("ACTIVIDADES UNO");
        return this.actividadesDAO.getAllActividades();
    }
 
    /**
     * @param id  - id de l'Activitat
     * @return Retorna el DETALL DE L'ACTIVITAT per idActivitat
     * @RequestMapping http://localhost:8080/gosletics/actividades/{id}
     */
    @RequestMapping(value = "/actividades/{id}", method = RequestMethod.GET)
    public @ResponseBody
        Actividades getById(@PathVariable int id) {
        return this.actividadesDAO.getById(id);
    }

    /**
     * @param data (YYYY-MM-DD)
     * @return Retorna l'HORARI D'ACTIVITATS d'un dia determinat {data} donada a la petició GET 
     * @RequestMapping http://localhost:8080/gosletics/horario/{data}
     */
    @RequestMapping(value = "/horario/{data}", method = RequestMethod.GET)
    public @ResponseBody
        List<Activitats_dia> getByDate(@PathVariable String data) {
        return this.actividadesDAO.getByDate(data);
    }


    /** 
     * @param data (YYYY-MM-DD)
     * @return Retorna totes les ACTIVITATS DISPONIBLES a partir d'una {data} donada a la petició GET 
     * @RequestMapping http://localhost:8080/gosletics/reservas/{data}
     */
    @RequestMapping(value = "/reservas/{data}", method = RequestMethod.GET)
    public @ResponseBody
         List<Activitats_dia> getReservasByDate(@PathVariable String data) {
         return this.actividadesDisponiblesDAO.getReservasByDate(data);
    }

     /**
     * @return List<Clientes>
     * @return Retorna una llista de tots els clients
     * http://localhost:8080/gosletics/clientes
     */
    @RequestMapping(method = RequestMethod.GET, value = "/clientes")
    public @ResponseBody
    List<Clientes> getAllClientes() throws SQLException, IOException {
        return this.clientesDAO.getAllClientes();
    }

    /**
     * @return Clientes
     * @return Retorna objecte Clientes per [idClient}
     * @RequestMapping http://localhost:8080/gosletics/clientes/{idClient}
     *         
     *  gl_clientes
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
     * 
     */
    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Clientes getClienteById(@PathVariable int id) {
        return this.clientesDAO.getClientById(id);
    }         
               
}