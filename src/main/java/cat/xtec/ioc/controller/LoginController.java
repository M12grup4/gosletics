/**
 * @author juanrodriguezguardeno & Conxi Gàlvez Baixench
 * @version TEA3 M12grup4
 * @file LoginController.java
 * @descripcio Fitxer que recull totes les gestions de login
 * 
	Valida un usuari des del formulari de Login
	@RequestMapping(value = "/login/valida", method =  RequestMethod.POST)
 */

package cat.xtec.ioc.controller;

import cat.xtec.ioc.domain.Login;
import cat.xtec.ioc.domain.LoginDAO;
import java.io.IOException;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {
 
@Autowired
private LoginDAO loginDAO;

public  LoginController() {
    
}
    
    public LoginController(LoginDAO loginDAO) {
    
    this.loginDAO = loginDAO;
}

    
    @RequestMapping(value = "/login/valida", method =  RequestMethod.POST)
    public ResponseEntity validaLogin(@RequestBody Login login) throws SQLException, IOException{
       
        String resultado=loginDAO.validaLogin(login);
        // passem objecte Login a format JSON
        //String resultadoStr = resultado.toString();
        return new ResponseEntity(resultado, HttpStatus.OK);
      
      
    }
}