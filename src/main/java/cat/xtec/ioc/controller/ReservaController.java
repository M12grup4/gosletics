package cat.xtec.ioc.controller;
 
import cat.xtec.ioc.domain.ReservaDAO;
import cat.xtec.ioc.domain.Reserva;
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
public class ReservaController {
 

@Autowired
private ReservaDAO reservaDAO;

    /**
     *
     */
    public ReservaController() {
    }
 
    /**
     *
     * @param data (YYYY-MM-DD)
     * alta de RESERVA
     */
    
    @RequestMapping(value = "/reserva/alta", method = RequestMethod.POST)
    public ResponseEntity addReserva(@RequestBody Reserva reserva){
        System.out.println("CPMX UNO");
        reservaDAO.add(reserva);
        return new ResponseEntity("Reserva saved successfully", HttpStatus.OK);
    }
       
    /**
     *
     * @param data (YYYY-MM-DD)
     * MODIFICAR RESERVA
     * /**
        * @function updateDog
        * Aquesta funció recull la informació present del reserva, la popula als camps del formulari de creació de reserva, i en enviar
        * el formulari, actualitza les dades del reserva amb les que consten al formulari enviat amb PUT.
        * @param int {id} ID del reserva target a la BD.
        * Comprova que les dades dels camps siguin correctes i les saneja.
        * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.

     */
    @RequestMapping(value = "/reserva/modif", method = RequestMethod.PUT)
    public ResponseEntity updateReserva(@RequestBody Reserva reserva){
        System.out.println("CPMX modif");
        reservaDAO.updateReserva(reserva);
        return new ResponseEntity("Reserva modif successfully", HttpStatus.OK);
    }
             
    
 /**
     *
     * @param data (YYYY-MM-DD)
     * BAIXA DE  RESERVA
     */
    @RequestMapping(value = "/reserva/modif/{reservaid}", method = RequestMethod.PUT)
    public ResponseEntity deleteReserva(@PathVariable int reservaid,@RequestBody Reserva reserva ){
        System.out.println("CPMX MODIF");
        reservaDAO.updateReservabyId(reservaid, reserva);
        return new ResponseEntity("Reserva MODIF successfully", HttpStatus.OK);
    }

 /**
     *
     * @param data (YYYY-MM-DD)
     * BAIXA DE  RESERVA
     */
    @RequestMapping(value = "/reserva/baixa/{reservaid}", method = RequestMethod.DELETE)
    public ResponseEntity deleteReserva(@PathVariable int reservaid){
        System.out.println("CPMX delete");
        reservaDAO.deleteReserva(reservaid);
        return new ResponseEntity("Reserva deleted successfully", HttpStatus.OK);
    }
    
    
    
    
 /**
     *
     * @param data (YYYY-MM-DD)
     * CONSULTA DE  RESERVA
     */
    @RequestMapping(value = "/reserva", method = RequestMethod.GET)
    public @ResponseBody List<Reserva> getReservas() {
        System.out.println("CPMX GET RESERVAS ALL TODAS");
        return this.reservaDAO.getReservas();
        //return this.actividadesDAO.getAllActividades()
        //return new ResponseEntity("Reserva deleted successfully", HttpStatus.OK);
    }
    
     /**
     *
     * @param data (YYYY-MM-DD)
     * CONSULTA DE  RESERVA 
     */
    @RequestMapping(value = "/reserva/{idReserva}", method = RequestMethod.GET)
    public @ResponseBody Reserva getReservaById(@PathVariable int idReserva) {
      
        return this.reservaDAO.getReservaById(idReserva);
    }
            
}