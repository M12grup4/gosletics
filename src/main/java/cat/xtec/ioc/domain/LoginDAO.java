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
 * @author juanrodriguezguardeno
 */
@Configuration
public class LoginDAO {

    public LoginDAO() {

    }

    public Login validaLogin(Login login) throws SQLException, IOException {
        String qry = "select aes_decrypt(email, 'AES')as email, aes_decrypt(pass, 'AES') as pass, id as idCol"
                + " FROM GL_clientes"
              + " WHERE aes_decrypt(email, 'AES')='" + login.getMail() + "' "
                + " and aes_decrypt(pass, 'AES')='" + login.getPass()+"'";
   
            Login resLog=null;
            Boolean isOK=false;
            Boolean isAdmin=false;
            //***********************
            //* error = "ko" , malo
            //* error = "ok" , bueno
            //************************
            String error="ko";
        
        dbConnection dbConnection = new dbConnection();
        
        try (
            Connection conn = (Connection) dbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);) {
         
           while (rs.next()) {
                               
                String pass = rs.getString("pass");
                String email = rs.getString("email");
                int id = rs.getInt("idCol");
                
                if ((email.equals(login.getMail()))&&(pass.equals(login.getPass()))) {
                    isOK = true;
                    error = "ok";
                    if (email.equals("admin@gostetic.com")) {
                        isAdmin = true;
                        
                    } else {
                        isAdmin = false;
                       
                    }
                     resLog = new Login(email,pass,error,isAdmin);
                     resLog.setIsOK(true);
                     resLog.setId(id);
                     

                } else {
                    error = "ko";
                    resLog = new Login(error, isAdmin, isOK);
                }

            }
            return resLog;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
        resLog = new Login(error,isAdmin, isOK);
        return resLog;

    }

}
