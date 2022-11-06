package cat.xtec.ioc.controller;
 
import cat.xtec.ioc.domain.PerrosDAO;
import cat.xtec.ioc.domain.Perros;
import java.util.Date;
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
public class GosController {
 

@Autowired
private PerrosDAO perrosDAO;

    /**
     *
     */
    public GosController() {
    }
 
    /**
     *
     * @param data (YYYY-MM-DD)
     * @return Retorna totes les activitats duna {data} donada a la petició GET http://localhost:8080/gosletics/horario/{data}
     */
    
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
    @RequestMapping(value = "/gossos/baixa/{gosid}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerros(@PathVariable int gosid){
        System.out.println("CPMX delete");
        perrosDAO.deletePerros(gosid);
        return new ResponseEntity("Perros deleted successfully", HttpStatus.OK);
    }
    
        
 /**
     *
     * @param data (YYYY-MM-DD)
     * CONSULTA DE  RESERVA
     */
    @RequestMapping(value = "/gossos", method = RequestMethod.GET)
    public @ResponseBody List<Perros> getPerross() {
        System.out.println("CPMX GET RESERVAS ALL TODAS");
        return this.perrosDAO.getPerros();
        //return this.actividadesDAO.getAllActividades()
        //return new ResponseEntity("Perros deleted successfully", HttpStatus.OK);
    }
    
     /**
     *
     * @param data (YYYY-MM-DD)
     * CONSULTA DE  RESERVA 
     */
    @RequestMapping(value = "/gossos/{idPerros}", method = RequestMethod.GET)
    public @ResponseBody Perros getPerrosById(@PathVariable int idPerros) {
      
        return this.perrosDAO.getPerrosById(idPerros);
    }
            
}