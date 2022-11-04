package cat.xtec.ioc.controller;
 
import cat.xtec.ioc.domain.Actividades;
import cat.xtec.ioc.domain.ActividadesDAO;
import cat.xtec.ioc.domain.ActividadesDisponiblesDAO;
import cat.xtec.ioc.domain.Activitats_dia;
import java.util.Date;
//import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

}
