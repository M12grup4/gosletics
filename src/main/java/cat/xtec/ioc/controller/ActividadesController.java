package cat.xtec.ioc.controller;
 
import cat.xtec.ioc.domain.Actividades;
import cat.xtec.ioc.domain.ActividadesDAO;
import cat.xtec.ioc.domain.PerrosDAO;
import cat.xtec.ioc.domain.ActividadesDisponiblesDAO;
import cat.xtec.ioc.domain.Activitats_dia;
import cat.xtec.ioc.domain.Clientes;
import cat.xtec.ioc.domain.ClientesDAO;
import cat.xtec.ioc.domain.Perros;
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
private PerrosDAO perrosDAO;

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
     *
     * @param id  - id de l'Activitat
     * @return Retorna l'activitata segons una id donada a la petició GET http://localhost:8080/gosletics/actividades/{id}
     */
    @RequestMapping(value = "/actividades/{id}", method = RequestMethod.GET)
public @ResponseBody
Actividades getById(@PathVariable int id) {
return this.actividadesDAO.getById(id);
}

    /**
     *
     * @param data (YYYY-MM-DD)
     * @return Retorna totes les activitats duna {data} donada a la petició GET http://localhost:8080/gosletics/horario/{data}
     */
    @RequestMapping(value = "/horario/{data}", method = RequestMethod.GET)
public @ResponseBody
List<Activitats_dia> getByDate(@PathVariable String data) {
return this.actividadesDAO.getByDate(data);
}


    /**
     *
     * @param data (YYYY-MM-DD)
     * @return Retorna totes les activitats duna {data} donada a la petició GET http://localhost:8080/gosletics/horario/{data}
     */
    @RequestMapping(value = "/reservas/{data}", method = RequestMethod.GET)
        public @ResponseBody
             List<Activitats_dia> getReservasByDate(@PathVariable String data) {
             return this.actividadesDisponiblesDAO.getReservasByDate(data);
                     
        }

        /**
     *
     * @return Retorna totes les activitats a la petició GET
     * http://localhost:8080/gosletics/actividades
     */
    @RequestMapping(method = RequestMethod.GET, value = "/clientes")
    public @ResponseBody
    List<Clientes> getAllClientes() {
        return this.clientesDAO.getAllClientes();
    }

    /**
     *
     * @param id - id de l'Activitat
     * @return Retorna l'activitata segons una id donada a la petició GET
     * http://localhost:8080/gosletics/actividades/{id}
     */
    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Clientes getClienteById(@PathVariable int id) {
        return this.clientesDAO.getClienteById(id);
    }         
             
    /**
     *
     * @param data (YYYY-MM-DD)
     * @return Retorna totes les activitats duna {data} donada a la petició GET http://localhost:8080/gosletics/horario/{data}
     */
    /********** from https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/*/
     @RequestMapping(value = "/gossos/alta", method = RequestMethod.POST)
    public ResponseEntity addPerros(@RequestBody Perros perros){
        System.out.println("CPMX UNO");
        perrosDAO.add(perros);
        return new ResponseEntity("Perros saved successfully", HttpStatus.OK);
    }
       
    /**
     *
     * @param data (YYYY-MM-DD)
     * MODIFICAR GOS
     * /**
        * @function updateDog
        * Aquesta funció recull la informació present del gos, la popula als camps del formulari de creació de gos, i en enviar
        * el formulari, actualitza les dades del gos amb les que consten al formulari enviat amb PUT.
        * @param int {id} ID del gos target a la BD.
        * Comprova que les dades dels camps siguin correctes i les saneja.
        * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.

     */
    
    @RequestMapping(value = "/gossos/modif", method = RequestMethod.PUT)
    public ResponseEntity updatePerros(@RequestBody Perros perros){
        System.out.println("CPMX modif");
        perrosDAO.updatePerros(perros);
        return new ResponseEntity("Perros modif successfully", HttpStatus.OK);
    }
             


 /**
     *
     * @param data (YYYY-MM-DD)
     * BAIXA DE  GOS
     */
    
   
    /*  NO FUNCIONA **************************
      
    @RequestMapping(value = "/gossos/baixa", method = RequestMethod.PUT)
    public ResponseEntity deletePerros(@RequestBody Perros perros){
        System.out.println("CPMX modif");
        perrosDAO.deletePerros(perros);
        return new ResponseEntity("Perros modif successfully", HttpStatus.OK);
    }
    
    */
    
    
    @RequestMapping(value = "/gossos/baixa/{gosid}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerros(@PathVariable int gosid){
        System.out.println("CPMX delete");
        perrosDAO.deletePerros(gosid);
        return new ResponseEntity("Perros deleted successfully", HttpStatus.OK);
    }
            
}