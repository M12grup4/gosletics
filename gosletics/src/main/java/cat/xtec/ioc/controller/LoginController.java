/**
 * @author juanrodriguezguardeno & Conxi Gàlvez Baixench
 * @version TEA3 M12grup4
 * @file LoginController.java
 * Goslètic 27/11/22 - s'afegeix ruta de logout 
 *      Logincontroller.java: /login/logout
 *      LoginDAO.java: surtLogin()
 * @descripcio Fitxer que recull totes les gestions de login
 *  - Valida un usuari des del formulari de Login (POST) a /login/valida -> validaLogin(login)
 *  - Logout d'usuari (POST) a /logint/logout -> surtLogin(login)
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

    // mapeig del Login d'usuari
    @RequestMapping(value = "/login/valida", method =  RequestMethod.POST)
    public ResponseEntity validaLogin(@RequestBody Login login) throws SQLException, IOException{
        String resultado=loginDAO.validaLogin(login);
        // passem objecte Login a format JSON
        //String resultadoStr = resultado.toString();
        return new ResponseEntity(resultado, HttpStatus.OK);     
    }
    
    // mapeig del Logout d'usuari
    @RequestMapping(value = "/login/logout", method =  RequestMethod.GET)
    public ResponseEntity logoutUser() throws SQLException, IOException{
        return new ResponseEntity("logged out", HttpStatus.OK);
    }
}