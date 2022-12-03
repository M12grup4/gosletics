/**
 * @author Conxi Gàlvez Baixench
 * @versio TEA3 M12grup4
 * @file GosController.java
 * @descripcio Fitxer que recull totes les gestions de gossos de Goslètics
 * GosController.java
 * 
	Alta de gos
     	@RequestMapping(value = "/gossos/alta", method = RequestMethod.POST)

       Modificació de les dades d'un gos
	@RequestMapping(value = "/gossos/modif", method = RequestMethod.PUT)

	Baixa de gos determinat {idGos}
	@RequestMapping(value = "/gossos/baixa/{gosid}", method = RequestMethod.DELETE)

	Consulta de tots els gossos registrats
	@RequestMapping(value = "/gossos", method = RequestMethod.GET)

	Consulta d'un gos determinat per {idGos}
	@RequestMapping(value = "/gossos/{idPerros}", method = RequestMethod.GET)

	Consulta dels gossos que té un client determinat per {idClient}
	@RequestMapping(value = "/gossos/client/{idClient}", method = RequestMethod.GET)

	Consulta d'un gos determinat per {nomGos}
	@RequestMapping(value = "/gossos/{nomGos}", method = RequestMethod.GET)
 */


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
 
@RestController
public class GosController {
 
    @Autowired
    private PerrosDAO perrosDAO;

    public GosController() {
    }
 
    /**
     *
     * @return ResponseEntity Perros
     * @param perros
     * Alta de gos a partir de formulari (dades JSON)
     * http://localhost:8080/gosletics/gossos/alta
     */
    
    @RequestMapping(value = "/gossos/alta", method = RequestMethod.POST)
    public ResponseEntity addPerros(@RequestBody Perros perros){
        perrosDAO.add(perros);
        return new ResponseEntity("Perros saved successfully", HttpStatus.OK);
    }
       
    /**
     * 
     * @return ResponseEntity Perros
     * @param perros
     * MODIFICAR GOS
     * http://localhost:8080/gosletics/gossos/modif
     * 
     */
    @RequestMapping(value = "/gossos/modif", method = RequestMethod.PUT)
    public ResponseEntity updatePerros(@RequestBody Perros perros){
        perrosDAO.updatePerros(perros);
        return new ResponseEntity("Perros modif successfully", HttpStatus.OK);
    }
          

 /**
     * @return void
     * @param gosid
     * BAIXA DE  GOS
     * http://localhost:8080/gosletics/gossos/baixa/{gosid}
     */
    @RequestMapping(value = "/gossos/baixa/{gosid}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerros(@PathVariable int gosid){
        
        perrosDAO.deletePerros(gosid);
        return new ResponseEntity("Perros deleted successfully", HttpStatus.OK);
    }
    
        
 /**
     *@return List Perros
     * CONSULTA DE TOTS ELS GOSSOS
     * http://localhost:8080/gosletics/gossos/
     */
    @RequestMapping(value = "/gossos", method = RequestMethod.GET)
    public @ResponseBody List<Perros> getPerross() {
        
        return this.perrosDAO.getPerros();
    }
    
     /**
     *
     * @return Perros
     * @param idPerros
     * CONSULTA DE G0SS0S PER ID gos
     * http://localhost:8080/gosletics/gossos/{idPerros}
     */
    @RequestMapping(value = "/gossos/{idPerros}", method = RequestMethod.GET)
    public @ResponseBody Perros getPerrosById(@PathVariable int idPerros) {
      
        return this.perrosDAO.getPerrosById(idPerros);
    }
    
     /**
     *
     * @param idClient
     * @return List Perros
     * CONSULTA DE G0SS0S PER ID_CLIENT
     * http://localhost:8080/gosletics/gossos/client/{idclient)
     */
    @RequestMapping(value = "/gossos/client/{idClient}", method = RequestMethod.GET)
    public @ResponseBody List<Perros> getPerrosByIdClient(@PathVariable int idClient) {
      
        return this.perrosDAO.getPerrosByIdClient(idClient);
    }
            
    
     /**
     *
     * @param nomGos
     * @return List Perros
     * CONSULTA DE G0SS0S PER NOM_GOS 
     * http://localhost:8080/gosletics/gossos/{nomGos}
     */
    @RequestMapping(value = "/gossos/nomGos/{nomGos}", method = RequestMethod.GET)
    public @ResponseBody List<Perros> getPerrosByNomGos(@PathVariable String nomGos) {
      
        return this.perrosDAO.getPerrosByNomGos(nomGos);
    }
            
}