/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author juanrodriguezguardeno
 */


@RestController
public class LoginController {
 
 


@Autowired
private LoginDAO loginDAO;

public  LoginController() {
    
}
    
    public LoginController(LoginDAO loginDAO) {
    
    this.loginDAO = loginDAO;
}

    
    @RequestMapping(value = "/login/valida", method =  RequestMethod.GET)
    public ResponseEntity validaLogin(@RequestBody String login) throws SQLException, IOException{
       
        Login resultado=loginDAO.validaLogin(login);
        System.out.println ("LOGON RESLTA: " + resultado);
        return new ResponseEntity("Login success", HttpStatus.OK);
    }
    
    
    
        /**
     *
     * @return Retorna les dades del login a la petició GET http://localhost:8080/gosletics/login
     */
 /*   
@RequestMapping(value = "/login/{login}", method =  RequestMethod.GET)
   // @RequestMapping(value = "/login", method =  RequestMethod.GET)
    public @ResponseBody
        Login getLogin (@PathVariable Login login) throws SQLException, IOException {
        return this.loginDAO.getLogin(login);
    }  
        
  */      
        
        
}
