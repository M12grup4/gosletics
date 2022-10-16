package cat.xtec.ioc.controller;
 
import cat.xtec.ioc.domain.Actividades;
import cat.xtec.ioc.domain.ActividadesDAO;
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
}

/*
@RestController
public class TeamsController {
 
@Autowired
private TeamRepository teamRepository;
 
public TeamsController() {
}
 
public TeamsController(TeamRepository teamRepository) {
this.teamRepository = teamRepository;
}
 
@RequestMapping(method = RequestMethod.GET, value = "/teams")
public @ResponseBody
List<Team> getAll() {
return this.teamRepository.getAll();
}
 
@RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
public @ResponseBody
Team getById(@PathVariable int id) {
return this.teamRepository.get(id);
}
}
*/