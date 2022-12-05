/**
 * @author Conxi Gàlvez Baixench
 * @versio TEA3 M12grup4
 * @file ClientController.java
 * @descripcio Fitxer que recull totes les gestions de clients de Goslètics
 * ClientController

	Alta de client
     	@RequestMapping(value = "/clients/alta", method = RequestMethod.POST)

        Modificació de les dades d'un client
	@RequestMapping(value = "/clients/modif/{idClient}", method = RequestMethod.PUT)

	Baixa de client determinat {idClient}
	@RequestMapping(value = "/clients/baixa/{idClient}", method = RequestMethod.DELETE)

	Consulta de tots els clients registrats
	@RequestMapping(value = "/clients", method = RequestMethod.GET)

	Consulta d'un client determinat per {idClient}
	@RequestMapping(value = "/clients/{idClient}", method = RequestMethod.GET)

	Consulta d'un client determinat per {nomClient}
	@RequestMapping(value = "/clients/nomClient/{nomClient}", method = RequestMethod.GET)
 */

package cat.xtec.ioc.controller;
 
import cat.xtec.ioc.domain.ClientesDAO;
import cat.xtec.ioc.domain.Clientes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class ClientController {

@Autowired
private ClientesDAO clientesDAO;

public ClientController() {
}
public ClientController(ClientesDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }
    /**
     *
     * @param Clientes
     * Alta de client a partir de formulari (dades JSON)
     * http://localhost:8080/clientletics/clients/alta
     */
    @RequestMapping(value = "/clients/alta", method = RequestMethod.POST)
    public ResponseEntity addClient(@RequestBody Clientes clientes) throws SQLException, IOException{
    String resultat = clientesDAO.addClient(clientes);
        return new ResponseEntity (resultat,HttpStatus.OK);
    }
     
    /**
     *
     * @param Clientes
     * MODIFICAR CLIENT
     * /**
        * @function updateDog
        * Aquesta funció recull la informació present del client, la popula als camps del formulari de creació de client, i en enviar
        * el formulari, actualitza les dades del client amb les que consten al formulari enviat amb PUT.
        * @param int {id} ID del client target a la BD.
        * Comprova que les dades dels camps siguin correctes i les saneja.
        * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.

     */
 /*   @RequestMapping(value = "/clients/modif", method = RequestMethod.PUT)
    public ResponseEntity updateClientes(@RequestBody Clientes clientes){
        clientesDAO.updateClientes(clientes);
        return new ResponseEntity("Clientes modif successfully", HttpStatus.OK);
    }*/
             


 /**
     *
     * @param {clientid}
     * BAIXA DE  CLIENT
     */
 /*   @RequestMapping(value = "/clients/baixa/{clientid}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClientes(@PathVariable int clientid){
        
        clientesDAO.deleteClientes(clientid);
        return new ResponseEntity("Clientes deleted successfully", HttpStatus.OK);
    }*/
    
        
 /**
     *
     * CONSULTA DE TOTS ELS CLIENTS
     */
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public @ResponseBody List<Clientes> getClientes() throws SQLException, IOException {
        
        return this.clientesDAO.getAllClientes();
    }
    
     /**
     *
     * @param {idClientes}
     * CONSULTA DE CLIENTS PER ID client
     */
    @RequestMapping(value = "/clients/{idClientes}", method = RequestMethod.GET)
    public @ResponseBody Clientes getClientById(@PathVariable int idClientes) {
      
        return this.clientesDAO.getClientById(idClientes);
    }
    
     
            
    
     /**
     *
     * @param {idClient}
     * CONSULTA DE G0SS0S PER NOM_GOS 
     */
   /* @RequestMapping(value = "/clients/nomClient/{nomClient}", method = RequestMethod.GET)
    public @ResponseBody List<Clientes> getClientesByNomClient(@PathVariable String nomClient) {
      
        return this.clientesDAO.getClientesByNomClient(nomClient);
    }
      */      
}