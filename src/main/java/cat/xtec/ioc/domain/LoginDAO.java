/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.domain;

import cat.xtec.ioc.db.dbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author juanrodriguezguardeno & conxi
 * @param Login
 * Es vol sortida String amb les dades "id#mail#isAdmin"
 * Si id = -1 és que no s'ha validat correctament i no s'ha aconseguit cap id de client
 * Si isAdmin = true; l'usuari validat és l'administrador de l'eina
 */
@Configuration
public class LoginDAO {

    public LoginDAO() {

    }
    
    public String validaLogin(Login login) throws SQLException, IOException {
        String qry = "select aes_decrypt(email, 'AES')as email, aes_decrypt(pass, 'AES') as pass, id as idCol"
                + " FROM GL_clientes"
                + " WHERE aes_decrypt(email, 'AES')='" + login.getMail() + "' "
                + " and aes_decrypt(pass, 'AES')='" + login.getPass()+"'";

        //***********************
        //* error = "ko" , malo
        //* error = "ok" , bueno
        //************************
        String error="ko";
        String outputValida="";
        Login resLog=null;
        Boolean isOK=false;
        Boolean isAdmin=false;
        String pass="";
        String email=login.getMail();
        System.out.println("email: " + email);
        int id=-1;
        dbConnection dbConnection = new dbConnection();
        
        try (
            Connection conn = (Connection) dbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);) {
         
           while (rs.next()) {
                pass = rs.getString("pass");//són les dades recollides de la query
                email = rs.getString("email");
                
                id = rs.getInt("idCol");
                
                if ((email.equals(login.getMail()))&&(pass.equals(login.getPass()))) {//es comparen si les dades recollides són les mateixes
                    isOK = true;
                    error = "ok";
                    if (email.equals("admin@gosletic.com")) {
                        isAdmin = true;
                    } else {
                        isAdmin = false;
                    }
                    resLog = new Login(error,isAdmin,isOK, email,id);

                } else {
                    error = "ko";
                    isAdmin = false;
                    isOK = false;
                    resLog = new Login(error, isAdmin, isOK,email,id);
                }
            }
//        return resLog;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        //resLog = new Login(error, isAdmin, isOK,login.getMail(),id);
        outputValida=id +"#"+email+"#"+isAdmin; // "id#mail#isAdmin"
        System.out.println("outputValida : " + outputValida);
        return outputValida;
    }
}
