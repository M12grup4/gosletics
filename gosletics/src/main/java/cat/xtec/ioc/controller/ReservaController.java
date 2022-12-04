/**
 * @author Conxi Gàlvez Baixench
 * @versio TEA3 M12grup4
 * @file ReservaController.java
 * @descripcio Fitxer que recull totes les gestions de Reservers de Goslètics
 *      Alta de Reserva
 *	@RequestMapping(value = "/reserva/alta", method = RequestMethod.POST)
 *	
 *	Anul·lació de Reserva per {idReserva}
 *	@RequestMapping(value = "/reserva/baixa/{reservaid}", method = RequestMethod.DELETE)
 *
 *	Consulta de Reserves actuals de Goslèctics	
 *	@RequestMapping(value = "/reserva", method = RequestMethod.GET)
 *
 *	Consulta d'una Reserva determinada per {idReserva}
 *	@RequestMapping(value = "/reserva/{idReserva}", method = RequestMethod.GET)
 **/

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
     * @param reserva
     * @return ResponseEntity reserva
     * alta de RESERVA
     */
    
    @RequestMapping(value = "/reserva/alta", method = RequestMethod.POST)
    public ResponseEntity addReserva(@RequestBody Reserva reserva){
        reservaDAO.add(reserva);
        return new ResponseEntity("Reserva saved successfully", HttpStatus.OK);
    }
       
    /**
     * @return ResponseEntity reserva
     * @param reserva
     * MODIFICAR RESERVA
     
     **/
    @RequestMapping(value = "/reserva/modif", method = RequestMethod.PUT)
    public ResponseEntity updateReserva(@RequestBody Reserva reserva){
        reservaDAO.updateReserva(reserva);
        return new ResponseEntity("Reserva modif successfully", HttpStatus.OK);
    }
             
    
 /**
     * @return ResponseEntity reserva
     * @param reservaid
     * BAIXA DE  RESERVA
     */
    @RequestMapping(value = "/reserva/modif/{reservaid}", method = RequestMethod.PUT)
    public ResponseEntity deleteReserva(@PathVariable int reservaid,@RequestBody Reserva reserva ){
        reservaDAO.updateReservabyId(reservaid, reserva);
        return new ResponseEntity("Reserva MODIF successfully", HttpStatus.OK);
    }

 /**
     * @return ResponseEntity reserva
     * @param reservaid
     * BAIXA DE  RESERVA
     */
    @RequestMapping(value = "/reserva/baixa/{reservaid}", method = RequestMethod.DELETE)
    public ResponseEntity deleteReserva(@PathVariable int reservaid){
        reservaDAO.deleteReserva(reservaid);
        return new ResponseEntity("Reserva deleted successfully", HttpStatus.OK);
    }
    
    
    
    
 /**
     * @return List Reserva
     * @param data (YYYY-MM-DD)
     * CONSULTA DE TOTES LES RESERVES
     * @version TEA3
     */
    @RequestMapping(value = "/reserva", method = RequestMethod.GET)
    public @ResponseBody List<Reserva> getReservas() {
        return this.reservaDAO.getReservas();
        //return this.actividadesDAO.getAllActividades()
        //return new ResponseEntity("Reserva deleted successfully", HttpStatus.OK);
    }
    
     /**
     * @return reserva
     * @param idReserva
     * CONSULTA DE  RESERVA 
     */
    @RequestMapping(value = "/reserva/{idReserva}", method = RequestMethod.GET)
    public @ResponseBody Reserva getReservaById(@PathVariable int idReserva) {
      
        return this.reservaDAO.getReservaById(idReserva);
    }
    
    /**
     * @return List Reserva
     * @param idClient (int)
     * @param idAct (int)
     * CONSULTA DE TOTES LES RESERVES d'un client i una activitat concreta
     * @version TEA4
     */
    @RequestMapping(value = "/reserves/client/{idClient}/activitat/{idAct}", method = RequestMethod.GET)
    public @ResponseBody List<Reserva> getReservesByIdClientIdAct(@PathVariable int idClient, @PathVariable int idAct) {
        return this.reservaDAO.getReservesByIdClientIdAct(idClient, idAct);
        //return this.actividadesDAO.getAllActividades()
        //return new ResponseEntity("Reserva deleted successfully", HttpStatus.OK);
    }
            
}