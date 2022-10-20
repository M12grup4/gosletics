package cat.xtec.ioc.controller;
 
import cat.xtec.ioc.domain.Actividades;
import cat.xtec.ioc.domain.ActividadesDAO;
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
 
@RestController
public class ActividadesController {
 
 
@Autowired
private ActividadesDAO actividadesDAO;

 
public ActividadesController() {
    
    
}
 
public ActividadesController(ActividadesDAO actividadesDAO) {
    
    this.actividadesDAO = actividadesDAO;
}
 
@RequestMapping(method = RequestMethod.GET, value = "/actividades")
public @ResponseBody
List<Actividades> getAll() {
return this.actividadesDAO.getAllActividades();
}
 
@RequestMapping(value = "/actividades/{id}", method = RequestMethod.GET)
public @ResponseBody
Actividades getById(@PathVariable int id) {
return this.actividadesDAO.getById(id);
}

@RequestMapping(value = "/horario/{data}", method = RequestMethod.GET)
public @ResponseBody
List<Activitats_dia> getByDate(@PathVariable String data) {
return this.actividadesDAO.getByDate(data);
}

}
